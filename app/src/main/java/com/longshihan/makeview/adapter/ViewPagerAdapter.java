package com.longshihan.makeview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Administrator
 * @time 2016/10/11 17:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Dat$
 * @updateDes ${TODO}
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> mFragments, Context mContext) {
        super(fm);
        this.mFragments = mFragments;
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


}
