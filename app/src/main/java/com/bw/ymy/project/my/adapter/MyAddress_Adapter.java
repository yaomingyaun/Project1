package com.bw.ymy.project.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.ymy.project.R;
import com.bw.ymy.project.my.bean.AddRessBean;
import com.bw.ymy.project.my.bean.ZujiBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * yao
 * 我的地址adapter
 * */
public class MyAddress_Adapter extends RecyclerView.Adapter<MyAddress_Adapter.ViewHolder> {

    private List<AddRessBean.ResultBean> mdata;
    private Context context;

    public MyAddress_Adapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }
    public  void  setlist(List<AddRessBean .ResultBean> datas)
    {
       this.mdata=datas;
       notifyDataSetChanged();
    }

    //获取布局
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.action_myaddress_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

      viewHolder.location_name.setText(mdata.get(i).getRealName());
      viewHolder.location_phone.setText(mdata.get(i).getPhone());
      viewHolder.location.setText(mdata.get(i).getAddress());

      viewHolder.location_del.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mdata.remove(i);
              notifyDataSetChanged();
          }
      });
      //点击修改
        viewHolder.location_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myAddListener!=null)
                {
                    myAddListener.onclick(mdata.get(i).getId(),mdata.get(i).getRealName(),mdata.get(i).getPhone(),mdata.get(i).getAddress(),mdata.get(i).getZipCode());
                }
            }
        });
        //点击
        String whetherDefault=mdata.get(i).getWhetherDefault();
        //判断默认地址
        if(Integer.parseInt(whetherDefault)==1)
        {
            viewHolder.location_moren.setChecked(true);
        }else
        {
            viewHolder.location_moren.setChecked(false);
        }

        viewHolder.location_moren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(setonclickmorenmoren!=null)
                {
                    setonclickmorenmoren.onclick(i);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        private TextView location_name,location_phone,location;
            Button location_updata,location_del;
            CheckBox location_moren;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location_name=itemView.findViewById(R.id.location_name);
            location_phone=itemView.findViewById(R.id.location_phone);
            location=itemView.findViewById(R.id.location);
            location_updata=itemView.findViewById(R.id.location_updata);
            location_del=itemView.findViewById(R.id.location_del);
            location_moren=itemView.findViewById(R.id.location_moren);

        }
    }
    //定义接口修改地址
        public  MyAddListener myAddListener;
        public  void  setonclick(MyAddListener listener)
        {
            this.myAddListener=listener;
        }
        public  interface  MyAddListener
        {
            void  onclick(int id,String l1,String l2,String l3,String l4);
        }



        public  Setonclickmoren setonclickmorenmoren;
        public  void  setOnclickmoren(Setonclickmoren onclickmoren)
        {
            this.setonclickmorenmoren=onclickmoren;

        }

        public  interface  Setonclickmoren
    {
        void  onclick(int i);
    }

}
