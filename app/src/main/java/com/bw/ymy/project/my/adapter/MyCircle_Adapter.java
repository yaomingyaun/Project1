package com.bw.ymy.project.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.bean.Bootom2Bean;
import com.bw.ymy.project.my.bean.MyCircleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *  yao
 *   点击查看更多
 *
 */
public class MyCircle_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MyCircleBean.ResultBean> mdata;
    private Context mcontext;

    public MyCircle_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<MyCircleBean.ResultBean> mdatas) {
      mdata.clear();
      mdata.addAll(mdatas);
      notifyDataSetChanged();
    }
    //更多
    public void setmoreadd(List<MyCircleBean.ResultBean> mdatas) {

        mdata.addAll(mdatas);
        notifyDataSetChanged();
    }
    public  int getid(int position)
    {
        return  mdata.get(position).getCommodityId();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mcontext).inflate(R.layout.action_my_circle_item,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //评论
        viewHolder1.mycircle_content.setText(mdata.get(i).getContent());
        //照片
       Uri uri=Uri.parse(mdata.get(i).getHeadPic());
       viewHolder1.mycircle_icon.setImageURI(uri);
        //时间
        String date = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new java.util.Date(mdata.get(i).getCreateTime()));
        viewHolder1.mycircle_time.setText(date);
        //赞
        viewHolder1.mycircle_greatNum.setText(mdata.get(i).getGreatNum()+"");
    }
    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder
    {
        private SimpleDraweeView mycircle_icon;
        private TextView mycircle_content,mycircle_time,mycircle_greatNum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mycircle_content=itemView.findViewById(R.id.mycircle_content);
            mycircle_icon=itemView.findViewById(R.id.mycircle_icon);
            mycircle_greatNum=itemView.findViewById(R.id.mycircle_greatNum);
            mycircle_time=itemView.findViewById(R.id.mycircle_time);


        }
    }
    //点击监听
    public ClickListenter listenter;

    public void setOnClickListenter(ClickListenter clickListenter){
        listenter=clickListenter;
    }

    public interface ClickListenter{
        void onClick(int position);
    }
}
