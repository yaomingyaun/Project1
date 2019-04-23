package com.bw.ymy.project.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.App.Event;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.adapter.PLAdapter;
import com.bw.ymy.project.home.bean.PLBean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PL extends Fragment implements IView {


    @BindView(R.id.plXRecyclerView)
    XRecyclerView plXRecyclerView;
    PLAdapter plAdapter;
    IPresenter iPresenter;
    int page=1;
    String commodityId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_fragment_pl, container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //注册
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);

        iPresenter=new IPresenter(this);


        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        plXRecyclerView.setLayoutManager(layoutManager);

        plXRecyclerView.setLoadingMoreEnabled(true);
        plXRecyclerView.setPullRefreshEnabled(true);
        plAdapter=new PLAdapter(getContext());
        plXRecyclerView.setAdapter(plAdapter);
        plXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                getlodata();
            }
            @Override
            public void onLoadMore() {
                getlodata();
            }
        });
        getlodata();

    }
public  void   getlodata()
{
            iPresenter.get(String.format(Apis.PL,commodityId,page++),PLBean.class);
}
 //接受传值
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void setpid(Event event)
    {
        if(event.getName().equals("pl"))
        {
            commodityId= (String) event.getO();
        }
    }
    @Override
    public void onSuccess(Object data) {

        if(data instanceof PLBean)
        {
            PLBean bean= (PLBean) data;
            if(page==1)
            {
                plAdapter.setlist(bean.getResult());
            }else
            {
                plAdapter.setadd(bean.getResult());
            }
            page++;
            plXRecyclerView.loadMoreComplete();
            plXRecyclerView.refreshComplete();

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
        EventBus.getDefault().unregister(this);
    }
}
