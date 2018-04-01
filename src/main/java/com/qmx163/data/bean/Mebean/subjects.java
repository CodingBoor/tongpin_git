package com.qmx163.data.bean.Mebean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class subjects {


    /**
     * code : 200
     * data : [{"id":"1d988751559511e7905400163e323696","name":"地理","img":null,"intro":"地理","sort":9,"status":0},{"id":"185d7569559511e7905400163e323696","name":"历史","img":null,"intro":"历史","sort":8,"status":0},{"id":"133f60bb559511e7905400163e323696","name":"政治","img":null,"intro":"政治","sort":7,"status":0},{"id":"0c32ac41559511e7905400163e323696","name":"生物","img":null,"intro":"生物","sort":6,"status":0},{"id":"06af54c8559511e7905400163e323696","name":"化学","img":null,"intro":"化学","sort":5,"status":0},{"id":"0101757d559511e7905400163e323696","name":"物理","img":null,"intro":"物理","sort":4,"status":0},{"id":"f8b632ce559411e7905400163e323696","name":"英语","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/8c86045de8d548a1b82f64abfce6c33d.png","intro":"英语","sort":3,"status":0},{"id":"f2ede8b2559411e7905400163e323696","name":"数学","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/37928a393506457d8a7f9c6f19b6d16a.png","intro":"数学","sort":2,"status":0},{"id":"a4e9cb77559211e7905400163e323696","name":"语文","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/899b682cd02d4fd4b09a687fb7988436.png","intro":"语文","sort":1,"status":0}]
     * message : 成功
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1d988751559511e7905400163e323696
         * name : 地理
         * img : null
         * intro : 地理
         * sort : 9
         * status : 0
         */

        private String id;
        private String name;
        private Object img;
        private String intro;
        private int sort;
        private int status;

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

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
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
    }
}
