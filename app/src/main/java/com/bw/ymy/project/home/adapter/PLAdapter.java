package com.bw.ymy.project.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.R;
import com.bw.ymy.project.home.bean.PLBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PLAdapter extends RecyclerView.Adapter<PLAdapter.ViewHolder>{

    private List<PLBean.ResultBean> mdata;
    private Context context;

    public PLAdapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }

    public  void  setlist(List<PLBean.ResultBean> list)
    {

        mdata.clear();
        mdata.addAll(list);
        notifyDataSetChanged();

    }
    public  void  setadd(List<PLBean.ResultBean> list)
    {


        mdata.addAll(list);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.action_pl_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.pl_name.setText(mdata.get(i).getNickName());

        String date = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new java.util.Date(mdata.get(i).getCreateTime()));
        viewHolder.pl_data.setText(date);

        Uri uri=Uri.parse(mdata.get(i).getImage());
        viewHolder.pl_icon2.setImageURI(uri);

        viewHolder.pl_pl.setText(mdata.get(i).getContent());

        Uri uri1=Uri.parse(mdata.get(i).getHeadPic());
        viewHolder.pl_tx.setImageURI(uri1);


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView pl_tx,pl_icon2;
        private TextView pl_name,pl_data,pl_pl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pl_tx=itemView.findViewById(R.id.pl_tx);
            pl_icon2=itemView.findViewById(R.id.pl_icon2);
            pl_name=itemView.findViewById(R.id.pl_name);
            pl_data=itemView.findViewById(R.id.pl_data);
            pl_pl=itemView.findViewById(R.id.pl_pl);

        }
    }
}
