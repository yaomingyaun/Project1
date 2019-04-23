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
import com.bw.ymy.project.home.bean.TopBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *   top
 *
 */
public class Top_Adapter extends RecyclerView.Adapter<Top_Adapter.ViewHolder>{

    private List<TopBean.ResultBean> mdata;
    private Context mcontext;

    public Top_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<TopBean.ResultBean> datas) {
       this. mdata=datas;
        notifyDataSetChanged();
    }

    public  String  get(int position)
    {
        return  mdata.get(position).getId();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mcontext,R.layout.action_top_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            //赋值
      viewHolder.title.setText(mdata.get(i).getName());
        //点击进入详情
      viewHolder.title.setOnClickListener(new View.OnClickListener() {
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

        private TextView title,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.top_title);

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
