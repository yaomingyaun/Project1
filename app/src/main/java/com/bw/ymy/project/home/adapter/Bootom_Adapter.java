package com.bw.ymy.project.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ymy.project.R;
import com.bw.ymy.project.home.bean.BootomBean;
import com.bw.ymy.project.home.bean.TopBean;

import java.util.ArrayList;
import java.util.List;

/**
 *  yao
 *   热销生活的adapter的
 *
 */
public class Bootom_Adapter extends RecyclerView.Adapter<Bootom_Adapter.ViewHolder>{

    private List<BootomBean.ResultBean> mdata;
    private Context mcontext;

    public Bootom_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<BootomBean.ResultBean> datas) {
       this. mdata=datas;
        notifyDataSetChanged();
    }

    public String getid(int position)
    {
        return  mdata.get(position).getId();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mcontext,R.layout.action_bootom_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    //获取标题
      viewHolder.title.setText(mdata.get(i).getName());
        //点击事件
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
            title=itemView.findViewById(R.id.bootom_title);

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
