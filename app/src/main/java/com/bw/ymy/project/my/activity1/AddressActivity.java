package com.bw.ymy.project.my.activity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.adapter.MyAddress_Adapter;
import com.bw.ymy.project.my.bean.AddRessBean;
import com.bw.ymy.project.my.bean.MoRenBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity implements IView {

    @BindView(R.id.newAddress)
    Button newAddress;

    @BindView(R.id.addressRecyclerView)
    RecyclerView addressRecyclerView;
    MyAddress_Adapter myAddress_adapter;
    AddRessBean addRessBean;
    IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        //创建布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressRecyclerView.setLayoutManager(layoutManager);
            //创建适配器
        myAddress_adapter=new MyAddress_Adapter(this);
        addressRecyclerView.setAdapter(myAddress_adapter);
        iPresenter.get(Apis.SHDZ,AddRessBean.class);
        //点击修改地址
        myAddress_adapter.setonclick(new MyAddress_Adapter.MyAddListener() {
            @Override
            public void onclick(int id, String l1, String l2, String l3, String l4) {

                Intent intent=new Intent(AddressActivity.this,UPaddressActivity.class);

                intent.putExtra("id",id);
                intent.putExtra("l1",l1);
                intent.putExtra("l2",l2);
                intent.putExtra("l3",l3);
                intent.putExtra("l4",l4);
                startActivity(intent);

            }
        });
        //默认地址
        myAddress_adapter.setOnclickmoren(new MyAddress_Adapter.Setonclickmoren() {
            @Override
            public void onclick(int i) {

                Map<String,String> map=new HashMap<>();
                map.put("id",addRessBean.getResult().get(i).getId()+"");
                iPresenter.post(Apis.MOREN,map,MoRenBean.class);

            }
        });
    }

    //点击事件
            @OnClick(R.id.newAddress)
       public  void  setlick(View v)
            {
          switch (v.getId())
          {
              case R.id.newAddress:
                  Intent intent=new Intent(AddressActivity.this,NewAddressActivity.class);
                  startActivity(intent);
                  break;
                  default:break;


              }

            }
    @Override
    public void onSuccess(Object data) {

        if(data instanceof  AddRessBean)
        {
             addRessBean= (AddRessBean) data;
            if(addRessBean.getMessage().equals("查询成功"))
            {
                myAddress_adapter.setlist(addRessBean.getResult());

            }
        }else if (data instanceof MoRenBean)
        {
            MoRenBean moRenBean= (MoRenBean) data;
            if(moRenBean.getMessage().equals("设置成功"))
            {
                Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
                iPresenter.get(Apis.SHDZ,AddRessBean.class);
            }else
            {
                Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
