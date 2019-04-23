package com.bw.ymy.project.my.activity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.MainActivity;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.bean.UPPassBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpPassActivity extends AppCompatActivity implements IView {

    @BindView(R.id.up_pass1)
    EditText up_pass1;
    @BindView(R.id.up_pass2)
    EditText up_pass2;
    @BindView(R.id.ok_pass)
    Button ok_pass;

    IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_pass);

        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        //再次输入密码是 大于6位  方可以点击
        //再次输入的密码
        up_pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //输入的数字大于6位时，点击按钮才会亮，
                ok_pass.setEnabled(s.length()>=6);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @OnClick(R.id.ok_pass)
    public  void  setonclick(View v)
    {
        switch (v.getId())
        {
            case R.id.ok_pass:
                String pass1=up_pass1.getText().toString();
                String pass2=up_pass2.getText().toString();

                Map<String,String> map=new HashMap<>();
                map.put("oldPwd",pass1);
                map.put("newPwd",pass2);
                iPresenter.put(Apis.UPPass,map,UPPassBean.class);
                break;
        }
    }
    @Override
    public void onSuccess(Object data) {
        if(data instanceof UPPassBean)
        {
            UPPassBean bean= (UPPassBean) data;
            if(bean.getMessage().equals("修改成功"))
            {
                Toast.makeText(this, bean.getMessage()+"请重新登录", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
