package com.bw.ymy.project;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.homeshow.ShowActivity;
import com.bw.ymy.project.login.bean.LoginBean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.sing.SingActivity;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity implements IView {
    //绑定
    @BindView(R.id.login_phone)
    EditText login_phone;

    @BindView(R.id.login_pass)
    EditText login_pass;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.login_icon_pass)
    ImageView login_icon_pass;

    @BindView(R.id.jz_pass)
    CheckBox jz_pass;

    @BindView(R.id.kszc)
    TextView kszc;
    @BindView(R.id.yuanxing)
    SimpleDraweeView yuanxing;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        //sharedPreferences
        sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        //记住密码
        boolean  checkpass=sharedPreferences.getBoolean("checkpass",false);
        if(checkpass)
        {
            //把值拿出来
            String phone=sharedPreferences.getString("phone",null);
            String pwd=sharedPreferences.getString("pwd",null);
            //赋值给账号 密码
            login_phone.setText(phone);
            login_pass.setText(pwd);
            //记住密码  为选中状态
            jz_pass.setChecked(true);
        }

    }
    //点击登录
    @OnClick({R.id.login,R.id.kszc})
    public void  setclick(View v)
    {
       switch (v.getId())
       {
           //点击登录
           case R.id.login:
                yuanxing.setVisibility(View.VISIBLE);

               ObjectAnimator animator=ObjectAnimator.ofFloat(yuanxing,"rotation",0f,360f);
               animator.setDuration(2000);

               animator.start();
               final int count = 2;
               Observable.interval(0, 1, TimeUnit.SECONDS)
                       .take(count + 1)
                       .map(new Func1<Long, Long>() {
                           @Override
                           public Long call(Long aLong) {
                               return count - aLong;
                           }
                       })
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<Long>() {
                           @Override
                           public void onNext(Long num) {

                               if(num==0)
                               {
                                   yuanxing.setVisibility(View.GONE);
                                   getnetwork();

                               }
                           }

                           @Override
                           public void onCompleted() {
                           }
                           @Override
                           public void onError(Throwable e) {

                           }
                       });
               break;
               //快速注册
           case R.id.kszc:
               Intent intent=new Intent(MainActivity.this,SingActivity.class);
               startActivity(intent);
               break;

       }
    }
    public  void  getnetwork()
    {

        if (NetWork.getNetype(this)== -1){
            //初始化数据
            Toast.makeText(this,"网络未连接,请连接网络",Toast.LENGTH_SHORT).show();
            NetWork.setNetworkMethod(MainActivity.this);

        }else {
            if (NetWork.getNetype(this)==1){
                Toast.makeText(this,"连接WIFI网络",Toast.LENGTH_SHORT).show();
                setpass();


            }else {
                Toast.makeText(this,"连接移动网络",Toast.LENGTH_SHORT).show();
                setpass();
            }

        }

    }
    public  void  setpass()
    {
        String phone=login_phone.getText().toString();
        String pwd=login_pass.getText().toString();
        //判断是否勾选密码
        if(jz_pass.isChecked())
        {
            //把账号密码 存入
            editor.putString("phone",phone);
            editor.putString("pwd",pwd);
            editor.putBoolean("checkpass",true);
            editor.commit();
        }else
        {
            //清空
            editor.clear();
            //提交
            editor.commit();
        }
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("pwd",pwd);
        iPresenter.post(Apis.Login,map,LoginBean.class);
    }
//密码显示或隐藏
    @OnTouch(R.id.login_icon_pass)
    public  boolean setLogin_pass(View v,MotionEvent event)
    {

        //按下时  密码显示
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            login_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }
        //抬起时  密码隐藏
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            login_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        return true;
    }
    @Override
    public void onSuccess(Object data) {
        if(data instanceof LoginBean)
        {
            LoginBean loginBean= (LoginBean) data;
            if(loginBean.getMessage().equals("登录成功"))
            {
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                //把userId sessionId  传过来
                SharedPreferences sharedPreferences=getSharedPreferences("spDemo",MODE_PRIVATE);
                sharedPreferences.edit().putString("userId",loginBean.getResult().getUserId()).putString("sessionId",loginBean.getResult().getSessionId()).commit();
                startActivity(intent);
            }else
            {
                Toast.makeText(this, loginBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
