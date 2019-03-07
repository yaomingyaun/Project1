package com.bw.ymy.project.homeshow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bw.ymy.project.R;
import com.bw.ymy.project.circle.activity.Circle;
import com.bw.ymy.project.home.activity.Home;
import com.bw.ymy.project.my.activity.My;
import com.bw.ymy.project.order.activity.Order;
import com.bw.ymy.project.shopping_car.activity.activity.activity.Shopping_Car;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends FragmentActivity {
    @BindView(R.id.show_viewpage)
    ViewPager show_viewpage;

    @BindView(R.id.show_radio)
    RadioGroup show_radio;

   List<Fragment> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //绑定
        ButterKnife.bind(this);
        //添加
        list=new ArrayList<>();
        list.add(new Home());
        list.add(new Circle());
        list.add(new Shopping_Car());
        list.add(new Order());
        list.add(new My());
        //创建适配器
        show_viewpage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //滑动
        show_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case  R.id.show_home:
                        show_viewpage.setCurrentItem(0);
                        break;
                    case  R.id.show_circle:
                        show_viewpage.setCurrentItem(1);
                        break;
                    case  R.id.shoping_car:
                        show_viewpage.setCurrentItem(2);
                        break;
                    case  R.id.show_order:
                        show_viewpage.setCurrentItem(3);
                        break;
                    case  R.id.show_my:
                        show_viewpage.setCurrentItem(4);
                        break;
                        default:break;
                }
            }
        });
    }
}
