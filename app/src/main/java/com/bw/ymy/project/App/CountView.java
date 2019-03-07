package com.bw.ymy.project.App;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.R;
import com.bw.ymy.project.shopping_car.activity.activity.adapter.Goods_Adapter;
import com.bw.ymy.project.shopping_car.activity.activity.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountView extends RelativeLayout implements View.OnClickListener{


    private ImageView add_view;

    private ImageView jian_view;

    private TextView num_view;
    private   Context context;

    public CountView(Context context) {
        super(context);
        init(context);
    }



    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context=context;

        View view=View.inflate(context,R.layout.action_goods_view,null);
            add_view=view.findViewById(R.id.add_view);
        jian_view=view.findViewById(R.id.jian_view);
        num_view=view.findViewById(R.id. num_view);
        addView(view);
        add_view.setOnClickListener(this);
        jian_view.setOnClickListener(this);
    }

    private int count;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_view:
                count++;
                num_view.setText(count + "");
                list.get(position).setCount(count);
                break;
            case R.id.jian_view:
                if (count > 1) {
                    count--;
                } else {
                    Toast.makeText(context, "数量最少为1", Toast.LENGTH_SHORT).show();
                }
                num_view.setText(count + "");
                list.get(position).setCount(count);
                break;

        }
    }
    //传过来
    private  List<GoodBean.ResultBean> list=new ArrayList<>();
    private int position;
    private Goods_Adapter goods_adapter;
    public void setData(Goods_Adapter goods_adapter, List<GoodBean.ResultBean> mdata, int i) {
        this.list=mdata;
        this.goods_adapter=goods_adapter;

        position=i;
        count=list.get(i).getCount();
        num_view.setText(count+"");

    }


}
