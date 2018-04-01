package com.qmx163.data.bean.Mebean;

import java.util.List;

/**
 * Created by likai on 2017/8/15.
 */

public class TeachDetailEn {

    /**
     * code : 200
     * data : {"id":"4193aef355ac11e7905400163e323696","name":"杨茗哲","mobile":"","email":"","sex":1,"intro":"<p><b><font color=\"#c24f4a\">张三<span style=\"line-height: 1.5;\">*老师介绍<\/span><\/font><\/b><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/8d72a5a29783479fb94b6ed95103efee.png\" style=\"max-width:100%;\"><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/38cd0288aa2d473a957217d0e5f35663.png\" style=\"line-height: 20px; max-width: 100%;\"><br><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/a8e53e8c096d4b89929a3719e7c82fd3.png\" style=\"max-width:100%;\"><br><\/p><p><br><\/p>","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/039509e6dede4ba99005daafb69464b7.jpg","organizationId":"0618cc2b55a011e7905400163e323696","organizationName":"北京大学附属中学","titleId":"b89cc730558f11e7905400163e323696","qualification":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/48c3256c44034d86a82d01930010d2dc.png","subjects":"06af54c8559511e7905400163e323696,f2ede8b2559411e7905400163e323696","introVideoImg":"","introVideo":"","sort":2,"status":0,"famous":1,"addTime":"2017-06-20 19:33:14","updateTime":"2017-08-15 09:37:10","searchKey":null,"concern":1,"concernAmount":4,"totalDurationTime":null,"totalLessonPeriods":1,"lessonPeriodses":{"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"lessonId":"86a4f86056fb11e7905400163e323696","catalog":null,"name":null,"beginTime":null,"durationTime":null,"videoUrl":null,"coursewareName":null,"coursewareUrl":null,"status":null,"questionStatus":null,"giftStatus":null,"amount":null,"playbackAmount":null,"webinarId":null,"webinarVedioUrl":null,"lessonName":"高二化学","teacherId":"4193aef355ac11e7905400163e323696","teacherName":"杨茗哲","teacherImg":null,"subjectId":"06af54c8559511e7905400163e323696","subjectName":"化学","gradeId":null,"gradeName":null,"introVideoImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/6c833949aecb4561ad6d1c7e1d07c556.jpg","introVideo":"","concern":1,"concernAmount":10,"questionAmount":null,"id":null},{"lessonId":"2b25f2ce566d11e7905400163e323696","catalog":"第二讲","name":"概率","beginTime":"2017-08-05 10:00","durationTime":90,"videoUrl":"","coursewareName":null,"coursewareUrl":"","status":2,"questionStatus":1,"giftStatus":1,"amount":0,"playbackAmount":0,"webinarId":974370744,"webinarVedioUrl":"http://e.vhall.com/webinar/host/974370744?vc=eyJpdiI6IjNydlwvV1loMjZwNHM3c3VZdzNGVkpBPT0iLCJ2YWx1ZSI6ImJXa01ZdEhIWGlHZ0M2T3lKZUpjeUZDald3V1BQbUgxRHhZK2s0VE9md0U9IiwibWFjIjoiMWViMjk5MWZmNTE5Mjg3MmZhMzJhZGMzMGNmNDM5OTgwNjM2OTNlZDgzNTg0ZDM5Y2FkY2I2YmRlZDQ3OThlZiJ9","lessonName":"高二英语","teacherId":"4193aef355ac11e7905400163e323696","teacherName":"杨茗哲","teacherImg":null,"subjectId":"f2ede8b2559411e7905400163e323696","subjectName":"数学","gradeId":null,"gradeName":null,"introVideoImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/1217d9dca5b84402a9ce67793d11a06e.jpg","introVideo":"","concern":1,"concernAmount":4,"questionAmount":null,"id":"bde6040f75d111e78d8900163e06d055"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
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
         * id : 4193aef355ac11e7905400163e323696
         * name : 杨茗哲
         * mobile :
         * email :
         * sex : 1
         * intro : <p><b><font color="#c24f4a">张三<span style="line-height: 1.5;">*老师介绍</span></font></b></p><p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/8d72a5a29783479fb94b6ed95103efee.png" style="max-width:100%;"><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/38cd0288aa2d473a957217d0e5f35663.png" style="line-height: 20px; max-width: 100%;"><br></p><p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/a8e53e8c096d4b89929a3719e7c82fd3.png" style="max-width:100%;"><br></p><p><br></p>
         * img : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/039509e6dede4ba99005daafb69464b7.jpg
         * organizationId : 0618cc2b55a011e7905400163e323696
         * organizationName : 北京大学附属中学
         * titleId : b89cc730558f11e7905400163e323696
         * qualification : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/48c3256c44034d86a82d01930010d2dc.png
         * subjects : 06af54c8559511e7905400163e323696,f2ede8b2559411e7905400163e323696
         * introVideoImg :
         * introVideo :
         * sort : 2
         * status : 0
         * famous : 1
         * addTime : 2017-06-20 19:33:14
         * updateTime : 2017-08-15 09:37:10
         * searchKey : null
         * concern : 1
         * concernAmount : 4
         * totalDurationTime : null
         * totalLessonPeriods : 1
         * lessonPeriodses : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"lessonId":"86a4f86056fb11e7905400163e323696","catalog":null,"name":null,"beginTime":null,"durationTime":null,"videoUrl":null,"coursewareName":null,"coursewareUrl":null,"status":null,"questionStatus":null,"giftStatus":null,"amount":null,"playbackAmount":null,"webinarId":null,"webinarVedioUrl":null,"lessonName":"高二化学","teacherId":"4193aef355ac11e7905400163e323696","teacherName":"杨茗哲","teacherImg":null,"subjectId":"06af54c8559511e7905400163e323696","subjectName":"化学","gradeId":null,"gradeName":null,"introVideoImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/6c833949aecb4561ad6d1c7e1d07c556.jpg","introVideo":"","concern":1,"concernAmount":10,"questionAmount":null,"id":null},{"lessonId":"2b25f2ce566d11e7905400163e323696","catalog":"第二讲","name":"概率","beginTime":"2017-08-05 10:00","durationTime":90,"videoUrl":"","coursewareName":null,"coursewareUrl":"","status":2,"questionStatus":1,"giftStatus":1,"amount":0,"playbackAmount":0,"webinarId":974370744,"webinarVedioUrl":"http://e.vhall.com/webinar/host/974370744?vc=eyJpdiI6IjNydlwvV1loMjZwNHM3c3VZdzNGVkpBPT0iLCJ2YWx1ZSI6ImJXa01ZdEhIWGlHZ0M2T3lKZUpjeUZDald3V1BQbUgxRHhZK2s0VE9md0U9IiwibWFjIjoiMWViMjk5MWZmNTE5Mjg3MmZhMzJhZGMzMGNmNDM5OTgwNjM2OTNlZDgzNTg0ZDM5Y2FkY2I2YmRlZDQ3OThlZiJ9","lessonName":"高二英语","teacherId":"4193aef355ac11e7905400163e323696","teacherName":"杨茗哲","teacherImg":null,"subjectId":"f2ede8b2559411e7905400163e323696","subjectName":"数学","gradeId":null,"gradeName":null,"introVideoImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/1217d9dca5b84402a9ce67793d11a06e.jpg","introVideo":"","concern":1,"concernAmount":4,"questionAmount":null,"id":"bde6040f75d111e78d8900163e06d055"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
         */

        private String id;
        private String name;
        private String mobile;
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
        private int sort;
        private int status;
        private int famous;
        private String addTime;
        private String updateTime;
        private Object searchKey;
        private int concern;
        private int concernAmount;
        private Object totalDurationTime;
        private int totalLessonPeriods;
        private LessonPeriodsesBean lessonPeriodses;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
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

        public LessonPeriodsesBean getLessonPeriodses() {
            return lessonPeriodses;
        }

        public void setLessonPeriodses(LessonPeriodsesBean lessonPeriodses) {
            this.lessonPeriodses = lessonPeriodses;
        }

        public static class LessonPeriodsesBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 2
             * startRow : 1
             * endRow : 2
             * total : 2
             * pages : 1
             * list : [{"lessonId":"86a4f86056fb11e7905400163e323696","catalog":null,"name":null,"beginTime":null,"durationTime":null,"videoUrl":null,"coursewareName":null,"coursewareUrl":null,"status":null,"questionStatus":null,"giftStatus":null,"amount":null,"playbackAmount":null,"webinarId":null,"webinarVedioUrl":null,"lessonName":"高二化学","teacherId":"4193aef355ac11e7905400163e323696","teacherName":"杨茗哲","teacherImg":null,"subjectId":"06af54c8559511e7905400163e323696","subjectName":"化学","gradeId":null,"gradeName":null,"introVideoImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/6c833949aecb4561ad6d1c7e1d07c556.jpg","introVideo":"","concern":1,"concernAmount":10,"questionAmount":null,"id":null},{"lessonId":"2b25f2ce566d11e7905400163e323696","catalog":"第二讲","name":"概率","beginTime":"2017-08-05 10:00","durationTime":90,"videoUrl":"","coursewareName":null,"coursewareUrl":"","status":2,"questionStatus":1,"giftStatus":1,"amount":0,"playbackAmount":0,"webinarId":974370744,"webinarVedioUrl":"http://e.vhall.com/webinar/host/974370744?vc=eyJpdiI6IjNydlwvV1loMjZwNHM3c3VZdzNGVkpBPT0iLCJ2YWx1ZSI6ImJXa01ZdEhIWGlHZ0M2T3lKZUpjeUZDald3V1BQbUgxRHhZK2s0VE9md0U9IiwibWFjIjoiMWViMjk5MWZmNTE5Mjg3MmZhMzJhZGMzMGNmNDM5OTgwNjM2OTNlZDgzNTg0ZDM5Y2FkY2I2YmRlZDQ3OThlZiJ9","lessonName":"高二英语","teacherId":"4193aef355ac11e7905400163e323696","teacherName":"杨茗哲","teacherImg":null,"subjectId":"f2ede8b2559411e7905400163e323696","subjectName":"数学","gradeId":null,"gradeName":null,"introVideoImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/1217d9dca5b84402a9ce67793d11a06e.jpg","introVideo":"","concern":1,"concernAmount":4,"questionAmount":null,"id":"bde6040f75d111e78d8900163e06d055"}]
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
            private List<LessonItemDetalEn> list;
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

            public List<LessonItemDetalEn> getList() {
                return list;
            }

            public void setList(List<LessonItemDetalEn> list) {
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
                 * lessonId : 86a4f86056fb11e7905400163e323696
                 * catalog : null
                 * name : null
                 * beginTime : null
                 * durationTime : null
                 * videoUrl : null
                 * coursewareName : null
                 * coursewareUrl : null
                 * status : null
                 * questionStatus : null
                 * giftStatus : null
                 * amount : null
                 * playbackAmount : null
                 * webinarId : null
                 * webinarVedioUrl : null
                 * lessonName : 高二化学
                 * teacherId : 4193aef355ac11e7905400163e323696
                 * teacherName : 杨茗哲
                 * teacherImg : null
                 * subjectId : 06af54c8559511e7905400163e323696
                 * subjectName : 化学
                 * gradeId : null
                 * gradeName : null
                 * introVideoImg : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/6c833949aecb4561ad6d1c7e1d07c556.jpg
                 * introVideo :
                 * concern : 1
                 * concernAmount : 10
                 * questionAmount : null
                 * id : null
                 */

                private String lessonId;
                private Object catalog;
                private Object name;
                private Object beginTime;
                private Object durationTime;
                private Object videoUrl;
                private Object coursewareName;
                private Object coursewareUrl;
                private Object status;
                private Object questionStatus;
                private Object giftStatus;
                private Object amount;
                private Object playbackAmount;
                private Object webinarId;
                private Object webinarVedioUrl;
                private String lessonName;
                private String teacherId;
                private String teacherName;
                private Object teacherImg;
                private String subjectId;
                private String subjectName;
                private Object gradeId;
                private Object gradeName;
                private String introVideoImg;
                private String introVideo;
                private int concern;
                private int concernAmount;
                private Object questionAmount;
                private Object id;

                public String getLessonId() {
                    return lessonId;
                }

                public void setLessonId(String lessonId) {
                    this.lessonId = lessonId;
                }

                public Object getCatalog() {
                    return catalog;
                }

                public void setCatalog(Object catalog) {
                    this.catalog = catalog;
                }

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public Object getBeginTime() {
                    return beginTime;
                }

                public void setBeginTime(Object beginTime) {
                    this.beginTime = beginTime;
                }

                public Object getDurationTime() {
                    return durationTime;
                }

                public void setDurationTime(Object durationTime) {
                    this.durationTime = durationTime;
                }

                public Object getVideoUrl() {
                    return videoUrl;
                }

                public void setVideoUrl(Object videoUrl) {
                    this.videoUrl = videoUrl;
                }

                public Object getCoursewareName() {
                    return coursewareName;
                }

                public void setCoursewareName(Object coursewareName) {
                    this.coursewareName = coursewareName;
                }

                public Object getCoursewareUrl() {
                    return coursewareUrl;
                }

                public void setCoursewareUrl(Object coursewareUrl) {
                    this.coursewareUrl = coursewareUrl;
                }

                public Object getStatus() {
                    return status;
                }

                public void setStatus(Object status) {
                    this.status = status;
                }

                public Object getQuestionStatus() {
                    return questionStatus;
                }

                public void setQuestionStatus(Object questionStatus) {
                    this.questionStatus = questionStatus;
                }

                public Object getGiftStatus() {
                    return giftStatus;
                }

                public void setGiftStatus(Object giftStatus) {
                    this.giftStatus = giftStatus;
                }

                public Object getAmount() {
                    return amount;
                }

                public void setAmount(Object amount) {
                    this.amount = amount;
                }

                public Object getPlaybackAmount() {
                    return playbackAmount;
                }

                public void setPlaybackAmount(Object playbackAmount) {
                    this.playbackAmount = playbackAmount;
                }

                public Object getWebinarId() {
                    return webinarId;
                }

                public void setWebinarId(Object webinarId) {
                    this.webinarId = webinarId;
                }

                public Object getWebinarVedioUrl() {
                    return webinarVedioUrl;
                }

                public void setWebinarVedioUrl(Object webinarVedioUrl) {
                    this.webinarVedioUrl = webinarVedioUrl;
                }

                public String getLessonName() {
                    return lessonName;
                }

                public void setLessonName(String lessonName) {
                    this.lessonName = lessonName;
                }

                public String getTeacherId() {
                    return teacherId;
                }

                public void setTeacherId(String teacherId) {
                    this.teacherId = teacherId;
                }

                public String getTeacherName() {
                    return teacherName;
                }

                public void setTeacherName(String teacherName) {
                    this.teacherName = teacherName;
                }

                public Object getTeacherImg() {
                    return teacherImg;
                }

                public void setTeacherImg(Object teacherImg) {
                    this.teacherImg = teacherImg;
                }

                public String getSubjectId() {
                    return subjectId;
                }

                public void setSubjectId(String subjectId) {
                    this.subjectId = subjectId;
                }

                public String getSubjectName() {
                    return subjectName;
                }

                public void setSubjectName(String subjectName) {
                    this.subjectName = subjectName;
                }

                public Object getGradeId() {
                    return gradeId;
                }

                public void setGradeId(Object gradeId) {
                    this.gradeId = gradeId;
                }

                public Object getGradeName() {
                    return gradeName;
                }

                public void setGradeName(Object gradeName) {
                    this.gradeName = gradeName;
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

                public Object getQuestionAmount() {
                    return questionAmount;
                }

                public void setQuestionAmount(Object questionAmount) {
                    this.questionAmount = questionAmount;
                }

                public Object getId() {
                    return id;
                }

                public void setId(Object id) {
                    this.id = id;
                }
            }
        }
    }
}
