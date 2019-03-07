package com.bw.ymy.project.shopping_car.activity.activity.bean;
// 加入购物车的bean类  加入判断 存入再次  再取出来
public class AddBean {

    private int commodityId;
    private int count;

    public AddBean(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
