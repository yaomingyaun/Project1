package com.bw.ymy.project.mvp.model;

import com.bw.ymy.project.mvp.mycallback.MyCallBack;
import com.bw.ymy.project.mvp.retfit.Refit;
import com.google.gson.Gson;

import java.util.Map;

import rx.schedulers.Schedulers;

public class IModel implements Model {
//post
    @Override
    public void post(String url, final Map<String, String> map, final Class clazz, final MyCallBack myCallBack) {
        Refit.getInstance().post(url, map, new Refit.HttpListener() {
            @Override
            public void onsuccess(String data) {
                try {
                    Object o=new Gson().fromJson(data,clazz);
                    if(myCallBack!=null)
                    {
                        myCallBack.Onsuccess(o);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    if(myCallBack!=null)
                    {
                        myCallBack.onFail(e.getMessage());
                    }
                }
            }
            @Override
            public void onFail(String error) {
                if(myCallBack!=null)
                {
                    myCallBack.onFail(error);
                }
            }
        });
    }
//get
    @Override
    public void get(String url, final Class clazz, final MyCallBack myCallBack) {
        Refit.getInstance().get(url, new Refit.HttpListener() {
            @Override
            public void onsuccess(String data) {
                Object o=new Gson().fromJson(data,clazz);
                if(myCallBack!=null)
                {
                    myCallBack.Onsuccess(o);
                }
            }

            @Override
            public void onFail(String error) {
                if(myCallBack!=null)
                {
                    myCallBack.onFail(error);
                }
            }
        });

    }

    //delete
    @Override
    public void delete(String url, final Class clazz, final MyCallBack myCallBack) {
        Refit.getInstance().delete(url, new Refit.HttpListener() {
            @Override
            public void onsuccess(String data) {
                Object o=new Gson().fromJson(data,clazz);
                if(myCallBack!=null)
                {
                    myCallBack.Onsuccess(o);
                }
            }

            @Override
            public void onFail(String error) {
                if(myCallBack!=null)
                {
                    myCallBack.onFail(error);
                }
            }
        });
    }

    //put
    @Override
    public void put(String url, Map<String, String> map, final Class clazz, final MyCallBack myCallBack) {
        Refit.getInstance().put(url, map, new Refit.HttpListener() {
            @Override
            public void onsuccess(String data) {
                try {
                    Object o=new Gson().fromJson(data,clazz);
                    if(myCallBack!=null)
                    {
                        myCallBack.Onsuccess(o);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    if(myCallBack!=null)
                    {
                        myCallBack.onFail(e.getMessage());
                    }
                }
            }
            @Override
            public void onFail(String error) {
                if(myCallBack!=null)
                {
                    myCallBack.onFail(error);
                }
            }
        });
    }
}
