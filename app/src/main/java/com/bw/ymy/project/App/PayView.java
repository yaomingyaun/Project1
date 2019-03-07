package com.bw.ymy.project.App;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.R;
import com.bw.ymy.project.shopping_car.activity.activity.adapter.Goods_Adapter;
import com.bw.ymy.project.shopping_car.activity.activity.adapter.JS_Adapter;
import com.bw.ymy.project.shopping_car.activity.activity.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;


public class PayView extends LinearLayout implements View.OnClickListener{

    private TextView shopcar_jian;
    private TextView shopcar_num;
    private TextView shopcar_add;

    private Context context;

    public PayView(Context context) {
        super(context);
        init(context);
    }

    public PayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context=context;
        View view=View.inflate(context,R.layout.action_countview_pay_item,null);
        shopcar_jian=view.findViewById(R.id.shopcar_jian);
        shopcar_num=view.findViewById(R.id.shopcar_num);
        shopcar_add=view.findViewById(R.id. shopcar_add);
        addView(view);
        shopcar_jian.setOnClickListener(this);
        shopcar_add.setOnClickListener(this);
    }
    private int count;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopcar_add:
                count++;
                shopcar_num.setText(count+"");
              list.get(position).setCount(count);
                break;
            case R.id.shopcar_jian:
                if (count > 1) {
                    count--;
                } else {
                    Toast.makeText(context, "数量最少为1", Toast.LENGTH_SHORT).show();
                }
                shopcar_num.setText(count + "");
              list.get(position).setCount(count);
                break;

        }



    }

//    //传过来
    private  List<GoodBean.ResultBean> list=new ArrayList<>();
    private int position;
    private JS_Adapter js_adapter;

    public void setData(JS_Adapter js_adapter, List<GoodBean.ResultBean> mdata, int i) {
            this.list=mdata;
          this.  js_adapter=js_adapter;
        position=i;
        count=list.get(i).getCount();
        shopcar_num.setText(count+"");
    }
}