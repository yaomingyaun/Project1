package com.bw.ymy.project.mvp.prensenter;

import java.util.Map;

public interface Presnenter {

    //post
    void  post(String url, Map<String,String> map,Class clazz);

    //get
    void  get(String url,Class clazz);

    //delete
    void  delete(String url,Class clazz);

    //put

    void  put(String url, Map<String,String> map,Class clazz);
}
