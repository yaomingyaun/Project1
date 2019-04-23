package com.bw.ymy.project.circle.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bw.ymy.project.R;
import com.bw.ymy.project.circle.bean.CircleBean;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 圈子的adapter
 * */
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {
    private List<CircleBean.ResultBean> mdata=new ArrayList<>();
    private Context mcontext;
    public CircleAdapter(Context context) {
    this. mcontext = context;

    }
    public  void  setlist(List<CircleBean.ResultBean> datas)
    {
        this.mdata=datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view=LayoutInflater.from(mcontext).inflate(R.layout.action_circle_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //描述
        viewHolder.title.setText(mdata.get(i).getContent());
        //名字
        viewHolder.name.setText((mdata.get(i).getNickName()));
        //点赞数
        viewHolder.num.setText(mdata.get(i).getGreatNum()+"");
        //图片  说
        Uri uri1=Uri.parse(mdata.get(i).getImage());
        viewHolder.icon2.setImageURI(uri1);
        //头像
        Uri uri=Uri.parse(mdata.get(i).getHeadPic());
        viewHolder.circle_tx.setImageURI(uri);
        //日期
        String date = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new java.util.Date(mdata.get(i).getCreateTime()));
        viewHolder.data.setText(date);
    final CircleBean.ResultBean bean=mdata.get(i);
        //判断是否点赞
        if(bean.getWhetherGreat()==1)
        {
            //点赞
            viewHolder.circle_dz.setImageResource(R.mipmap.common_btn_prise_s_hdpi);
        }else
        {
            //取消
            viewHolder.circle_dz.setImageResource(R.mipmap.common_btn_prise_n_hdpi);
        }

        viewHolder.circle_dz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(listener!=null)
              {
                  listener.onClick(mdata.get(i).getWhetherGreat(),i,mdata.get(i).getId());
              }
            }
        });
    }
        //点赞的方法
       public void getGivePraise(int position) {
            mdata.get(position).setWhetherGreat(1);
            mdata.get(position).setGreatNum(mdata.get(position).getGreatNum() + 1);
            notifyDataSetChanged();
        }

        //取消点赞的方法
       public void getCancelPraise(int position) {
            mdata.get(position).setWhetherGreat(2);
            mdata.get(position).setGreatNum(mdata.get(position).getGreatNum() - 1);
            notifyDataSetChanged();
        }
    @Override
    public int getItemCount() {
        return mdata.size();
    }

      class ViewHolder extends RecyclerView.ViewHolder
    {
        private    TextView title,data,name,num;
        private SimpleDraweeView circle_tx,icon2;
        private ImageView circle_dz;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             title=itemView.findViewById(R.id.cicle_title);
              data=itemView.findViewById(R.id.cicle_data);
            name=itemView.findViewById(R.id.cicle_name);
            circle_tx=itemView.findViewById(R.id.circle_tx);
            circle_dz=itemView.findViewById(R.id.circle_dz);
            icon2=itemView.findViewById(R.id.cicle_icon2);

            num=itemView.findViewById(R.id.circle_num);
        }
    }


    //接口
    private OnClickListener listener;
    public void setOnclickListener(OnClickListener listener){
        this.listener=listener;
    }

    public interface OnClickListener{
        void onClick(int whetherGreat, int i, int id);
    }
}
