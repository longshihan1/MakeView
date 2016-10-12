package com.longshihan.makeview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longshihan.makeview.bean.LisyBean;
import com.longshihan.makeview.R;

import java.util.List;

/**
 * @author Administrator
 * @time 2016/10/10 16:24
 * @des 横向list
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class Item0RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<LisyBean> list0;
    private int is_show;

    public Item0RecyAdapter(Context context, List<LisyBean> lists, int is_show) {
        this.context = context;
        this.list0 = lists;
        this.is_show = is_show;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_i, parent, false);
        RecyclerView.ViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder item1 = (ItemViewHolder) holder;
        item1.mTextView.setText(list0.get(position).getTitle());
        // item1.mImageView.setImageResource(getC);
        if (is_show != 1) {
            item1.mprice.setVisibility(View.VISIBLE);
            item1.mprice.setText(list0.get(position).getMoney());
        } else {
            item1.mprice.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list0.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        TextView mprice;
        LinearLayout mLinearLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.one_adapterimg);
            mTextView = (TextView) itemView.findViewById(R.id.one_adaptertxt);
            mprice = (TextView) itemView.findViewById(R.id.one_adapterprice);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linear_itemi);
        }
    }
}
