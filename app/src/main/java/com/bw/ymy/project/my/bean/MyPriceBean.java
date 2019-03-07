package com.bw.ymy.project.my.bean;

import java.util.List;

public class MyPriceBean {
    /**
     * result : {"balance":99999960,"detailList":[{"amount":39,"consumerTime":1547255922000,"orderId":"20190112091812803494","userId":494}]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
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

    public static class ResultBean {
        /**
         * balance : 99999960
         * detailList : [{"amount":39,"consumerTime":1547255922000,"orderId":"20190112091812803494","userId":494}]
         */

        private double balance;
        private List<DetailListBean> detailList;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * amount : 39
             * consumerTime : 1547255922000
             * orderId : 20190112091812803494
             * userId : 494
             */

            private int amount;
            private long consumerTime;
            private String orderId;
            private int userId;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getConsumerTime() {
                return consumerTime;
            }

            public void setConsumerTime(long consumerTime) {
                this.consumerTime = consumerTime;
            }

            public String getOrderId() {
                return orderId;
            }
            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }
            public int getUserId() {
                return userId;
            }
            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
