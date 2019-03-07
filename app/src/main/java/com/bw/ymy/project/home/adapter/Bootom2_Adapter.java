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
import com.bw.ymy.project.home.bean.Bootom2Bean;
import com.bw.ymy.project.home.bean.SEARCHBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 *  yao
 *   点击查看更多
 *
 */
public class Bootom2_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Bootom2Bean.ResultBean> mdata;
    private Context mcontext;

    public Bootom2_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<Bootom2Bean.ResultBean> mdatas) {
      mdata.clear();
      mdata.addAll(mdatas);
      notifyDataSetChanged();
    }
    //更多
    public void setmoreadd(List<Bootom2Bean.ResultBean> mdatas) {

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
        View view=View.inflate(mcontext,R.layout.action_bootom2_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //加载数据
        viewHolder1.title.setText(mdata.get(i).getCommodityName());
        viewHolder1.price.setText("￥"+mdata.get(i).getPrice()+"");
        Uri uri=Uri.parse(mdata.get(i).getMasterPic());
        viewHolder1.icon.setImageURI(uri);
        //点击事件
        viewHolder1.b2_linear.setOnClickListener(new View.OnClickListener() {
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
        private LinearLayout b2_linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.b2_icon);
            title=itemView.findViewById(R.id.b2_title);
            price=itemView.findViewById(R.id.b2_price);
            b2_linear=itemView.findViewById(R.id.b2_linear);


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
