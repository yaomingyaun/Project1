package com.bw.ymy.project.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ymy.project.R;
import com.bw.ymy.project.home.bean.HomeBean;
import com.bw.ymy.project.home.bean.MoreBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 *  yao
 *   点击查看更多
 *
 */
public class MORE_Adapter extends RecyclerView.Adapter<MORE_Adapter.ViewHolder>{

    private List<MoreBean.ResultBean> mdata;
    private Context mcontext;

    public MORE_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<MoreBean.ResultBean> mdatas) {
      mdata.clear();
      mdata.addAll(mdatas);
      notifyDataSetChanged();
    }
    //更多
    public void setmoreadd(List<MoreBean.ResultBean> mdatas) {

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
        View view=View.inflate(mcontext,R.layout.action_more_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //加载数据
        viewHolder.title.setText(mdata.get(i).getCommodityName());
        viewHolder.price.setText("￥"+mdata.get(i).getPrice()+"");
        Uri uri=Uri.parse(mdata.get(i).getMasterPic());
        viewHolder.icon.setImageURI(uri);
        //点击进入详情
    viewHolder.more_linear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                if(listenter!=null)
                {
                    listenter.onClick(i);
                }
        }
    });


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder
    {
        private SimpleDraweeView icon;
        private TextView title,price;
        private LinearLayout more_linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.more_icon);
            title=itemView.findViewById(R.id.more_title);
            price=itemView.findViewById(R.id.more_price);
            more_linear=itemView.findViewById(R.id.more_linear);

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
