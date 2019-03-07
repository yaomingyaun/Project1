package com.bw.ymy.project.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bw.ymy.project.R;
import com.bw.ymy.project.my.bean.ZujiBean;
import com.facebook.drawee.view.SimpleDraweeView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * yao
 *  我的足迹adapter
 * */
public class MyZujiAdapter extends RecyclerView.Adapter<MyZujiAdapter.ViewHolder> {

    private List<ZujiBean.ResultBean> mdata;
    private Context context;

    public MyZujiAdapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }
    public  void  setlist(List<ZujiBean .ResultBean> datas)
    {
       this.mdata=datas;
       notifyDataSetChanged();
    }
    public  int getcommodityId(int position)
    {
        return  mdata.get(position).getCommodityId();
    }
    //获取布局
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.action_zuji_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        //照片

        //名称
        viewHolder.title.setText(mdata.get(i).getCommodityName());
        //价格
        viewHolder.price.setText("￥"+mdata.get(i).getPrice()+"");
        //浏览数量
        viewHolder.num.setText("已浏览"+mdata.get(i).getBrowseNum()+"次");
        //日期
        String date = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new java.util.Date(mdata.get(i).getBrowseTime()));
        viewHolder.data.setText(date);

        Uri uri=Uri.parse(mdata.get(i).getMasterPic());
        viewHolder.icon.setImageURI(uri);

        viewHolder.zuji_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicklistener!=null)
                {
                    clicklistener.onClick(i);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,price,num,data;
        private SimpleDraweeView icon;
        private RelativeLayout zuji_linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.footr_title);
            data=itemView.findViewById(R.id.footr_data);
            price=itemView.findViewById(R.id.footr_price);
            num=itemView.findViewById(R.id.footr_num);
            icon=itemView.findViewById(R.id.footr_icon);
            zuji_linear=itemView.findViewById(R.id.zuji_linear);

        }
    }
    public Clicklistener clicklistener;

    public   void  setOnclick(Clicklistener onclick)
    {
        clicklistener=onclick;
    }
    public interface Clicklistener
    {
        void  onClick(int postion);
    }
}
