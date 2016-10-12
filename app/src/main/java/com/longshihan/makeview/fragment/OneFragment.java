package com.longshihan.makeview.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longshihan.makeview.R;
import com.longshihan.makeview.adapter.RecyListViewAdapter;
import com.longshihan.makeview.bean.LisyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    @BindView(R.id.recy)
    RecyclerView mRecy;

    private Context mContext;
    RecyListViewAdapter mRecyListViewAdapter;
    List<LisyBean> lists;
    String listss[] = new String[3];

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        lists = new ArrayList<>();
        initData();
        initView();
        return view;
    }


    private void initData() {
        for (int i = 0; i < 8; i++) {
            LisyBean lisy = new LisyBean();
            lisy.setTitle("玲" + i);
            lisy.setMoney("￥" + "12." + i);
            lists.add(lisy);
        }
        for (int i = 0; i < 3; i++) {
            listss[i] = "折" + i;
        }
    }

    private void initView() {
        mRecyListViewAdapter = new RecyListViewAdapter(mContext, listss, lists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(linearLayoutManager);
        //设置adapter
        mRecy.setAdapter(mRecyListViewAdapter);
    }
}
