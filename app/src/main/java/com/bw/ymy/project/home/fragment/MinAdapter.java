package com.bw.ymy.project.home.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MinAdapter extends FragmentPagerAdapter {


    String[] str = new String[]{
            "商品","详情","评论"
    };
    public MinAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i)
        {
            case  0:
                return  new SP();

            case 1:
                return  new XQ();
            default:
                return  new PL();
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }

    @Override
    public int getCount() {
        return str.length;
    }
}
