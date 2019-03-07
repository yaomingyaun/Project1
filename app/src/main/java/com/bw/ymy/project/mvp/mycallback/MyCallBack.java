package com.bw.ymy.project.mvp.mycallback;

public interface MyCallBack<T>{

    void  Onsuccess(T data);

    void  onFail(String error);
}
