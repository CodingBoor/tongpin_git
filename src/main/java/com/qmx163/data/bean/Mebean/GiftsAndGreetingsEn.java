package com.qmx163.data.bean.Mebean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class GiftsAndGreetingsEn {


    /**
     * code : 200
     * data : {"greetings":[{"id":"c629aa806cf211e78d8900163e06d055","name":"下午好","img":"","price":0,"sort":2,"status":1,"type":1,"addTime":"2017-07-20 10:26:00","updateTime":null,"searchKey":null},{"id":"bcbfe3e16cf211e78d8900163e06d055","name":"早上好","img":"","price":0,"sort":1,"status":1,"type":1,"addTime":"2017-07-20 10:25:44","updateTime":null,"searchKey":null}],"gifts":[{"id":"8ebddb2c6cf211e78d8900163e06d055","name":"调皮","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201707/e05a40f9b7694ef6bed1a4461dcd3b26.png","price":1,"sort":2,"status":1,"type":0,"addTime":"2017-07-20 10:24:27","updateTime":null,"searchKey":null},{"id":"4686b4115be511e7905400163e323696","name":"掌声","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/eb4c5cc9f7284ee2b5a82a3f0577bee4.png","price":1,"sort":1,"status":1,"type":0,"addTime":"2017-06-28 17:36:30","updateTime":"2017-06-28 19:38:48","searchKey":null}]}
     * message : 成功
     */

    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<GreetingsBean> greetings;
        private List<GiftsBean> gifts;

        public List<GreetingsBean> getGreetings() {
            return greetings;
        }

        public void setGreetings(List<GreetingsBean> greetings) {
            this.greetings = greetings;
        }

        public List<GiftsBean> getGifts() {
            return gifts;
        }

        public void setGifts(List<GiftsBean> gifts) {
            this.gifts = gifts;
        }

        public static class GreetingsBean {
            /**
             * id : c629aa806cf211e78d8900163e06d055
             * name : 下午好
             * img :
             * price : 0
             * sort : 2
             * status : 1
             * type : 1
             * addTime : 2017-07-20 10:26:00
             * updateTime : null
             * searchKey : null
             */

            private String id;
            private String name;
            private String img;
            private double price;
            private int sort;
            private int status;
            private int type;
            private String addTime;
            private Object updateTime;
            private Object searchKey;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getSearchKey() {
                return searchKey;
            }

            public void setSearchKey(Object searchKey) {
                this.searchKey = searchKey;
            }
        }

        public static class GiftsBean {
            /**
             * id : 8ebddb2c6cf211e78d8900163e06d055
             * name : 调皮
             * img : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201707/e05a40f9b7694ef6bed1a4461dcd3b26.png
             * price : 1
             * sort : 2
             * status : 1
             * type : 0
             * addTime : 2017-07-20 10:24:27
             * updateTime : null
             * searchKey : null
             */

            private String id;
            private String name;
            private String img;
            private double price;
            private int sort;
            private int status;
            private int type;
            private String addTime;
            private Object updateTime;
            private Object searchKey;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getSearchKey() {
                return searchKey;
            }

            public void setSearchKey(Object searchKey) {
                this.searchKey = searchKey;
            }
        }
    }
}
