package com.bw.ymy.project.sing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.MainActivity;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.sing.bean.SingBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class SingActivity extends AppCompatActivity implements IView {

    @BindView(R.id.sing_phone)
    EditText sing_phone;

    @BindView(R.id.sing_pass)
    EditText sing_pass;

    @BindView(R.id.zhuce)
    Button zhuce;

    @BindView(R.id.sing_login)
    TextView sing_login;

    @BindView(R.id.sing_icon_pass)
    ImageView login_icon_pass;

    IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);
        //绑定
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
    }
    @OnClick({R.id.zhuce,R.id.sing_login})
    public void  singonclick(View v)
    {
      switch (v.getId())
      {
          //注册
          case R.id.zhuce:
              String phone=sing_phone.getText().toString();
              String pwd=sing_pass.getText().toString();
              //判断账号最少11位
              if(phone.length()<11)
              {
                  Toast.makeText(SingActivity.this,"请输入正确的账号长度",Toast.LENGTH_LONG).show();

              }else if(pwd.length()<5)
              {
                  Toast.makeText(SingActivity.this,"请输入正确密码长度",Toast.LENGTH_LONG).show();
              }
              Map<String,String> map=new HashMap<>();
              map.put("phone",phone+"");
              map.put("pwd",pwd+"");
              iPresenter.post(Apis.Sing,map,SingBean.class);

              break;
              //立即登录
          case R.id.sing_login:
              Intent intent=new Intent(SingActivity.this,MainActivity.class);
              startActivity(intent);
              break;

              default:break;

      }

    }
    //密码显示或隐藏
    @OnTouch(R.id.sing_icon_pass)
    public  boolean setLogin_pass(View v,MotionEvent event)
    {

        //按下时  密码显示
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            sing_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }
        //抬起时  密码隐藏
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            sing_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        return true;
    }

    @Override
    public void onSuccess(Object data) {
        if (data instanceof SingBean)
        {
            SingBean singBean= (SingBean) data;
            if(singBean.getMessage().equals("注册成功"))
            {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
