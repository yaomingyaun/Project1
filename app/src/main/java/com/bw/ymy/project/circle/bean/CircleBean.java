package com.bw.ymy.project.circle.bean;

import java.util.List;

public class CircleBean {

    /**
     * result : [{"commodityId":1,"content":"51545454545","createTime":1550780869000,"greatNum":2,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":522,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-21/1547920190221142749.jpg","nickName":"d5_38S30","userId":560,"whetherGreat":2},{"commodityId":1,"content":"??????","createTime":1550773411000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/head_pic/2019-02-21/20190221122218.png","id":521,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-21/7432520190221122331.png","nickName":"ds","userId":124,"whetherGreat":2},{"commodityId":1,"content":"??????","createTime":1550773123000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/head_pic/2019-02-21/20190221122218.png","id":520,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-21/0127920190221121843.png","nickName":"ds","userId":124,"whetherGreat":2},{"commodityId":1,"content":"??????","createTime":1550772836000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/head_pic/2019-02-21/20190221122218.png","id":519,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-21/4896520190221121356.png","nickName":"ds","userId":124,"whetherGreat":2},{"commodityId":1,"content":"??????","createTime":1550772551000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/head_pic/2019-02-21/20190221122218.png","id":518,"image":"http://172.17.8.100/images/small/circle_pic/2019-02-21/3804920190221120911.png","nickName":"ds","userId":124,"whetherGreat":2}]
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
         * commodityId : 1
         * content : 51545454545
         * createTime : 1550780869000
         * greatNum : 2
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 522
         * image : http://172.17.8.100/images/small/circle_pic/2019-02-21/1547920190221142749.jpg
         * nickName : d5_38S30
         * userId : 560
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;

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

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
