package com.bw.ymy.project.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.App.Event;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.bean.CommBean;
import com.bw.ymy.project.home.bean.JIARUBean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.shopping_car.activity.activity.bean.AddBean;
import com.bw.ymy.project.shopping_car.activity.activity.bean.GoodBean;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SP extends Fragment implements IView {


    @BindView(R.id.banner1)
    Banner banner1;
    @BindView(R.id.comm_title)
    TextView comm_title;
    @BindView(R.id.comm_num)
    TextView comm_num;
    @BindView(R.id.comm_price)
    TextView comm_price;
    @BindView(R.id.comm_add)
    ImageView comm_add;
    @BindView(R.id.webview)
    WebView webView;
       private IPresenter iPresenter;
    private String pid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_fragment_sp, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //注册
              EventBus.getDefault().register(this);
              ButterKnife.bind(this,view);
                iPresenter=new IPresenter(this);
                //轮播图
                getBanner();
                EventBus.getDefault().postSticky(new Event(pid,"p2"));

    }
    //接受传值
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void setpid(String s)
    {
        pid=s;

    }

    public void  addGoods(List<AddBean> list)
    {
        //如果为空，直接诶添加
        if(list.size()==0)
        {
            list.add(new AddBean(Integer.valueOf(pid),1));
        }
        //不为空
        else {
            for (int i = 0; i < list.size(); i++) {
                if (Integer.valueOf(pid) == list.get(i).getCommodityId()) {
                    int count = list.get(i).getCount();
                    count++;
                    list.get(i).setCount(count);
                    break;
                } else if (i == list.size() - 1) {
                    list.add(new AddBean(Integer.valueOf(pid), 1));
                    break;
                }
            }
        }

        String s=new Gson().toJson(list);
        Map<String,String> map=new HashMap<>();
        map.put("data",s);
       iPresenter.put(Apis.Add,map,JIARUBean.class);
    }
    //点击加入购物车
    @OnClick(R.id.comm_add)
    public  void  setonclickk(View v)
    {
        switch (v.getId())
        {
            case R.id.comm_add:
                getgoods();
                    break;
                    default:break;
        }
    }

    //查询购物车数据
    private void getgoods() {
        iPresenter.get(Apis.GOODS,GoodBean.class);
    }
    private void getBanner() {
        banner1.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        iPresenter.get(String.format(Apis.COMM,pid),CommBean.class);
    }
    @Override
    public void onSuccess(Object data) {
        if (data instanceof CommBean) {
            CommBean commBean = (CommBean) data;
            List<String> list = new ArrayList<>();
            String[] splot = commBean.getResult().getPicture().split("\\,");

            for (int i = 0; i < splot.length; i++) {
                list.add(splot[i]);
            }
            banner1.setImages(list);
            //开始轮播
            banner1.start();
            comm_title.setText(commBean.getResult().getCommodityName());
            comm_num.setText("已售" + commBean.getResult().getCommentNum() + "");
            comm_price.setText("￥" + commBean.getResult().getPrice() + "");
            //显示webview
            webView.loadDataWithBaseURL(null, commBean.getResult().getDetails(), "text/html", "utf-8", null);
            //可触摸
            webView.getSettings().setBuiltInZoomControls(true);
        }
        else if(data instanceof GoodBean)
        {
            GoodBean goodBean= (GoodBean) data;
            if(goodBean.getMessage().equals("查询成功"))
            {
                List<AddBean> list=new ArrayList<>();
                List<GoodBean.ResultBean>  resultBeans=goodBean.getResult();
                for (GoodBean.ResultBean resultBean:resultBeans)
                {
                    list.add(new AddBean(resultBean.getCommodityId(),resultBean.getCount()));
                }
                addGoods(list);
            }
        }
        else  if(data instanceof JIARUBean)
        {
            JIARUBean jiaruBean= (JIARUBean) data;
            if(jiaruBean.getMessage().equals("同步成功"))
            {
                Toast.makeText(getContext(), "加入购物车成功！", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
        EventBus.getDefault().unregister(this);
    }

}
