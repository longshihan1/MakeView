package com.longshihan.makeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longshihan.makeview.R;


/**
 * Created by dell on 2016/8/2.
 */
public class HomeTabLayout extends LinearLayout {
    private ImageView imageView;
    private TextView textView;
    private TextView msgCount;
    private int position;

    public HomeTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HomeTabLayout);
        int image = a.getResourceId(R.styleable.HomeTabLayout_tabImage, -1);
        String text = a.getString(R.styleable.HomeTabLayout_tabText);
        position = a.getInteger(R.styleable.HomeTabLayout_tabPosition, 0);
        a.recycle();

        imageView.setImageResource(image);
        textView.setText(text);
    }

    public HomeTabLayout(Context context) {
        super(context);
        initView(context);
    }

    public HomeTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HomeTabLayout);
        int image = a.getResourceId(R.styleable.HomeTabLayout_tabImage, -1);
        String text = a.getString(R.styleable.HomeTabLayout_tabText);
        position = a.getInteger(R.styleable.HomeTabLayout_tabPosition, 0);
        a.recycle();

        imageView.setImageResource(image);
        textView.setText(text);

    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_tab_item, this, true);
        imageView = (ImageView) view.findViewById(R.id.imageview);
        textView = (TextView) view.findViewById(R.id.textview);
        textView.setTextSize(10);
        msgCount = (TextView) view.findViewById(R.id.tv_msg_count);

    }


    public void setStateNomal() {
        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.tab_home_normal);
                break;
            case 1:
                imageView.setImageResource(R.drawable.tab_shangou_normal);
                break;
            case 2:
                imageView.setImageResource(R.drawable.tab_shopping_normal);
                break;
            case 3:
                imageView.setImageResource(R.drawable.tab_user_normal);
                break;
        }
        textView.setTextColor(getResources().getColor(R.color.mainactivity_tab_text));
    }

    public void setStatePessed() {
        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.tab_home_pressed);
                break;
            case 1:
                imageView.setImageResource(R.drawable.tab_shangou_pressed);
                break;
            case 2:
                imageView.setImageResource(R.drawable.tab_shopping_pressed);
                break;
            case 3:
                imageView.setImageResource(R.drawable.tab_user_pressed);
                break;
        }
        textView.setTextColor(getResources().getColor(R.color.title_blue));
    }


    public void setMsgCount(int count) {
        if (count > 0) {
           // msgCount.setText(String.valueOf(count));
            msgCount.setVisibility(View.VISIBLE);
        } else {
            msgCount.setVisibility(View.INVISIBLE);
        }
    }
}
