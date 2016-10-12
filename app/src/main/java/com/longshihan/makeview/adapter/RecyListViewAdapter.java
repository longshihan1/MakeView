package com.longshihan.makeview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.longshihan.makeview.R;
import com.longshihan.makeview.bean.LisyBean;
import com.longshihan.makeview.utils.FullyGridLayoutManager;
import com.longshihan.makeview.utils.MyLayoutManager;
import com.longshihan.makeview.view.LinearSpaceItemDecoration;

import java.util.List;

/**
 * @author Administrator
 * @time 2016/10/10 14:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class RecyListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private String[] titles;
    private List<LisyBean> list0;
    private List<LisyBean> list1;
    private List<LisyBean> list2;
    private static final int ITEM0 = 1;
    private static final int ITEM1 = 2;
    private static final int ITEM2 = 3;
    private static final int ITEM3 = 0;
    private Item0RecyAdapter adapter0;
    private Item0RecyAdapter adapter1;
    private Item0RecyAdapter adapter2;

    public RecyListViewAdapter(Context context, String[] titles, List<LisyBean> list) {
        this.context = context;
        this.titles = titles;
        this.list0 = list;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case ITEM0:
                view = mLayoutInflater.inflate(R.layout.item0, parent, false);
                holder = new Item1ViewHolder(view);
                break;
            case ITEM1:
                view = mLayoutInflater.inflate(R.layout.item1, parent, false);
                holder = new Item2ViewHolder(view);
                break;
            case ITEM2:
                view = mLayoutInflater.inflate(R.layout.item2, parent, false);
                holder = new Item3ViewHolder(view);
                break;
            case ITEM3:
                view = mLayoutInflater.inflate(R.layout.item3, parent, false);
                holder = new Item4ViewHolder(view);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == ITEM3) {
            Item4ViewHolder item4 = (Item4ViewHolder) holder;
            //设置播放时间间隔
            item4.mViewPager.setPlayDelay(3000);
            //设置透明度
            item4.mViewPager.setAnimationDurtion(1500);
            //设置适配器
            item4.mViewPager.setAdapter(new TestNormalAdapter());
            //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R
            // .drawable.point_normal));
            item4.mViewPager.setHintView(new ColorPointHintView(context, Color.YELLOW, Color
                    .WHITE));
            // item4.mViewPager.setAdapter(new AdvertisePagerViewAdapter());
        } else {
            refreshData(holder, list0, position);
        }
       /* switch (getItemViewType(position)) {
            case ITEM0:
                //加载数据
                refreshData(holder, list0, position);
                break;
            case ITEM1:
                //加载数据
                refreshData(holder, list0, position);
                break;
            case ITEM2:
                //加载数据
                refreshData(holder, list0, position);
                break;
        }*/

    }

    private void refreshData(RecyclerView.ViewHolder holder, List<LisyBean> list, int position) {
        switch (position) {
            case ITEM0:
                //adapter0.addAll(list);
                Item1ViewHolder item1 = (Item1ViewHolder) holder;
                MyLayoutManager linearLayoutManager = new MyLayoutManager(context, 1);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                int spacingInPixels = 10;
                item1.mRecyler0.addItemDecoration(new LinearSpaceItemDecoration(spacingInPixels));
                item1.mRecyler0.setLayoutManager(linearLayoutManager);
                //设置adapter
                item1.mRecyler0.setAdapter(new Item0RecyAdapter(context, list, 0));
                break;
            case ITEM1:
                //adapter1.addAll(list);
                Item2ViewHolder item2 = (Item2ViewHolder) holder;
                FullyGridLayoutManager gridLayoutManager1 = new FullyGridLayoutManager(context, 4);
                gridLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);
                gridLayoutManager1.setSmoothScrollbarEnabled(true);
                item2.mRecyler1.addItemDecoration(new LinearSpaceItemDecoration(10));
                item2.mRecyler1.setLayoutManager(gridLayoutManager1);
                //设置adapter
                item2.mRecyler1.setAdapter(new Item0RecyAdapter(context, list, 1));

                break;
            case ITEM2:
                //adapter2.addAll(list);
                Item3ViewHolder item3 = (Item3ViewHolder) holder;
                FullyGridLayoutManager linearLayoutManager2 = new FullyGridLayoutManager(context,
                        2);
                item3.mRecyler2.addItemDecoration(new LinearSpaceItemDecoration(10));
                linearLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
                linearLayoutManager2.setSmoothScrollbarEnabled(true);
                item3.mRecyler2.setLayoutManager(linearLayoutManager2);
                //设置adapter
                item3.mRecyler2.setAdapter(new Item0RecyAdapter(context, list, 2));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM3;
        } else if (position == 1) {
            return ITEM0;
        } else if (position == 2) {
            return ITEM1;
        } else if (position == 3) {
            return ITEM2;
        }
        return 0;
    }

    //item1 的ViewHolder---->listView横向
    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyler0;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            mRecyler0 = (RecyclerView) itemView.findViewById(R.id.item0_recy);
        }
    }

    //item2 的ViewHolder------->GridView (2*4)
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyler1;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mRecyler1 = (RecyclerView) itemView.findViewById(R.id.item1_recy);
        }
    }

    //item3 的ViewHolder------->瀑布流模式
    public static class Item3ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyler2;

        public Item3ViewHolder(View itemView) {
            super(itemView);
            mRecyler2 = (RecyclerView) itemView.findViewById(R.id.item2_recy);
        }
    }

    //item4 的Viewpage--------->轮播图
    public static class Item4ViewHolder extends RecyclerView.ViewHolder {
        RollPagerView mViewPager;

        public Item4ViewHolder(View itemView) {
            super(itemView);
            mViewPager = (RollPagerView) itemView.findViewById(R.id.roll_view_pager);
        }
    }
}
