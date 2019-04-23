package com.bw.ymy.project.mvp.retfit;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bw.ymy.project.App.App;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Refit {


    private   static  Refit instance;
    //公共的网址
    private static  final String BWURL="http://mobile.bwstudent.com/small/";


    //懒汉式
    //单列
    public  static  synchronized  Refit getInstance()
    {
        if(instance==null)
        {
            instance=new Refit();
        }
        return  instance;
    }


    private BaseApi baseApi;
    //构造方法
    //读写拦截器
    private   Refit()
    {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(15,TimeUnit.SECONDS);
        builder.readTimeout(15,TimeUnit.SECONDS);
        builder.writeTimeout(15,TimeUnit.SECONDS);
        //拦截器  存值  sessionid  userid
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拿到请求
                Request original=chain.request();
                //取出保存的 userID，sessid
                SharedPreferences sharedPreferences=App.getApplication().getSharedPreferences("spDemo",Context.MODE_PRIVATE);
                String userId=sharedPreferences.getString("userId","");
                String sessionId=sharedPreferences.getString("sessionId","");
                //重新构造
                Request.Builder  requestBuilder=original.newBuilder();
                //把原来请求的参数放进去
                requestBuilder.method(original.method(),original.body());
                //添加特殊的 userid  sessionid
                if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId))
                {
                    requestBuilder.addHeader("userId",userId);
                    requestBuilder.addHeader("sessionId",sessionId);
                }
                //打包
                Request request=requestBuilder.build();
                //返回
                return chain.proceed(request);
            }
        });
        builder.retryOnConnectionFailure(true);
        OkHttpClient client=builder.build();


        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BWURL)
                .client(client)
                .build();
        baseApi=retrofit.create(BaseApi.class);

    }
    //put
    public Refit put(String url, Map<String,String> map,HttpListener listener)
    {
        if(map==null)
        {
            map=new HashMap<>();
        }
        baseApi.put(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));
        return  instance;
    }
   //post方法
    public Refit post(String url, Map<String,String> map,HttpListener listener)
    {
        if(map==null)
        {
            map=new HashMap<>();
        }
        baseApi.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));
        return  instance;
    }

    //get方法
     public  void  get(String url,HttpListener listener)
     {
         baseApi.get(url)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(getobserver(listener));

     }
        //delete
        public  void  delete(String url,HttpListener listener)
        {
            baseApi.delete(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getobserver(listener));

        }
    //上传头像
    public void postFile(String url, Map<String, String> map,HttpListener listener) {
        if (map == null) {
            map = new HashMap<>();
        }
        MultipartBody multipartBody = filesToMultipartBody(map);

        baseApi.postFile(url, multipartBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));
    }

    //上传头像
    public static MultipartBody filesToMultipartBody(Map<String,String> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            File file = new File(entry.getValue());
            builder.addFormDataPart(entry.getKey(), "图片1.png",
                    RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

        public Observer getobserver(final HttpListener listener)
        {
            Observer observer=new Observer<ResponseBody>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if(listener!=null)
                    {
                        listener.onFail(e.getMessage());
                    }

                }

                @Override
                public void onNext(ResponseBody responseBody) {


                    try {
                        String string=responseBody.string();
                        if(listener!=null)
                        {
                            listener.onsuccess(string);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if(listener!=null)
                        {
                            listener.onFail(e.getMessage());
                        }
                    }
                }


            };
            return observer;
        }



    public  interface  HttpListener
    {
        void  onsuccess(String data);
        void  onFail(String error);
    }
}
