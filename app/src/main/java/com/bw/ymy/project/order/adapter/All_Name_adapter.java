package com.bw.ymy.project.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.ymy.project.R;
import com.bw.ymy.project.order.bean.AllBean;

import java.util.ArrayList;
import java.util.List;

;

/*
 * yao
 *  全部订单adapter
 * */
public class All_Name_adapter extends RecyclerView.Adapter<All_Name_adapter.ViewHolder> {

    private List<AllBean.OrderListBean> mdata;

    private Context context;

    public All_Name_adapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }

    public  void  setlist(List<AllBean.OrderListBean> datas)
    {
       this.mdata=datas;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     View   view=LayoutInflater.from(context).inflate(R.layout.action_all_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {



        viewHolder.dingdanhao.setText("订单号："+mdata.get(i).getOrderId());


        //布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        viewHolder.goodsnamegoods.setLayoutManager(layoutManager);
        All_Goods_adapter all_goods_adapter=new All_Goods_adapter(context,mdata.get(i).getDetailList());
        viewHolder.goodsnamegoods.setAdapter(all_goods_adapter);


        //点击事件
        viewHolder.but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allNameOnclick!=null)
                {
                    allNameOnclick.onclick(mdata.get(i));
                }
            }
        });

        String orderStatus = mdata.get(i).getOrderStatus();
        int i1 = Integer.parseInt(orderStatus);
        switch (i1){
            case 1:
                viewHolder.but1.setText("去支付");
                break;
            case 2:
                viewHolder.but1.setText("点击收货");
                break;
            case 3:
                viewHolder.but1.setText("去评价");
                break;
            case 9:
                viewHolder.but1.setText("完成");
                break;
            default:
                break;
        }







    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{


        private TextView dingdanhao;
            Button but1;
        RecyclerView goodsnamegoods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        but1=itemView.findViewById(R.id.but1);
            dingdanhao=itemView.findViewById(R.id.dingdanhao);
            goodsnamegoods=itemView.findViewById(R.id.goodsnamegoods);

        }
    }

    public  AllNameOnclick allNameOnclick;

    public  void  setonclick(AllNameOnclick  anclick)
    {
        this.allNameOnclick=anclick;
    }

    public  interface AllNameOnclick
    {

        void  onclick(AllBean.OrderListBean list);
    }
}
