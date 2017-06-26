package com.heima.yqz.okhttputils;

import java.util.List;

/**
 * Created by Mryang on 2016/12/14.
 */

public class HomeShopBean {
    /**
     * homeTopic : [{"id":123,"pic":"/images/home/image1.jpg","title":"活动1"},{"id":124,"pic":"/images/home/image2.jpg","title":"活动2"},{"id":125,"pic":"/images/home/image3.jpg","title":"活动3"},{"id":126,"pic":"/images/home/image4.jpg","title":"活动1"},{"id":127,"pic":"/images/home/image5.jpg","title":"活动2"},{"id":128,"pic":"/images/home/image6.jpg","title":"活动3"},{"id":129,"pic":"/images/home/wawa.jpg","title":"充气娃娃"}]
     * response : home
     */
    public String response;
    public List<HomeTopicBean> homeTopic;
    public class HomeTopicBean {
        /**
         * id : 123
         * pic : /images/home/image1.jpg
         * title : 活动1
         */
        public int id;
        public String pic;
        public String title;
    }
}
