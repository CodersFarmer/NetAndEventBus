package com.heima.yqz.okhttputils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mryang on 2016/12/14.
 */

public class SecondActivity extends AppCompatActivity{
    private RecyclerView mRv;
    private ImageView mImageView;
    private HomeShopBean newShopBean;
    private String header = "http://10.0.2.2:8080/RedBabyShop";
    //创建图片集合，存储网络加载的图片
    private List<ImageView> imageViewList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mRv = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRv.setLayoutManager(lm);
        mRv.setAdapter(new MyAdapter());
    }
    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
            return new MyViewHolder(view);
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //设置图片资源
            if(newShopBean != null){
                String url = header+newShopBean.homeTopic.get(position).pic;
                //从网络加载数据
                //Picasso.with(getApplicationContext()).load(url).into(mImageView);
                /**
                 * Glide.with(context)
                 .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                 .into(ivImg);
                 * */
                Glide.with(getApplicationContext()).load(url).into(mImageView);
            }
        }
        @Override
        public int getItemCount() {
            if(newShopBean != null){
                return newShopBean.homeTopic.size();
            }
            return 10;
        }
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.recyclerview_item_iv);
        }
    }
    //接收EventBus的数据
    //注解
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(HomeShopBean bean) {
        newShopBean = bean;
    }
    //在可见的时候，注册
    @Override
    protected void onStart() {
        super.onStart();
        //注册EventBus
        EventBus.getDefault().register(this);
    }
    //在销毁的时候，注销
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }
}
