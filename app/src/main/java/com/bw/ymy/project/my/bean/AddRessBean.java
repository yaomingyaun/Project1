package com.bw.ymy.project.my.bean;

import java.util.List;

public class AddRessBean {

    /**
     * result : [{"address":"北京 海淀区 八维","createTime":1551116886000,"id":866,"phone":"17832598756","realName":"李云龙","userId":280,"whetherDefault":1,"zipCode":"010101"},{"address":"北京市-北京市-海淀区","createTime":1551123525000,"id":867,"phone":"13687554232","realName":"邵处长","userId":280,"whetherDefault":2,"zipCode":"121212"}]
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
         * address : 北京 海淀区 八维
         * createTime : 1551116886000
         * id : 866
         * phone : 17832598756
         * realName : 李云龙
         * userId : 280
         * whetherDefault : 1
         * zipCode : 010101
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private String whetherDefault;
        private String zipCode;

        boolean isChecked=false;
        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(String whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
