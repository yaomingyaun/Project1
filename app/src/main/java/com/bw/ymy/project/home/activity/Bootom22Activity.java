package com.bw.ymy.project.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.adapter.Bootom2_Adapter;
import com.bw.ymy.project.home.bean.Bootom2Bean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
/** yao
 * 二级列表里面的数据
 * */
public class Bootom22Activity extends AppCompatActivity implements IView {

    @BindView(R.id.bootom2_XRecyclerView)
    XRecyclerView bootom2_XRecyclerView;
    private Bootom2_Adapter bootom2_adapter;

    IPresenter iPresenter;
    int page=1;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bootom22);
        //绑定
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        //接受传值
        Intent intent=getIntent();
         id=intent.getStringExtra("id");

        //网格布局
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        bootom2_XRecyclerView.setLayoutManager(gridLayoutManager);
        //适配器
        bootom2_adapter=new Bootom2_Adapter(this);
        bootom2_XRecyclerView.setAdapter(bootom2_adapter);
        //回调
        bootom2_adapter.setOnClickListenter(new Bootom2_Adapter.ClickListenter() {
            @Override
            public void onClick(int position) {
                int  pid=bootom2_adapter.getid(position);
                Intent intent=new Intent(Bootom22Activity.this,Commoditytivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
        //上拉加载
        bootom2_XRecyclerView.setPullRefreshEnabled(true);
        bootom2_XRecyclerView.setLoadingMoreEnabled(true);

        bootom2_XRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
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

        iPresenter.get(String.format(Apis.BOOTOM2,id,page++),Bootom2Bean.class);
    }
    @Override
    public void onSuccess(Object data) {
          if(data instanceof Bootom2Bean)
        {
            Bootom2Bean bootom2Bean= (Bootom2Bean) data;
            if(page==1)
            {
                bootom2_adapter.setlist(bootom2Bean.getResult());
            }else
            {
                bootom2_adapter.setmoreadd(bootom2Bean.getResult());
            }
            page++;
            bootom2_XRecyclerView.refreshComplete();
            bootom2_XRecyclerView.loadMoreComplete();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
