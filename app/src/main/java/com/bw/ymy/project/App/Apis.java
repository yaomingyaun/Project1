package com.bw.ymy.project.App;

public class Apis {
    //登录
    public  static  final String Login="user/v1/login";

    //注册
    public  static  final String Sing="user/v1/register";

    //banner展示列表
    public static  final String XBANNER="commodity/v1/bannerShow";

    //展示列表
    public  static  final String HOME="commodity/v1/commodityList";
    //展示圈子列表
    public  static  final  String quznzi="circle/v1/findCircleList?&page=1&count=20";
    //点赞
    public  static  final  String DZ="circle/verify/v1/addCircleGreat";
    //取消
    public  static  final  String Cancel="circle/verify/v1/cancelCircleGreat?circleId=%d";
    //详情轮播图
    public  static  final String COMM="commodity/v1/findCommodityDetailsById?commodityId=%s";

    //点击更多
    public static  final String MORE="commodity/v1/findCommodityListByLabel?labelId=%s&page=%d&count=10";
    //搜索
    public static  final  String SEARCH="commodity/v1/findCommodityByKeyword?keyword=%s&page=%d&count=10";
    //展示一级列表
    public static  final String TOP="commodity/v1/findFirstCategory";
    //展示二级列表
    public static final String BOOTOM="commodity/v1/findSecondCategory?firstCategoryId=%s";
    //展示二级列表内的数据
    public static final String BOOTOM2="commodity/v1/findCommodityByCategory?categoryId=%s&page=%d&count=8";
        //查询购物车
    public static final String GOODS="order/verify/v1/findShoppingCart";
    //加入购物车
    public  static  final  String Add="order/verify/v1/syncShoppingCart";
    //个人信息
    public  static  final  String  GRZLURL="user/verify/v1/getUserById";
    //修改昵称
    public  static  final  String UPName="user/verify/v1/modifyUserNick";
    //修改pass
    public  static  final  String UPPass="user/verify/v1/modifyUserPwd";
    //足迹
    public  static  final  String ZUji="commodity/verify/v1/browseList?page=1&count=20";
    //我的圈子
    public  static  final  String  MyCircle="circle/verify/v1/findMyCircleById?page=%d&count=5";
    //收货地址列表
    public  static  final  String SHDZ="user/verify/v1/receiveAddressList";
    //新增收货地址
    public  static  final  String ADD="user/verify/v1/addReceiveAddress";
    //新增收货地址
    public  static  final  String UP="user/verify/v1/changeReceiveAddress";
    //我的钱包
    public  static  final  String  QB="user/verify/v1/findUserWallet?page=1&count=1";
    //提交订单
    public  static  final  String  TJDD="order/verify/v1/createOrder";
    //查找全部订单
    public static final  String ALL_ORDER="order/verify/v1/findOrderListByStatus?status=%d&page=%d&count=5";
    //查找全部订单
    public static final  String MOREN="user/verify/v1/setDefaultReceiveAddress";

    //去支付
    public static final  String ZHIFU="order/verify/v1/pay";
    //去支付
    public static  final String QSH="order/verify/v1/confirmReceipt";




}