package com.bw.ymy.project.mvp.model;

import com.bw.ymy.project.mvp.mycallback.MyCallBack;

import java.util.Map;

import rx.schedulers.Schedulers;

public interface Model{
    //post
    void  post(String url, Map<String,String> map, Class clazz, MyCallBack myCallBack);

    //get
    void  get(String url,Class clazz, MyCallBack myCallBack);

    //get
    void  delete(String url,Class clazz, MyCallBack myCallBack);

    //put
    void  put(String url, Map<String,String> map, Class clazz, MyCallBack myCallBack);
    //file 上传头像方法
    void  getFile(String urlstr, Map<String, String> map, Class clazz, MyCallBack callBack);
}
