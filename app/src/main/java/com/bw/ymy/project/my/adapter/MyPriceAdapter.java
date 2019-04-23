package com.bw.ymy.project.my.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ymy.project.R;
import com.bw.ymy.project.my.bean.MyPriceBean;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*我的钱包adapter
* */
public class MyPriceAdapter extends RecyclerView.Adapter<MyPriceAdapter.ViewHolder>{

    private List<MyPriceBean.ResultBean.DetailListBean> mdata;
    private Context context;

    public MyPriceAdapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }
    public  void  setlist(List<MyPriceBean.ResultBean.DetailListBean> datas)
    {
       this.mdata=datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.action_myprice_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.price.setText(mdata.get(i).getAmount()+"");

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(mdata.get(i).getConsumerTime()));
        viewHolder.data.setText(date);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        private TextView price,data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data=itemView.findViewById(R.id.xf_data);
            price=itemView.findViewById(R.id.xf_price);

        }
    }
}
