package com.bw.ymy.project.shopping_car.activity.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.App.Event;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.bean.AddRessBean;
import com.bw.ymy.project.shopping_car.activity.activity.adapter.JS_Adapter;
import com.bw.ymy.project.shopping_car.activity.activity.bean.AddBean;
import com.bw.ymy.project.shopping_car.activity.activity.bean.GoodBean;
import com.bw.ymy.project.shopping_car.activity.activity.bean.TJDD2Bean;
import com.bw.ymy.project.shopping_car.activity.activity.bean.TJDDBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JSActivity extends AppCompatActivity implements IView {

    @BindView(R.id.pay_RecyclerView)
    RecyclerView pay_RecyclerView;
    private JS_Adapter js_adapter;
    private List<GoodBean.ResultBean> list;
    @BindView(R.id.js_count)
    TextView js_count;
    @BindView(R.id.js_tijiao)
    Button js_tijiao;
    //总价
    int num=0;
    double  sumprice=0.00;
    private AddRessBean resultBean;
    IPresenter iPresenter;
    private  int add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);

        //绑定
       ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
            //创建布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pay_RecyclerView.setLayoutManager(layoutManager);
        js_adapter=new JS_Adapter(this);
        pay_RecyclerView.setAdapter(js_adapter);
        iPresenter.get(Apis.SHDZ,AddRessBean.class);
    }

    @OnClick({R.id.js_tijiao})
    public  void setlivk(View v)
    {
        switch (v.getId())
        {
            case R.id.js_tijiao:
                List<TJDD2Bean> lists=new ArrayList<>();
                for (int i=0;i<list.size();i++)
                {
                    GoodBean.ResultBean bean=list.get(i);
                    lists.add(new TJDD2Bean(bean.getCommodityId(),bean.getCount()));
                }
                 String    s=new Gson().toJson(lists);
                Map<String,String> map=new HashMap<>();
                map.put("orderInfo",s);
                map.put("totalPrice",sumprice+"");
                map.put("addressId",add+"");
                iPresenter.post(Apis.TJDD,map,TJDDBean.class);
                break;

                default:break;
        }
    }
//接受
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(Event evBean) {
        if (evBean.getName().equals("data")) {
         list= (List<GoodBean.ResultBean>) evBean.getO();

            js_adapter.setlist(list);


            js_adapter.setOnCartListChangeListener(new JS_Adapter.OnCartListChangeListener() {
                @Override
                public void onProducNumberChange(int i, int count, int number) {

                        js_adapter.ChangeNumber(i,number);
                        sumprice=js_adapter.getTotalPrice();
                        num=js_adapter.getTotalNumber();
                    js_count.setText("共计"+num+"件商品，共计"+sumprice+"元");

                }
            });
            //金额  价格

            for (int i=0;i<list.size();i++)
            {
                GoodBean.ResultBean  bean=list.get(i);
                num+=bean.getCount();
                sumprice+=bean.getCount()*bean.getPrice();
            }
            js_count.setText("共计"+num+"件商品，共计"+sumprice+"元");
        }
    }
    @Override
    public void onSuccess(Object data) {

        if(data instanceof TJDDBean)
        {
            TJDDBean bean= (TJDDBean) data;

            if(bean.getMessage().equals("创建订单成功"))
            {
                Toast.makeText(this, "创建订单成功", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "创建订单失败", Toast.LENGTH_SHORT).show();
            }
        }
        else if(data instanceof  AddRessBean)
        {
            resultBean= (AddRessBean) data;
           add=  resultBean.getResult().get(0).getId();
          //  Toast.makeText(this, resultBean.getResult().get(0)"", Toast.LENGTH_SHORT).show();
        }



    }
    @Override
    protected void onStart() {
        super.onStart();
        //注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
