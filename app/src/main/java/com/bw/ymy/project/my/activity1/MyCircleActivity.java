package com.bw.ymy.project.my.activity1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.adapter.Bootom2_Adapter;
import com.bw.ymy.project.home.bean.Bootom2Bean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.adapter.MyCircle_Adapter;
import com.bw.ymy.project.my.bean.MyCircleBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCircleActivity extends AppCompatActivity  implements IView {

    @BindView(R.id.Mycircle_XRecyclerView)
    XRecyclerView Mycircle_XRecyclerView;
    MyCircle_Adapter myCircle_adapter;
    IPresenter iPresenter;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);
        //绑定
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        //网格布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Mycircle_XRecyclerView.setLayoutManager(layoutManager);
        //创建适配器
        myCircle_adapter=new MyCircle_Adapter(this);
        Mycircle_XRecyclerView.setAdapter(myCircle_adapter);
        //上拉加载
        Mycircle_XRecyclerView.setPullRefreshEnabled(true);
        Mycircle_XRecyclerView.setLoadingMoreEnabled(true);

        Mycircle_XRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                getbootom();
            }

            @Override
            public void onLoadMore() {
                getbootom();
            }
        });
        getbootom();
    }
    public  void  getbootom()
    {

        iPresenter.get(String.format(Apis.MyCircle,page++),MyCircleBean.class);
    }
    @Override
    public void onSuccess(Object data) {
        if(data instanceof MyCircleBean)
        {
            MyCircleBean myCircleBean= (MyCircleBean) data;
            if(page==1)
            {
                myCircle_adapter.setlist(myCircleBean.getResult());
            }else
            {
                myCircle_adapter.setmoreadd(myCircleBean.getResult());
            }
            page++;
            Mycircle_XRecyclerView.refreshComplete();
            Mycircle_XRecyclerView.loadMoreComplete();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }

}