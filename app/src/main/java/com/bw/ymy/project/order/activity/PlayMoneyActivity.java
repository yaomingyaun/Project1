package com.bw.ymy.project.order.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.order.bean.QZF_Bean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayMoneyActivity extends AppCompatActivity implements IView {

    @BindView(R.id.play_yue)
    RadioButton play_yue;
    @BindView(R.id.play_weixin)
    RadioButton play_weixin;
    @BindView(R.id.zhifubao)
    RadioButton zhifubao;
    @BindView(R.id.qrzf_ok)
    Button qrzf_ok;
    @BindView(R.id.fhzy)
    Button fhzy;
    @BindView(R.id.success)
    LinearLayout success;
    @BindView(R.id.shibaitupian)
    LinearLayout shibaitupian;
    @BindView(R.id.jx)
            Button jx;
    String orderId;
    int PlayType=0;
    IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_money);
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        Intent intent=getIntent();
        orderId= intent.getStringExtra("orderId");

       Toast.makeText(this, orderId, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.play_yue,R.id.play_weixin,R.id.zhifubao,R.id.qrzf_ok,R.id.fhzy,R.id.jx})
    public  void  setOnclic(View view) {
        switch (view.getId())
        {
            case R.id.play_yue:
                PlayType=1;
                break;
            case R.id.play_weixin:
                PlayType=2;
                break;
            case R.id.zhifubao:
                PlayType=3;
                break;
            case R.id.qrzf_ok:
                Map<String,String> params=new HashMap<>();
                params.put("orderId",orderId);
                params.put("payType",String.valueOf(PlayType));
            iPresenter.post(Apis.ZHIFU,params,QZF_Bean.class);
                break;
            case R.id.fhzy:
               finish();
                break;
            case R.id.jx:
                WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
                attributes.alpha = 1f;
                this.getWindow().setAttributes(attributes);
                shibaitupian.setVisibility(View.GONE);
                break;
                default:break;
        }
    }

    @Override
    public void onSuccess(Object data) {
          if(data instanceof QZF_Bean)
        {
            QZF_Bean bean= (QZF_Bean) data;

            if(bean.getMessage().equals("支付成功"))
            {
                WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
                attributes.alpha = 0.5f;
                this.getWindow().setAttributes(attributes);
                this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
              success.setVisibility(View.VISIBLE);
            }else
            {
                WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
                attributes.alpha = 0.5f;
                this.getWindow().setAttributes(attributes);
                shibaitupian.setVisibility(View.VISIBLE);
            }
        }

    }
}
