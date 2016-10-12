package com.longshihan.makeview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.longshihan.makeview.R;

import java.util.List;

/**
 * @author Administrator
 * @time 2016/10/10 18:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class AdvertisePagerViewAdapter extends PagerAdapter {

    private List<ImageView> adver_images;
    private Context mContext;

    public AdvertisePagerViewAdapter(List<ImageView> adver_images, Context context) {
        this.adver_images = adver_images;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = adver_images.get(position % adver_images.size());
        view.setImageResource(R.mipmap.ic_launcher);
        container.addView(view);// 将view加入到viewPager中，这句很重要
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
