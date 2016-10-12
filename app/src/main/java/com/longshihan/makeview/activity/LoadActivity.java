package com.longshihan.makeview.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.longshihan.makeview.R;
import com.longshihan.makeview.adapter.ViewPagerAdapter;
import com.longshihan.makeview.base.BaseActivity;
import com.longshihan.makeview.fragment.OneFragment;
import com.longshihan.makeview.fragment.SecondFragment;
import com.longshihan.makeview.fragment.ThirdFragment;
import com.longshihan.makeview.fragment.ThreeFragment;
import com.longshihan.makeview.view.HomeTabLayout;
import com.longshihan.makeview.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoadActivity extends BaseActivity {
    @BindView(R.id.tabmain_viewPager)
    NoScrollViewPager mTabmainViewPager;
    @BindView(R.id.tab_home)
    HomeTabLayout mTabHome;
    @BindView(R.id.tab_shopping)
    HomeTabLayout mTabShopping;
    @BindView(R.id.tab_cart)
    HomeTabLayout mTabCart;
    @BindView(R.id.tab_user)
    HomeTabLayout mTabUser;
    @BindView(R.id.main_send)
    ImageView mMainSend;

    private OneFragment mOneFragment;
    private SecondFragment mSecondFragment;
    private ThreeFragment mThreeFragment;
    private ThirdFragment mThirdFragment;
    private int[] tabids = {R.id.tab_home, R.id.tab_shopping, R.id.tab_cart, R.id.tab_user};
    private int tab_index;
    ViewPagerAdapter adapter;


    @Override
    public int getContentViewId() {
        return R.layout.activity_load;
    }


    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mTabmainViewPager.setNoScroll(true);
        initData();
        initView();
    }

    @Override
    protected String AllowPression() {
        return PERMISSIONS_STORAGE[16];
    }

    private void initData() {
        mOneFragment = new OneFragment();
        mSecondFragment = new SecondFragment();
        mThreeFragment = new ThreeFragment();
        mThirdFragment = new ThirdFragment();

    }

    private void initView() {
        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(mOneFragment);
        fragments.add(mSecondFragment);
        fragments.add(mThreeFragment);
        fragments.add(mThirdFragment);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, this);
        mTabmainViewPager.setAdapter(adapter);
    }

    @OnClick({R.id.tab_home, R.id.tab_shopping, R.id.tab_cart, R.id.tab_user, R.id.main_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                mTabmainViewPager.setCurrentItem(0);
                changeTabState(0);
                break;
            case R.id.tab_shopping:
                mTabmainViewPager.setCurrentItem(1);
                changeTabState(1);
                break;
            case R.id.tab_cart:
                mTabmainViewPager.setCurrentItem(2);
                changeTabState(2);
                break;
            case R.id.tab_user:
                mTabmainViewPager.setCurrentItem(3);
                changeTabState(3);
                break;
            case R.id.main_send:
                Toast.makeText(LoadActivity.this, "发布信息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 点击底部切换界面
     *
     * @param index
     */
    public void changeTabState(int index) {
        for (int i = 0; i < tabids.length; i++) {
            HomeTabLayout tabLayout = (HomeTabLayout) findViewById(tabids[i]);
            if (index == i) {
                tabLayout.setStatePessed();
            } else {
                tabLayout.setStateNomal();
            }
        }
        tab_index = index;
    }

    /**
     * @return 当前界面
     */
    public int getTabIndex() {
        return tab_index;
    }
}
