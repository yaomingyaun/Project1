package com.bw.ymy.project.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.App.Event;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.bean.CommBean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XQ extends Fragment  implements IView {

    @BindView(R.id.xq_webview)
    WebView xq_webview;
    private String pid;
    IPresenter iPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_fragment_xq, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);
        iPresenter=new IPresenter(this);
        //实现详情
        iPresenter.get(String.format(Apis.COMM,pid),CommBean.class);
        EventBus.getDefault().postSticky(new Event(pid,"pl"));

    }

    //接受传值
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void setpid(Event event)
    {
        if(event.getName().equals("p2"))
        {
            pid= (String) event.getO();

        }
    }
    @Override
    public void onSuccess(Object data) {
        if (data instanceof CommBean) {
            CommBean commBean = (CommBean) data;
            //显示webview
            xq_webview.loadDataWithBaseURL(null, commBean.getResult().getDetails(), "text/html", "utf-8", null);
            //可触摸
            xq_webview.getSettings().setBuiltInZoomControls(true);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
        EventBus.getDefault().unregister(this);
    }
}
