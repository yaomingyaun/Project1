package com.bw.ymy.project.order.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.order.adapter.All_Name_adapter;
import com.bw.ymy.project.order.bean.AllBean;
import com.bw.ymy.project.order.bean.QSH_Bean;
import com.bw.ymy.project.order.bean.QZF_Bean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Order extends Fragment implements IView {

    @BindView(R.id.all_dingdan)
    ImageView all_dingdan;

    @BindView(R.id.all_dfk)
    ImageView all_dfk;


    @BindView(R.id.wancheng)
    ImageView wancheng;


    @BindView(R.id.all_dsh)
    ImageView all_dsh;


    @BindView(R.id.all_dpj)
    ImageView all_dpj;



    @BindView(R.id.goods_recylerview)
    RecyclerView goods_recylerview;
    All_Name_adapter all_adapter;
    int idss;
    int page=1;
    IPresenter iPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_order, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定
        ButterKnife.bind(this,view);
        iPresenter=new IPresenter(this);





    }
    //点击
    @OnClick({R.id.all_dingdan,R.id.all_dfk,R.id.wancheng,R.id.all_dsh,R.id.all_dpj})
    public  void  setlick(View v)
    {
        switch (v.getId())
        {
            //查看全部订单
            case R.id.all_dingdan:
                page=1;
                idss=0;
                allManager();
                lodata(idss);
                break;
            //查看全部订单
            case R.id.all_dfk:

                page=1;
                idss=1;
                allManager();
                lodata(idss);
                break;
            case R.id.all_dsh:

                page=1;
                idss=2;
                allManager();
                lodata(idss);
                break;
            case R.id.all_dpj:
                page=1;
                idss=3;
                allManager();
                lodata(idss);
                break;
            case R.id.wancheng:

                page=1;
                idss=9;
                allManager();
                lodata(idss);
                break;
        }
    }
    //全部订单的布局
    private void allManager( ) {
        //布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        goods_recylerview.setLayoutManager(layoutManager);
            //适配器
        all_adapter=new All_Name_adapter(getContext());
        goods_recylerview.setAdapter(all_adapter);

        //点击
        all_adapter.setonclick(new All_Name_adapter.AllNameOnclick() {
            @Override
            public void onclick(AllBean.OrderListBean list) {
                String orderStatus=list.getOrderStatus();

                int i=Integer.parseInt(orderStatus);

                switch (i)
                {
                    //点击去支付
                    case 1:
                        Map<String,String> map=new HashMap<>();
                        map.put("orderId",list.getOrderId()+"");
                        map.put("payType",1+"");
                        iPresenter.post(Apis.ZHIFU,map,QZF_Bean.class);
                        break;
                        //点击确认收货
                    case 2:
                        Map<String,String> map1=new HashMap<>();
                        map1.put("orderId",list.getOrderId()+"");
                        iPresenter.put(Apis.QSH,map1,QSH_Bean.class);
                        break;
                }
            }
        });


    }

    public  void  lodata(int idss)
    {
        iPresenter.get(String.format(Apis.ALL_ORDER,idss,page++),AllBean.class);
    }
    @Override
    public void onSuccess(Object data) {
        if(data instanceof AllBean)
        {
            AllBean allBean= (AllBean) data;
            all_adapter.setlist(allBean.getOrderList());

        }
        else if(data instanceof QZF_Bean)
        {
            QZF_Bean bean= (QZF_Bean) data;

            if(bean.getMessage().equals("支付成功"))
            {
                Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
        else if(data instanceof QSH_Bean)
        {
            QSH_Bean bean= (QSH_Bean) data;

            if(bean.getMessage().equals("确认收货成功"))
            {
                Toast.makeText(getContext(), "收货成功", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(getContext(), "收货失败", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
