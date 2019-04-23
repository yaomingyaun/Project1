package com.bw.ymy.project.home.bean;

import java.util.List;

public class PLBean {

    /**
     * result : [{"commodityId":25,"content":"123","createTime":1551823001000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-07/20190307112048.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-05/4769020190305155641.jpg","nickName":"213","userId":895},{"commodityId":25,"content":"啦啦啦啦","createTime":1551748813000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-04/20190304191118.png","image":"","nickName":"嘻嘻","userId":1508},{"commodityId":25,"content":"砀山酥梨就是好，汁多皮薄，老少皆宜！！！","createTime":1551131906000,"headPic":"http://172.17.8.100/images/small/default/user.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-02-25/5907220190225155826.jpeg","nickName":"ER_O2LS6","userId":256}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 25
         * content : 123
         * createTime : 1551823001000
         * headPic : http://172.17.8.100/images/small/head_pic/2019-03-07/20190307112048.jpg
         * image : http://172.17.8.100/images/small/comment_pic/2019-03-05/4769020190305155641.jpg
         * nickName : 213
         * userId : 895
         */

        private int commodityId;
        private String content;
        private long createTime;
        private String headPic;
        private String image;
        private String nickName;
        private int userId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
