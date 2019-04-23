package com.bw.ymy.project.order.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ymy.project.R;
import com.bw.ymy.project.order.bean.AllBean;
import com.facebook.drawee.view.SimpleDraweeView;


;
import java.util.List;

/*
 * yao
 *  全部订单adapter
 * */
public class All_Goods_adapter extends RecyclerView.Adapter<All_Goods_adapter.ViewHolder> {

    private List<AllBean.OrderListBean.DetailListBean> mdata;
    private Context context;

    public All_Goods_adapter(Context context, List<AllBean.OrderListBean.DetailListBean> detailList) {
        this.context = context;
      this.mdata=detailList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.action_all_goods,null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
       viewHolder.title.setText(mdata.get(i).getCommodityName());
       viewHolder.price.setText(mdata.get(i).getCommodityPrice()+"");
       viewHolder.num.setText(mdata.get(i).getCommodityCount()+"");

       String[] strings=mdata.get(i).getCommodityPic().split(",");
       Uri uri=Uri.parse(strings[0]);
        viewHolder.icon.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView icon ;
        private TextView title,price;
        private TextView num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.dd_icon);
            title=itemView.findViewById(R.id.dd_title);
            price=itemView.findViewById(R.id.dd_price);
            num=itemView.findViewById(R.id.dd_num);


        }
    }
}
