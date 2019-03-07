package com.bw.ymy.project.shopping_car.activity.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ymy.project.App.CountView;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.bean.HomeBean;
import com.bw.ymy.project.shopping_car.activity.activity.bean.GoodBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 *  yao
 *
 *
 */
public class Goods_Adapter extends RecyclerView.Adapter<Goods_Adapter.ViewHolder>{

    private List<GoodBean.ResultBean> mdata;
    private Context mcontext;


    public Goods_Adapter(Context context) {
        this.mcontext = context;
        mdata=new ArrayList<>();
    }
//加载数据
    public void setlist(List<GoodBean.ResultBean> datas) {
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
    View view=LayoutInflater.from(mcontext).inflate(R.layout.action_goods_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.title.setText(mdata.get(i).getCommodityName());
        viewHolder.price.setText("￥"+mdata.get(i).getPrice()+"");
        Uri uri=Uri.parse(mdata.get(i).getPic());
        viewHolder.icon.setImageURI(uri);
            //商品VIEW
        viewHolder.countview.setData(this,mdata,i);
        //点击勾选
        viewHolder.goods_check.setChecked(mdata.get(i).isChecked());

        //点击选择商品
        viewHolder.goods_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mdata.get(i).setChecked(isChecked);
                if(mShopCallBackListener!=null)
                {
                    mShopCallBackListener.callBack(mdata);
                }
            }
        });
        //点击删除
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdata.remove(i);
                notifyDataSetChanged();
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
        private TextView title,price,del;
        private CountView countview;
        private CheckBox goods_check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.goods_icon);
            title=itemView.findViewById(R.id.goods_title);
            price=itemView.findViewById(R.id.goods_price);
           countview=itemView.findViewById(R.id.countview);
           del=itemView.findViewById(R.id.del);
            goods_check=itemView.findViewById(R.id.goods_check);


        }
    }
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<GoodBean.ResultBean> list);
    }
}
