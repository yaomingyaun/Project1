package com.bw.ymy.project.my.activity1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.bean.GRZL;
import com.bw.ymy.project.my.bean.UPName;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MypersonalActivity extends AppCompatActivity  implements IView {

    @BindView(R.id.my_pass)
    TextView my_pass;
    @BindView(R.id.my_name)
    TextView my_name;

    @BindView(R.id.my_icon)
    SimpleDraweeView my_icon;


   private EditText up_name;
    private GRZL grzl;

    IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypersonal);
        //绑定
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        iPresenter.get(Apis.GRZLURL,GRZL.class);

    }
    @OnClick({R.id.my_name,R.id.my_pass})
    public  void setobclick(View v)
    {
        switch (v.getId())
        {
            case R.id.my_name:
                //定义AlertDialog
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                //获取布局
                View view=View.inflate(this,R.layout.action_alert_item,null);

                    up_name=view.findViewById(R.id.up_name);
                     builder.setTitle("修改昵称");
                    up_name.setText(grzl.getResult().getNickName());
                    builder.setView(view);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String name=up_name.getText().toString();
                            Map<String,String> map=new HashMap<>();
                            map.put("nickName",name+"");
                            iPresenter.put(Apis.UPName,map,UPName.class);

                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                    break;

                    //点击 跳去修改密码
            case R.id.my_pass:
                Intent intent=new Intent(this,UpPassActivity.class);
                startActivity(intent);
                break;

                    default:break;
        }
    }
    @Override
    public void onSuccess(Object data) {
        if(data instanceof GRZL)
        {
             grzl= (GRZL) data;
          String name=  grzl.getResult().getNickName();
          my_name.setText(name);

          String pass=grzl.getResult().getPassword();
          my_pass.setText(pass);

          String icon=grzl.getResult().getHeadPic();
            Uri uri=Uri.parse(icon);
            my_icon.setImageURI(uri);

        }
        else if(data instanceof UPName)
        {
            UPName upBean= (UPName) data;
            Toast.makeText(this, upBean.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
    }
}
