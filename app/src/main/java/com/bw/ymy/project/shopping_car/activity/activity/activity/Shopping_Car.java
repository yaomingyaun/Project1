package com.bw.ymy.project.shopping_car.activity.activity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.App.Event;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.shopping_car.activity.activity.adapter.Goods_Adapter;
import com.bw.ymy.project.shopping_car.activity.activity.bean.GoodBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Shopping_Car extends Fragment implements IView {


    @BindView(R.id.goodsView)
    RecyclerView goodsView;
    private Goods_Adapter goods_adapter;

    @BindView(R.id.shop_qx)
    CheckBox shop_qx;

    @BindView(R.id.shop_qjs)
    Button shop_qjs;

    private List<GoodBean.ResultBean> list;

    @BindView(R.id.shop_sumprice)
    TextView shop_sumprice;
    private IPresenter iPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_shopping_cart, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定
        ButterKnife.bind(this,view);
        iPresenter=new IPresenter(this);
        
        goodsmanager();
        //点击商品的
        goods_adapter.setListener(new Goods_Adapter.ShopCallBackListener() {
            @Override
            public void callBack(List<GoodBean.ResultBean> list) {
                double price=0;
                int sum=0;
                for (int i=0;i<list.size();i++)
                {
                    GoodBean.ResultBean resultBean=list.get(i);
                    if(resultBean.isChecked())
                    {
                        price+=resultBean.getPrice()*resultBean.getCount();
                        sum++;
                    }
                }
                if(sum<list.size())
                {
                    shop_qx.setChecked(false);
                }else
                {
                    shop_qx.setChecked(true);
                }
                shop_sumprice.setText("￥"+price);
                shop_sumprice.setTextColor(Color.RED);
            }
        });
    }

    private void goodsmanager() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        goodsView.setLayoutManager(layoutManager);
        goods_adapter=new Goods_Adapter(getContext());
        goodsView.setAdapter(goods_adapter);

        iPresenter.get(Apis.GOODS,GoodBean.class);
    }
List<GoodBean.ResultBean> goobean;
    //点击
    @OnClick({R.id.shop_qx,R.id.shop_qjs})
    public  void  setonclick(View view)
    {
        switch (view.getId())
        {
                //点击全选
            case R.id.shop_qx:
                double price=0;
                for (int i=0;i<goobean.size();i++)
                {
                    GoodBean.ResultBean resultBean=goobean.get(i);

                    resultBean.setChecked(shop_qx.isChecked());

                    price+=resultBean.getPrice()*resultBean.getCount();
                }
                if(shop_qx.isChecked())
                {
                    shop_sumprice.setText("￥"+price);
                }else
                {
                    shop_sumprice.setText("0.00");
                }
                goods_adapter.notifyDataSetChanged();
                break;
        //点击去结算
            case R.id.shop_qjs:

                list=new ArrayList<>();
                for (int i=0;i<goobean.size();i++)
                {
                    GoodBean.ResultBean   good=goobean.get(i);
                    if(good.isChecked())
                    {
                        list.add(goobean.get(i));
                    }
                }
                if(list.size()!=0)
                {
                    Intent intent=new Intent(getContext(),JSActivity.class);
                      EventBus.getDefault().postSticky(new Event(list,"data"));
                    startActivity(intent);

                }else

                {
                    Toast.makeText(getContext(), "请选择！", Toast.LENGTH_SHORT).show();
                }
                break;

                default:break;
        }
    }
    @Override
    public void onSuccess(Object data) {
        if(data instanceof GoodBean)
        {
            GoodBean bean= (GoodBean) data;
            goobean= bean.getResult();
            goods_adapter.setlist(bean.getResult());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
