package com.bw.ymy.project.mvp.prensenter;

import com.bw.ymy.project.mvp.model.IModel;
import com.bw.ymy.project.mvp.mycallback.MyCallBack;
import com.bw.ymy.project.mvp.view.IView;

import java.util.Map;

public class IPresenter implements Presnenter {

    private IView iView;
    private IModel iModel;

    public IPresenter(IView iView) {
        this.iView = iView;
        iModel=new IModel();
    }

    //post
    @Override
    public void post(String url, Map<String, String> map, Class clazz) {
        iModel.post(url, map, clazz, new MyCallBack() {
            @Override
            public void Onsuccess(Object data) {
                iView.onSuccess(data);
            }
            @Override
            public void onFail(String error) {
                iView.onSuccess(error);
            }
        });
    }
    @Override
    public void get(String url, Class clazz) {

        iModel.get(url, clazz, new MyCallBack() {
            @Override
            public void Onsuccess(Object data) {
                iView.onSuccess(data);
            }

            @Override
            public void onFail(String error) {
            iView.onSuccess(error);
            }
        });
    }

    //delete
    @Override
    public void delete(String url, Class clazz) {
        iModel.delete(url, clazz, new MyCallBack() {
            @Override
            public void Onsuccess(Object data) {
                iView.onSuccess(data);
            }

            @Override
            public void onFail(String error) {
                iView.onSuccess(error);
            }
        });
    }

    @Override
    public void put(String url, Map<String, String> map, Class clazz) {
        iModel.put(url, map, clazz, new MyCallBack() {
            @Override
            public void Onsuccess(Object data) {
                iView.onSuccess(data);
            }
            @Override
            public void onFail(String error) {
                iView.onSuccess(error);
            }
        });
    }

    public  void  detach()
    {
        iModel=null;
        iView=null;
    }
}
