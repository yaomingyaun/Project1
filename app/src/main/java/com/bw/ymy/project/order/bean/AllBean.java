package com.bw.ymy.project.order.bean;

import java.util.List;

public class AllBean {


    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/2.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/3.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/4.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/5.jpg","commodityPrice":88,"orderDetailId":234}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190302104015640280","orderStatus":1,"payAmount":88,"payMethod":1,"userId":280},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":158,"commodityName":"系带商务鞋休闲鞋皮鞋棉鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/swxxx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/swxxx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/swxxx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/swxxx/3/5.jpg","commodityPrice":99,"orderDetailId":118}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190301134413795280","orderStatus":1,"payAmount":99,"payMethod":1,"userId":280},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/2.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/3.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/4.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/5.jpg","commodityPrice":88,"orderDetailId":75}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190228141753588280","orderStatus":1,"payAmount":88,"payMethod":1,"userId":280},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/2.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/3.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/4.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/5.jpg","commodityPrice":88,"orderDetailId":19}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190225165942808280","orderStatus":2,"payAmount":88,"payMethod":1,"userId":280}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

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

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/2.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/3.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/4.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/5.jpg","commodityPrice":88,"orderDetailId":234}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20190302104015640280
         * orderStatus : 1
         * payAmount : 88
         * payMethod : 1
         * userId : 280
         */


        private String expressCompName;
        private String expressSn;
        private String orderId;
        private String orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 32
             * commodityName : 唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋
             * commodityPic : http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/2.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/3.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/4.jpg,http://172.17.8.100/images/small/commodity/nx/fbx/1/5.jpg
             * commodityPrice : 88
             * orderDetailId : 234
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}
