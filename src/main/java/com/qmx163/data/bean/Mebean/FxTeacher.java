package com.qmx163.data.bean.Mebean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */

public class FxTeacher {

    /**
     * code : 200
     * data : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"id":"4193aef355ac11e7905400163e323696","name":"张三","mobile":null,"email":"","sex":1,"intro":"<p><b><font color=\"#c24f4a\">张三<span style=\"line-height: 1.5;\">*老师介绍<\/span><\/font><\/b><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/8d72a5a29783479fb94b6ed95103efee.png\" style=\"max-width:100%;\"><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/38cd0288aa2d473a957217d0e5f35663.png\" style=\"line-height: 20px; max-width: 100%;\"><br><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/a8e53e8c096d4b89929a3719e7c82fd3.png\" style=\"max-width:100%;\"><br><\/p><p><br><\/p>","img":"","organizationId":"0618cc2b55a011e7905400163e323696","organizationName":"北京大学附属中学","titleId":"b89cc730558f11e7905400163e323696","qualification":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/48c3256c44034d86a82d01930010d2dc.png","subjects":"f2ede8b2559411e7905400163e323696,a4e9cb77559211e7905400163e323696","introVideoImg":"","introVideo":"","sort":null,"status":0,"famous":1,"addTime":"2017-06-20 19:33:14","updateTime":"2017-06-22 17:01:37","searchKey":null,"concern":0,"concernAmount":1,"totalDurationTime":null,"totalLessonPeriods":null,"lessonPeriodses":null},{"id":"4994595656fb11e7905400163e323696","name":"李四","mobile":null,"email":"","sex":-1,"intro":"<p>李四老师介绍<\/p>","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/d275e04cf95d45d2a6f567eb413b4458.png","organizationId":"19e42d6355a111e7905400163e323696","organizationName":"复旦大学附属中学","titleId":"bbc6cf55558f11e7905400163e323696","qualification":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/704463e792bb4c0199e4e2ccd1673a81.png","subjects":"1d988751559511e7905400163e323696,185d7569559511e7905400163e323696,133f60bb559511e7905400163e323696,0c32ac41559511e7905400163e323696,06af54c8559511e7905400163e323696,0101757d559511e7905400163e323696,f8b632ce559411e7905400163e323696,f2ede8b2559411e7905400163e323696,a4e9cb77559211e7905400163e323696","introVideoImg":"","introVideo":"","sort":null,"status":0,"famous":0,"addTime":"2017-06-22 11:31:28","updateTime":"2017-06-22 17:01:43","searchKey":null,"concern":1,"concernAmount":1,"totalDurationTime":null,"totalLessonPeriods":null,"lessonPeriodses":null}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 2
         * startRow : 1
         * endRow : 2
         * total : 2
         * pages : 1
         * list : [{"id":"4193aef355ac11e7905400163e323696","name":"张三","mobile":null,"email":"","sex":1,"intro":"<p><b><font color=\"#c24f4a\">张三<span style=\"line-height: 1.5;\">*老师介绍<\/span><\/font><\/b><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/8d72a5a29783479fb94b6ed95103efee.png\" style=\"max-width:100%;\"><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/38cd0288aa2d473a957217d0e5f35663.png\" style=\"line-height: 20px; max-width: 100%;\"><br><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/a8e53e8c096d4b89929a3719e7c82fd3.png\" style=\"max-width:100%;\"><br><\/p><p><br><\/p>","img":"","organizationId":"0618cc2b55a011e7905400163e323696","organizationName":"北京大学附属中学","titleId":"b89cc730558f11e7905400163e323696","qualification":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/48c3256c44034d86a82d01930010d2dc.png","subjects":"f2ede8b2559411e7905400163e323696,a4e9cb77559211e7905400163e323696","introVideoImg":"","introVideo":"","sort":null,"status":0,"famous":1,"addTime":"2017-06-20 19:33:14","updateTime":"2017-06-22 17:01:37","searchKey":null,"concern":0,"concernAmount":1,"totalDurationTime":null,"totalLessonPeriods":null,"lessonPeriodses":null},{"id":"4994595656fb11e7905400163e323696","name":"李四","mobile":null,"email":"","sex":-1,"intro":"<p>李四老师介绍<\/p>","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/d275e04cf95d45d2a6f567eb413b4458.png","organizationId":"19e42d6355a111e7905400163e323696","organizationName":"复旦大学附属中学","titleId":"bbc6cf55558f11e7905400163e323696","qualification":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/704463e792bb4c0199e4e2ccd1673a81.png","subjects":"1d988751559511e7905400163e323696,185d7569559511e7905400163e323696,133f60bb559511e7905400163e323696,0c32ac41559511e7905400163e323696,06af54c8559511e7905400163e323696,0101757d559511e7905400163e323696,f8b632ce559411e7905400163e323696,f2ede8b2559411e7905400163e323696,a4e9cb77559211e7905400163e323696","introVideoImg":"","introVideo":"","sort":null,"status":0,"famous":0,"addTime":"2017-06-22 11:31:28","updateTime":"2017-06-22 17:01:43","searchKey":null,"concern":1,"concernAmount":1,"totalDurationTime":null,"totalLessonPeriods":null,"lessonPeriodses":null}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 4193aef355ac11e7905400163e323696
             * name : 张三
             * mobile : null
             * email :
             * sex : 1
             * intro : <p><b><font color="#c24f4a">张三<span style="line-height: 1.5;">*老师介绍</span></font></b></p><p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/8d72a5a29783479fb94b6ed95103efee.png" style="max-width:100%;"><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/38cd0288aa2d473a957217d0e5f35663.png" style="line-height: 20px; max-width: 100%;"><br></p><p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/a8e53e8c096d4b89929a3719e7c82fd3.png" style="max-width:100%;"><br></p><p><br></p>
             * img :
             * organizationId : 0618cc2b55a011e7905400163e323696
             * organizationName : 北京大学附属中学
             * titleId : b89cc730558f11e7905400163e323696
             * qualification : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/48c3256c44034d86a82d01930010d2dc.png
             * subjects : f2ede8b2559411e7905400163e323696,a4e9cb77559211e7905400163e323696
             * introVideoImg :
             * introVideo :
             * sort : null
             * status : 0
             * famous : 1
             * addTime : 2017-06-20 19:33:14
             * updateTime : 2017-06-22 17:01:37
             * searchKey : null
             * concern : 0
             * concernAmount : 1
             * totalDurationTime : null
             * totalLessonPeriods : null
             * lessonPeriodses : null
             */

            private String id;
            private String name;
            private Object mobile;
            private String email;
            private int sex;
            private String intro;
            private String img;
            private String organizationId;
            private String organizationName;
            private String titleId;
            private String qualification;
            private String subjects;
            private String introVideoImg;
            private String introVideo;
            private Object sort;
            private int status;
            private int famous;
            private String addTime;
            private String updateTime;
            private Object searchKey;
            private int concern;
            private int concernAmount;
            private Object totalDurationTime;
            private int totalLessonPeriods;
            private Object lessonPeriodses;

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

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getOrganizationId() {
                return organizationId;
            }

            public void setOrganizationId(String organizationId) {
                this.organizationId = organizationId;
            }

            public String getOrganizationName() {
                return organizationName;
            }

            public void setOrganizationName(String organizationName) {
                this.organizationName = organizationName;
            }

            public String getTitleId() {
                return titleId;
            }

            public void setTitleId(String titleId) {
                this.titleId = titleId;
            }

            public String getQualification() {
                return qualification;
            }

            public void setQualification(String qualification) {
                this.qualification = qualification;
            }

            public String getSubjects() {
                return subjects;
            }

            public void setSubjects(String subjects) {
                this.subjects = subjects;
            }

            public String getIntroVideoImg() {
                return introVideoImg;
            }

            public void setIntroVideoImg(String introVideoImg) {
                this.introVideoImg = introVideoImg;
            }

            public String getIntroVideo() {
                return introVideo;
            }

            public void setIntroVideo(String introVideo) {
                this.introVideo = introVideo;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getFamous() {
                return famous;
            }

            public void setFamous(int famous) {
                this.famous = famous;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getSearchKey() {
                return searchKey;
            }

            public void setSearchKey(Object searchKey) {
                this.searchKey = searchKey;
            }

            public int getConcern() {
                return concern;
            }

            public void setConcern(int concern) {
                this.concern = concern;
            }

            public int getConcernAmount() {
                return concernAmount;
            }

            public void setConcernAmount(int concernAmount) {
                this.concernAmount = concernAmount;
            }

            public Object getTotalDurationTime() {
                return totalDurationTime;
            }

            public void setTotalDurationTime(Object totalDurationTime) {
                this.totalDurationTime = totalDurationTime;
            }

            public int getTotalLessonPeriods() {
                return totalLessonPeriods;
            }

            public void setTotalLessonPeriods(int totalLessonPeriods) {
                this.totalLessonPeriods = totalLessonPeriods;
            }

            public Object getLessonPeriodses() {
                return lessonPeriodses;
            }

            public void setLessonPeriodses(Object lessonPeriodses) {
                this.lessonPeriodses = lessonPeriodses;
            }
        }
    }
}
