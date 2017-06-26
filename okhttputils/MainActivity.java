package com.heima.yqz.okhttputils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private HomeShopBean homeshopbean;
    private TextView mTvsend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvsend = (TextView) findViewById(R.id.sendmesg);
        String url = "http://10.0.2.2:8080/RedBabyServer/home";
        OkHttpUtils.
                get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    //缓存失败
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    //缓存成功
                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(getApplicationContext(), "response:" + response, Toast.LENGTH_SHORT).show();
                        //把Json格式的字符串转换成对应的模型对象
                        Log.i(TAG, "onResponse: " + response);
                        processData(response);
                    }
                });
        mTvsend.setOnClickListener(this);
    }
    private void processData(String json) {
        Gson gson = new Gson();
        homeshopbean = gson.fromJson(json, HomeShopBean.class);
        //通过Eventbus方法传递集合homeshopbean黏性事件
        EventBus.getDefault().postSticky(homeshopbean);
    }
    //传递数据
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}
