package com.bw.ymy.project.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.adapter.MORE_Adapter;
import com.bw.ymy.project.home.bean.MoreBean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 *   点击更多
 */


public class MoreActivity extends AppCompatActivity implements IView {

    @BindView(R.id.more_xrecyvlerview)
    XRecyclerView more_xrecyvlerview;
    MORE_Adapter more_adapter;

    private String commodityId;
    private int page=1;
    private IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        iPresenter=new IPresenter(this);


        //接受传值
        final Intent intent=getIntent();
        commodityId= intent.getStringExtra("commodityId");


        //创建布局
        lodata(commodityId);
        getMore();
        //点击进入详情
        more_adapter.setOnClickListenter(new MORE_Adapter.ClickListenter() {
            @Override
            public void onClick(int position) {
                int pid=more_adapter.getid(position);
                Intent intent1=new Intent(MoreActivity.this,Commoditytivity.class);
                intent1.putExtra("pid",pid+"");
                startActivity(intent1);
            }
        });

    }



    private void getMore() {
        //网格布局
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        more_xrecyvlerview.setLayoutManager(gridLayoutManager);

        more_adapter=new MORE_Adapter(this);
        more_xrecyvlerview.setAdapter(more_adapter);
        //上拉加载
        more_xrecyvlerview.setPullRefreshEnabled(true);
        more_xrecyvlerview.setLoadingMoreEnabled(true);

        more_xrecyvlerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                lodata(commodityId);

            }

            @Override
            public void onLoadMore() {
                lodata(commodityId);
            }
        });
        lodata(commodityId);
    }
    //加载
    private void lodata(String commodityId) {

        iPresenter.get(String.format(Apis.MORE,commodityId,page++),MoreBean.class);

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MoreBean)
        {
            MoreBean bean1= (MoreBean) data;

            if(page==1)
            {
                more_adapter.setlist(bean1.getResult());
            }else
            {
                more_adapter.setmoreadd(bean1.getResult());
            }
            page++;
            more_xrecyvlerview.refreshComplete();
            more_xrecyvlerview.loadMoreComplete();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();

    }
}
