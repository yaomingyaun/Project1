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
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *   品质生活生活的adapter的
 *
 */
public class PZSS_Adapter extends RecyclerView.Adapter<PZSS_Adapter.ViewHolder>{

    private List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> mdata;
    private Context mcontext;

    public PZSS_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> datas) {
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
        View view=View.inflate(mcontext,R.layout.action_pzss_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //加载数据，并且赋值
        viewHolder.title.setText(mdata.get(i).getCommodityName());
        viewHolder.price.setText("￥"+mdata.get(i).getPrice()+"");
        Uri uri=Uri.parse(mdata.get(i).getMasterPic());
        viewHolder.icon.setImageURI(uri);
        //点击进入详情
        viewHolder.pzss_linear.setOnClickListener(new View.OnClickListener() {
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
        private LinearLayout pzss_linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.pzss_icon);
            title=itemView.findViewById(R.id.pzss_title);
            price=itemView.findViewById(R.id.pzss_price);
            pzss_linear=itemView.findViewById(R.id.pzss_linear);

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
