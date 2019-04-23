package com.bw.ymy.project.circle.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.circle.adapter.CircleAdapter;
import com.bw.ymy.project.circle.bean.CircleBean;
import com.bw.ymy.project.circle.bean.ZanBean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Circle extends Fragment implements IView {

    @BindView(R.id.circleRecyclerView)
    RecyclerView circleRecyclerView;
    private CircleAdapter circleAdapter;
    private  int position;
    IPresenter iPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_circle, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定
        ButterKnife.bind(this,view);
        iPresenter=new IPresenter(this);
        //布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理
        circleRecyclerView.setLayoutManager(layoutManager);
        //适配器
        Circlelodata();
        circleAdapter = new CircleAdapter(getContext());
        circleRecyclerView.setAdapter(circleAdapter);
        //点赞
        circleAdapter.setOnclickListener(new CircleAdapter.OnClickListener() {
            @Override
            public void onClick(int whetherGreat, int i, int id) {
                position = i;
                if (whetherGreat == 1)
                {
                    cancelGreatData(id)   ;
                }else
                {
                    getGreatData(id);
                }
            }
        });
    }
//获取点赞信息 点赞
    public void getGreatData(int id){
        Map<String,String> map=new HashMap<>();
        map.put("circleId",id+"");
        iPresenter.post(Apis.DZ,map,ZanBean.class);
    }
    //取消点赞
    public void cancelGreatData(int id){
        iPresenter.delete(String.format(Apis.Cancel,id),ZanBean.class);
    }

    private void Circlelodata() {

        iPresenter.get(Apis.quznzi,CircleBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CircleBean)
        {
            CircleBean bean= (CircleBean) data;

            circleAdapter.setlist(bean.getResult());
        }else {
            if (data instanceof ZanBean) {
                ZanBean zanBean = (ZanBean) data;
                if (zanBean.getMessage().equals("请先登录")) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                } else if (zanBean.getMessage().equals("点赞成功")) {
                    Toast.makeText(getContext(), "点赞成功", Toast.LENGTH_SHORT).show();
                    circleAdapter.getGivePraise(position);
                } else if (zanBean.getMessage().equals("取消成功")) {
                    Toast.makeText(getContext(), "取消成功", Toast.LENGTH_SHORT).show();
                    circleAdapter.getCancelPraise(position);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
