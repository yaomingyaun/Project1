package com.bw.ymy.project.shopping_car.activity.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bw.ymy.project.App.CountView;
import com.bw.ymy.project.App.PayView;
import com.bw.ymy.project.R;
import com.bw.ymy.project.shopping_car.activity.activity.bean.GoodBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 *  yao
 *
 *
 */
public class JS_Adapter extends RecyclerView.Adapter<JS_Adapter.ViewHolder>{

    private List<GoodBean.ResultBean> mdata;
    private Context mcontext;


    public JS_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<GoodBean.ResultBean> datas) {
       this. mdata=datas;
        notifyDataSetChanged();
    }
    public  int getid(int position)
    {
        return  mdata.get(position).getCommodityId();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mcontext,R.layout.action_pay_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.title.setText(mdata.get(i).getCommodityName());
        viewHolder.price.setText("￥"+mdata.get(i).getPrice()+"");
        Uri uri=Uri.parse(mdata.get(i).getPic());
        viewHolder.icon.setImageURI(uri);
            //商品VIEW
       viewHolder.payView.setData(this,mdata,i);



    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder
    {
        private SimpleDraweeView icon;
        private TextView title,price;
        private PayView payView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.pay_icon);
            title=itemView.findViewById(R.id.pay_title);
            price=itemView.findViewById(R.id.pay_price);
            payView=itemView.findViewById(R.id.payview);



        }
    }
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<GoodBean.ResultBean> list);
    }
}
