package com.bw.ymy.project.home.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.App.Event;
import com.bw.ymy.project.MainActivity;
import com.bw.ymy.project.R;
import com.bw.ymy.project.home.adapter.Bootom2_Adapter;
import com.bw.ymy.project.home.adapter.Bootom_Adapter;
import com.bw.ymy.project.home.adapter.MLSS_Adapter;
import com.bw.ymy.project.home.adapter.MORE_Adapter;
import com.bw.ymy.project.home.adapter.PZSS_Adapter;
import com.bw.ymy.project.home.adapter.RXXP_Adapter;
import com.bw.ymy.project.home.adapter.SEARCH_Adapter;
import com.bw.ymy.project.home.adapter.Top_Adapter;
import com.bw.ymy.project.home.bean.Bootom2Bean;
import com.bw.ymy.project.home.bean.BootomBean;
import com.bw.ymy.project.home.bean.HomeBean;
import com.bw.ymy.project.home.bean.MoreBean;
import com.bw.ymy.project.home.bean.SEARCHBean;
import com.bw.ymy.project.home.bean.TopBean;
import com.bw.ymy.project.home.bean.XBannerBean;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Home extends Fragment implements IView {
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.RXSP_RecyclerView)
    RecyclerView RXSP_RecyclerView;
   private RXXP_Adapter rxsp_adapter;

    @BindView(R.id.MLSS_RecyclerView)
    RecyclerView MLSS_RecyclerView;
    private MLSS_Adapter mlss_adapter;

    @BindView(R.id.PZSH_RecyclerView)
    RecyclerView PZSH_RecyclerView;
    private PZSS_Adapter pzss_adapter;

    @BindView(R.id.rxsp_more)
    ImageView rxsp_more;

    @BindView(R.id.mlss_more)
    ImageView mlss_more;

    @BindView(R.id.pzsh_more)
    ImageView pzsh_more;

    @BindView(R.id.home_pop)
    ImageView home_pop;

    @BindView(R.id.home_back)
    ImageView home_back;
    @BindView(R.id.home_search)
    EditText home_search;

    @BindView(R.id.home_sousuo)
    ImageView home_sousuo;
    @BindView(R.id.home_but1)
    Button home_but1;

    @BindView(R.id.home_rxxp)
    RelativeLayout home_rxxp;
    @BindView(R.id.home_mlss)
    RelativeLayout home_mlss;
    @BindView(R.id.home_pzsh)
    RelativeLayout home_pzsh;

   private Bootom_Adapter bootom_adapter;
    private Top_Adapter top_adapter;

    PopupWindow popupWindow;
    @BindView(R.id.serach_XRecyclerView)
    XRecyclerView serach_XRecyclerView;
    private SEARCH_Adapter search_adapter;
    @BindView(R.id.mei)
    ImageView mei;
    @BindView(R.id.mei1)
    TextView mei1;

    int page=1;
    IPresenter iPresenter;
    HomeBean homeBean;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_home, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定
        ButterKnife.bind(this,view);
        iPresenter=new IPresenter(this);
        //XBanner
        iPresenter.get(Apis.XBANNER,XBannerBean.class);

        //热销新品
        getRXXP();
        //魔力时尚
        getMLSS();
        //品质生活
        getPZSH();
        gettop();
    }
    //点击查看更多
    @OnClick({R.id.rxsp_more,R.id.mlss_more,R.id.pzsh_more,R.id.home_sousuo,R.id.home_back,R.id.home_but1,R.id.home_pop})
    public  void  setmoreclick(View v)
    {
        switch (v.getId())
        {
            //点击查看更多
            case  R.id.rxsp_more:
               Intent intent=new Intent(getContext(),MoreActivity.class);
                  int commodityId= homeBean.getResult().getRxxp().getId();
                  intent.putExtra("commodityId",commodityId+"");
                 startActivity(intent);
                break;
            //点击查看更多
            case  R.id.mlss_more:
                Intent intent1=new Intent(getContext(),MoreActivity.class);
                int commodityId1= homeBean.getResult().getMlss().getId();
                intent1.putExtra("commodityId",commodityId1+"");
                startActivity(intent1);
                break;
            //点击查看更多
            case  R.id.pzsh_more:
                Intent intent2=new Intent(getContext(),MoreActivity.class);
                int commodityId2= homeBean.getResult().getPzsh().getId();
                intent2.putExtra("commodityId",commodityId2+"");
                startActivity(intent2);
                break;
        //点击搜索的
            case  R.id.home_sousuo:
                home_pop.setVisibility(View.GONE);
                home_search.setVisibility(View.VISIBLE);
                serach_XRecyclerView.setVisibility(View.VISIBLE);
                home_sousuo.setVisibility(View.GONE);
                home_back.setVisibility(View.VISIBLE);
                home_but1.setVisibility(View.VISIBLE);
                xbanner.setVisibility(View.GONE);
                home_rxxp.setVisibility(View.GONE);
                home_mlss.setVisibility(View.GONE);
                home_pzsh.setVisibility(View.GONE);
                break;
            case R.id.home_but1:
                getSearch();
               // getlodata();
                break;
                //点击返回
            case R.id.home_back:
                home_pop.setVisibility(View.VISIBLE);
                home_search.setVisibility(View.INVISIBLE);
                home_sousuo.setVisibility(View.VISIBLE);
                home_back.setVisibility(View.GONE);
                home_but1.setVisibility(View.GONE);
                xbanner.setVisibility(View.VISIBLE);
                home_rxxp.setVisibility(View.VISIBLE);
                home_mlss.setVisibility(View.VISIBLE);
                home_pzsh.setVisibility(View.VISIBLE);
                home_sousuo.setVisibility(View.VISIBLE);
                serach_XRecyclerView.setVisibility(View.GONE);
                break;
                default:break;
        }
    }
    //top 加载
    private void gettop() {

        //获取pop布局
         View view=LayoutInflater.from(getContext()).inflate(R.layout.action_pop,null,false);
          final RecyclerView top_RecyclerView = view.findViewById(R.id.top_RecyclerView);
        final RecyclerView bootom_RecyclerView = view.findViewById(R.id.bootom_RecyclerView);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
            popupWindow=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
        // 设置PopupWindow是否能响应外部点击事件
            popupWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
            popupWindow.setFocusable(true);
        // 实例化一个ColorDrawable颜色
            ColorDrawable drawable=new ColorDrawable(getContext().getResources().getColor(R.color.province_line_border));
        // 设置弹出窗体的背景
             popupWindow.setBackgroundDrawable(drawable);
            home_pop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                    attributes.alpha = 0.5f;
                    getActivity().getWindow().setAttributes(attributes);
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                    //位置
                    popupWindow.showAsDropDown(v,0,25);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    top_RecyclerView.setLayoutManager(layoutManager);
                    //适配器
                    top_adapter=new Top_Adapter(getContext());
                    top_RecyclerView.setAdapter(top_adapter);
                    iPresenter.get(Apis.TOP,TopBean.class);
                        //点击一级条目
                    top_adapter.setOnClickListenter(new Top_Adapter.ClickListenter() {
                        @Override
                        public void onClick(int position) {
                            final String pid=top_adapter.get(position);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
              layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
               bootom_RecyclerView.setLayoutManager(layoutManager);
              //适配器
            bootom_adapter=new Bootom_Adapter(getContext());
             bootom_RecyclerView.setAdapter(bootom_adapter);
              bootom_RecyclerView.setVisibility(View.VISIBLE);

              iPresenter.get(String.format(Apis.BOOTOM,pid),BootomBean.class);
              getbootom(pid);
              //点击二级条目
              bootom_adapter.setOnClickListenter(new Bootom_Adapter.ClickListenter() {
                  @Override
                  public void onClick(int position) {
                      String id=bootom_adapter.getid(position);
                      Intent intent=new Intent(getContext(),Bootom22Activity.class);
                      intent.putExtra("id",id+"");
                      startActivity(intent);
                      popupWindow.dismiss();
                      WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                      attributes.alpha = 1f;
                      getActivity().getWindow().setAttributes(attributes);
                  }
              });

                        }
                    });
                }
            });
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                    attributes.alpha = 1f;
                    getActivity().getWindow().setAttributes(attributes);
                }
            });
    }
    public  void  getbootom(String pid)
    {
        iPresenter.get(String.format(Apis.BOOTOM,pid),Bootom2Bean.class);
    }
    private void getlodata() {
        String name=home_search.getText().toString();
        if(name.length()==0)
        {
            Toast.makeText(getContext(), "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
        }else
        {
            mei.setVisibility(View.GONE);
            mei1.setVisibility(View.GONE);
            iPresenter.get(String.format(Apis.SEARCH,name,page),SEARCHBean.class);
        }
    }
    private void getSearch() {
        //网格布局
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        serach_XRecyclerView.setLayoutManager(gridLayoutManager);
        search_adapter=new SEARCH_Adapter(getContext());
        serach_XRecyclerView.setAdapter(search_adapter);
        //上拉加载
        serach_XRecyclerView.setPullRefreshEnabled(true);
        serach_XRecyclerView.setLoadingMoreEnabled(true);
        search_adapter.setOnClickListenter(new SEARCH_Adapter.ClickListenter() {
            @Override
            public void onClick(int position) {
                int  pid=search_adapter.getid(position);
                Intent intent=new Intent(getContext(),Commoditytivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
        serach_XRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
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
    };
    //品质生活
    private void getPZSH() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        PZSH_RecyclerView.setLayoutManager(gridLayoutManager);
        //适配器
        pzss_adapter=new PZSS_Adapter(getContext());
        PZSH_RecyclerView.setAdapter(pzss_adapter);
        iPresenter.get(Apis.HOME,HomeBean.class);
        //点击进入详情页
        pzss_adapter.setOnClickListenter(new PZSS_Adapter.ClickListenter() {
            @Override
            public void onClick(int position) {
                int pid=pzss_adapter.getid(position);
                Intent intent=new Intent(getContext(),Commoditytivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }
    //魔丽时尚
    private void getMLSS() {
        //创建布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        MLSS_RecyclerView.setLayoutManager(layoutManager);
        //适配器
        mlss_adapter=new MLSS_Adapter(getContext());
        MLSS_RecyclerView.setAdapter(mlss_adapter);

        iPresenter.get(Apis.HOME,HomeBean.class);
        //点击进入详情页
        mlss_adapter.setOnClickListenter(new MLSS_Adapter.ClickListenter() {
            @Override
            public void onClick(int position) {
                int pid=mlss_adapter.getid(position);
                Intent intent=new Intent(getContext(),Commoditytivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }
    //热销新品
    private void getRXXP() {
                //创建布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RXSP_RecyclerView.setLayoutManager(layoutManager);
        //适配器
        rxsp_adapter=new RXXP_Adapter(getContext());
        RXSP_RecyclerView.setAdapter(rxsp_adapter);
        iPresenter.get(Apis.HOME,HomeBean.class);
        //点击进入详情页
        rxsp_adapter.setOnClickListenter(new RXXP_Adapter.ClickListenter() {
            @Override
            public void onClick(int position) {
                int pid=rxsp_adapter.getid(position);
                Intent intent=new Intent(getContext(),Commoditytivity.class);
                  intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onSuccess(Object data) {
        //XBanner
        if(data instanceof XBannerBean)
        {
            XBannerBean xBannerBean= (XBannerBean) data;
            xbanner.setData(xBannerBean.getResult(),null);
            xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    XBannerBean.ResultBean bean= (XBannerBean.ResultBean) model;
                    Glide.with(getActivity()).load(bean.getImageUrl()).into((ImageView) view);
                    xbanner.setPageChangeDuration(1000);
                }
            });
        }
        else  if(data instanceof HomeBean)
        {
             homeBean= (HomeBean) data;
            //热销新品
            rxsp_adapter.setlist(homeBean.getResult().getRxxp().getCommodityList());
            //魔丽时尚
            mlss_adapter.setlist(homeBean.getResult().getMlss().getCommodityList());
            //品质生活
            pzss_adapter.setlist(homeBean.getResult().getPzsh().getCommodityList());
        }
        else if(data instanceof SEARCHBean)
        {
            SEARCHBean searchBean= (SEARCHBean) data;
            List<SEARCHBean.ResultBean> result = searchBean.getResult();
            if (result.isEmpty())
            {
                mei.setVisibility(View.VISIBLE);
               mei1.setVisibility(View.VISIBLE);
            }else {
                if (page == 1) {
                    search_adapter.setlist(result);
                } else {
                    search_adapter.setmoreadd(result);
                }
                page++;
                serach_XRecyclerView.refreshComplete();
                serach_XRecyclerView.loadMoreComplete();
            }

        }
        else  if(data instanceof TopBean)
        {
            TopBean topBean= (TopBean) data;

            top_adapter.setlist(topBean.getResult());
        }
        else  if(data instanceof BootomBean)
        {
            BootomBean bootomBean= (BootomBean) data;

            bootom_adapter.setlist(bootomBean.getResult());
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
