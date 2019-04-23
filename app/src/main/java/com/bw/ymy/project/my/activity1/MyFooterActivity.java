package com.bw.ymy.project.my.activity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.activity.Commoditytivity;
import com.bw.ymy.project.home.activity.MoreActivity;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.adapter.MyZujiAdapter;
import com.bw.ymy.project.my.bean.ZujiBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFooterActivity extends AppCompatActivity implements IView {

    @BindView(R.id.myzuji_RecyclerView)
    RecyclerView myzuji_RecyclerView;
    private MyZujiAdapter myZujiAdapter;

    IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_footer);
        //绑定
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);

        //加载布局
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        myzuji_RecyclerView.setLayoutManager(gridLayoutManager);

        myZujiAdapter=new MyZujiAdapter(this);

        myzuji_RecyclerView.setAdapter(myZujiAdapter);
        //点击进入详情
        myZujiAdapter.setOnclick(new MyZujiAdapter.Clicklistener() {
            @Override
            public void onClick(int postion) {
                int pid=myZujiAdapter.getcommodityId(postion);
                Intent intent1=new Intent(MyFooterActivity.this,Commoditytivity.class);
                intent1.putExtra("pid",pid+"");
                startActivity(intent1);
            }
        });

        iPresenter.get(Apis.ZUji,ZujiBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        ZujiBean bean= (ZujiBean) data;
        if(bean.getMessage().equals("查询成功"))
        {
            myZujiAdapter.setlist(bean.getResult());
        }else
        {
            Toast.makeText(this, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
