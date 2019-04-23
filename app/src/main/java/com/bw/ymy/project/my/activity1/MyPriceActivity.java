package com.bw.ymy.project.my.activity1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.adapter.MyPriceAdapter;
import com.bw.ymy.project.my.bean.MyPriceBean;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPriceActivity extends AppCompatActivity  implements IView {

    @BindView(R.id.price_view)
    RecyclerView price_view;
    MyPriceAdapter myPriceAdapter;
    @BindView(R.id.myMoney)
    TextView myMoney;
    IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_price);

        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        //布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理
        price_view.setLayoutManager(layoutManager);

        myPriceAdapter = new MyPriceAdapter(this);
        price_view.setAdapter(myPriceAdapter);

        //网路
       iPresenter.get(Apis.QB,MyPriceBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MyPriceBean)
        {
            MyPriceBean bean= (MyPriceBean) data;

            myPriceAdapter.setlist(bean.getResult().getDetailList());

          double  blance = bean.getResult().getBalance();
            myMoney.setText(new DecimalFormat("#").format(blance));
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
