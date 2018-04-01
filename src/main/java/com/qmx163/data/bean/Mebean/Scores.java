package com.qmx163.data.bean.Mebean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class Scores {

    /**
     * code : 200
     * data : {"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":44,"pages":5,"list":[{"score":5,"addTime":"2017-07-14 11:34:53","timeDiff":6,"ruleDesc":"每日签到奖励 5","shortDesc":"签到","id":"664632fc684511e7905400163e323696","ruleId":"3872adea5a1d11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-14 11:29:12","timeDiff":6,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"9ae35f56684411e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":10,"addTime":"2017-07-13 15:18:07","timeDiff":27,"ruleDesc":"关注老师奖励 10，每日最多奖励 10次","shortDesc":"关注教师","id":"6b16ecc7679b11e7905400163e323696","ruleId":"c6e91ad85a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:52:11","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"e2e09ff4676d11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:44:46","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"d99ba915676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:42:32","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"8a203c67676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:41:10","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"593ae6cc676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:40:26","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"3edea47b676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":10,"addTime":"2017-07-11 14:54:07","timeDiff":75,"ruleDesc":"关注老师奖励 10，每日最多奖励 10次","shortDesc":"关注教师","id":"bc1bda9b660511e7905400163e323696","ruleId":"c6e91ad85a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":10,"addTime":"2017-07-11 14:53:59","timeDiff":75,"ruleDesc":"关注老师奖励 10，每日最多奖励 10次","shortDesc":"关注教师","id":"b78448db660511e7905400163e323696","ruleId":"c6e91ad85a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5],"navigateFirstPage":1,"navigateLastPage":5,"firstPage":1,"lastPage":5}
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
         * size : 10
         * startRow : 1
         * endRow : 10
         * total : 44
         * pages : 5
         * list : [{"score":5,"addTime":"2017-07-14 11:34:53","timeDiff":6,"ruleDesc":"每日签到奖励 5","shortDesc":"签到","id":"664632fc684511e7905400163e323696","ruleId":"3872adea5a1d11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-14 11:29:12","timeDiff":6,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"9ae35f56684411e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":10,"addTime":"2017-07-13 15:18:07","timeDiff":27,"ruleDesc":"关注老师奖励 10，每日最多奖励 10次","shortDesc":"关注教师","id":"6b16ecc7679b11e7905400163e323696","ruleId":"c6e91ad85a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:52:11","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"e2e09ff4676d11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:44:46","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"d99ba915676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:42:32","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"8a203c67676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:41:10","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"593ae6cc676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":6,"addTime":"2017-07-13 09:40:26","timeDiff":32,"ruleDesc":"直播提问奖励 6，每次直播最多奖励 10次","shortDesc":"直播提问","id":"3edea47b676c11e7905400163e323696","ruleId":"d2c3f4bb5a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":10,"addTime":"2017-07-11 14:54:07","timeDiff":75,"ruleDesc":"关注老师奖励 10，每日最多奖励 10次","shortDesc":"关注教师","id":"bc1bda9b660511e7905400163e323696","ruleId":"c6e91ad85a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"},{"score":10,"addTime":"2017-07-11 14:53:59","timeDiff":75,"ruleDesc":"关注老师奖励 10，每日最多奖励 10次","shortDesc":"关注教师","id":"b78448db660511e7905400163e323696","ruleId":"c6e91ad85a1e11e7905400163e323696","memberId":"b368c9f05ba711e7905400163e323696"}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5]
         * navigateFirstPage : 1
         * navigateLastPage : 5
         * firstPage : 1
         * lastPage : 5
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
             * score : 5
             * addTime : 2017-07-14 11:34:53
             * timeDiff : 6
             * ruleDesc : 每日签到奖励 5
             * shortDesc : 签到
             * id : 664632fc684511e7905400163e323696
             * ruleId : 3872adea5a1d11e7905400163e323696
             * memberId : b368c9f05ba711e7905400163e323696
             */

            private int score;
            private String addTime;
            private int timeDiff;
            private String ruleDesc;
            private String shortDesc;
            private String id;
            private String ruleId;
            private String memberId;

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public int getTimeDiff() {
                return timeDiff;
            }

            public void setTimeDiff(int timeDiff) {
                this.timeDiff = timeDiff;
            }

            public String getRuleDesc() {
                return ruleDesc;
            }

            public void setRuleDesc(String ruleDesc) {
                this.ruleDesc = ruleDesc;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRuleId() {
                return ruleId;
            }

            public void setRuleId(String ruleId) {
                this.ruleId = ruleId;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }
        }
    }
}
