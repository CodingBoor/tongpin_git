package com.qmx163.data.bean.Mebean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */

public class StudySearchEn {

    /**
     * code : 200
     * data : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"id":"32f0168892df11e78d8900163e06d055","title":"2016高考数学真题","content":"<p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/f887c4ecbb8845dc87a2e0e3a3b3b86c.png\" style=\"max-width:100%;\"><br><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/861acaf97d0640f89d622e46f00ca630.png\" style=\"max-width:100%;\"><br><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/be3896b60ae44d81b44116ef1ef4afb5.png\" style=\"max-width:100%;\"><br><\/p>","img":"","issueDay":"2017-09-06","watch":115,"status":0,"addTime":"2017-09-06 16:41:38","member":null,"commentPages":null,"commentsCount":3,"likes":null,"likesCount":0,"like":false,"collect":true},{"id":"39e73fd0894111e78d8900163e06d055","title":"函数","content":"<p>1.<img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/8eb9879ed4fb4a3cab53c5799a78b8aa.png\" style=\"line-height: 20px; max-width: 100%;\"><\/p><p>2.<img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/494ca1a0a3874a2891fbd29613a230d1.png\" style=\"line-height: 20px; max-width: 100%;\"><\/p><p><br><\/p>","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/e640245d93c24c639c50fac6c5789894.png","issueDay":"2017-08-25","watch":328,"status":0,"addTime":"2017-08-25 10:58:09","member":null,"commentPages":null,"commentsCount":20,"likes":null,"likesCount":2,"like":true,"collect":false}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * list : [{"id":"32f0168892df11e78d8900163e06d055","title":"2016高考数学真题","content":"<p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/f887c4ecbb8845dc87a2e0e3a3b3b86c.png\" style=\"max-width:100%;\"><br><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/861acaf97d0640f89d622e46f00ca630.png\" style=\"max-width:100%;\"><br><\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/be3896b60ae44d81b44116ef1ef4afb5.png\" style=\"max-width:100%;\"><br><\/p>","img":"","issueDay":"2017-09-06","watch":115,"status":0,"addTime":"2017-09-06 16:41:38","member":null,"commentPages":null,"commentsCount":3,"likes":null,"likesCount":0,"like":false,"collect":true},{"id":"39e73fd0894111e78d8900163e06d055","title":"函数","content":"<p>1.<img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/8eb9879ed4fb4a3cab53c5799a78b8aa.png\" style=\"line-height: 20px; max-width: 100%;\"><\/p><p>2.<img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/494ca1a0a3874a2891fbd29613a230d1.png\" style=\"line-height: 20px; max-width: 100%;\"><\/p><p><br><\/p>","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201708/e640245d93c24c639c50fac6c5789894.png","issueDay":"2017-08-25","watch":328,"status":0,"addTime":"2017-08-25 10:58:09","member":null,"commentPages":null,"commentsCount":20,"likes":null,"likesCount":2,"like":true,"collect":false}]
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
             * id : 32f0168892df11e78d8900163e06d055
             * title : 2016高考数学真题
             * content : <p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/f887c4ecbb8845dc87a2e0e3a3b3b86c.png" style="max-width:100%;"><br></p><p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/861acaf97d0640f89d622e46f00ca630.png" style="max-width:100%;"><br></p><p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/be3896b60ae44d81b44116ef1ef4afb5.png" style="max-width:100%;"><br></p>
             * img :
             * issueDay : 2017-09-06
             * watch : 115
             * status : 0
             * addTime : 2017-09-06 16:41:38
             * member : null
             * commentPages : null
             * commentsCount : 3
             * likes : null
             * likesCount : 0
             * like : false
             * collect : true
             */

            private String id;
            private String title;
            private String content;
            private String img;
            private String issueDay;
            private int watch;
            private int status;
            private String addTime;
            private Object member;
            private Object commentPages;
            private int commentsCount;
            private Object likes;
            private int likesCount;
            private boolean like;
            private boolean collect;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getIssueDay() {
                return issueDay;
            }

            public void setIssueDay(String issueDay) {
                this.issueDay = issueDay;
            }

            public int getWatch() {
                return watch;
            }

            public void setWatch(int watch) {
                this.watch = watch;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public Object getMember() {
                return member;
            }

            public void setMember(Object member) {
                this.member = member;
            }

            public Object getCommentPages() {
                return commentPages;
            }

            public void setCommentPages(Object commentPages) {
                this.commentPages = commentPages;
            }

            public int getCommentsCount() {
                return commentsCount;
            }

            public void setCommentsCount(int commentsCount) {
                this.commentsCount = commentsCount;
            }

            public Object getLikes() {
                return likes;
            }

            public void setLikes(Object likes) {
                this.likes = likes;
            }

            public int getLikesCount() {
                return likesCount;
            }

            public void setLikesCount(int likesCount) {
                this.likesCount = likesCount;
            }

            public boolean isLike() {
                return like;
            }

            public void setLike(boolean like) {
                this.like = like;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }
        }
    }
}
