package com.longshihan.makeview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.longshihan.makeview.bean.LisyBean;
import com.longshihan.makeview.R;
import com.longshihan.makeview.adapter.RecyListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    RecyListViewAdapter mRecyListViewAdapter;
    @BindView(R.id.recy)
    RecyclerView mRecy;

    List<LisyBean> lists;
    String listss[] = new String[3];
/*
    @BindView(R.id.de)
    CircleViewList mDe;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ButterKnife.bind(this);
        lists = new ArrayList<>();
        initData();
        initView();


    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            LisyBean lisy = new LisyBean();
            lisy.setTitle("玲" + i);
            lists.add(lisy);
        }
        for (int i = 0; i < 3; i++) {
            listss[i] = "折" + i;
        }
    }

    private void initView() {
        mRecyListViewAdapter = new RecyListViewAdapter(this, listss, lists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(linearLayoutManager);
        //设置adapter
        mRecy.setAdapter(mRecyListViewAdapter);
    }

   /* private void initView() {
        Timer timer = new Timer();
        long delay1 = 1 * 1000;
        long period1 = 1000;
        // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
        timer.schedule(new TimeTaskTest(mDe), delay1, period1);
    }*/
}
