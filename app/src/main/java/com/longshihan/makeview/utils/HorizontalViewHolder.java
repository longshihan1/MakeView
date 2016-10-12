/*
package com.longshihan.makeview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

*/
/**
 * @author Administrator
 * @time 2016/10/10 15:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 *//*

public class HorizontalViewHolder extends BaseHolder<List<Integer>>{
    private RecyclerView hor_recyclerview;

    private List<Integer> data;

    public HorizontalViewHolder(int viewId, ViewGroup parent, int viewType) {
        super(viewId, parent, viewType);
        hor_recyclerview = (RecyclerView) itemView.findViewById(R.id.item_recyclerview);
    }

    @Override
    public void refreshData(List<Integer> data, int position) {
        this.data = data;
        ViewGroup.LayoutParams layoutParams = hor_recyclerview.getLayoutParams();
        //高度等于＝条目的高度＋ 10dp的间距 ＋ 10dp（为了让条目居中）
        layoutParams.height = screenWidth/3 + dip2px(20);
        hor_recyclerview.setLayoutParams(layoutParams);
        hor_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
        hor_recyclerview.setBackgroundResource(R.color.colorAccent);
        hor_recyclerview.setAdapter(new HorizontalAdapter());
    }

    private class HorizontalAdapter extends RecyclerView.Adapter<BaseHolder>{

        @Override
        public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(R.layout.item_x2_imageview,parent,viewType);
        }

        @Override
        public void onBindViewHolder(BaseHolder holder, int position) {
            holder.refreshData(data.get(position),position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
}
*/
