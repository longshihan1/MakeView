package com.longshihan.makeview.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.longshihan.makeview.R;
import com.longshihan.makeview.utils.AppManager;
import com.longshihan.makeview.utils.AppUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * HistoryBean: ldh (394380623@qq.com)
 * Date: 2015-09-17
 * Time: 10:15
 * FIXME
 */
public abstract class BaseActivity extends AppCompatActivity {
    public ImageButton mTitleLeft;
    public TextView mTitle;
    public View mTitleRight, mTitleRight2;
    private TitleListener mTitleSetListener;
    //protected ViewFinder mViewFinder;
    private RelativeLayout title_container;
    private Unbinder unbinder;

    public abstract int getContentViewId();

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }


    interface TitleListener {
        public void setTitle(TextView title, ImageButton left, View right);
    }

    public void setTitleListener(TitleListener listener) {
        // setTitleListener(listener, R.layout.titlebar);
    }


    public void setTitleListener(TitleListener listener, int titleLayoutRes) {
        initTitle();
        mTitleSetListener = listener;
        mTitleSetListener.setTitle(mTitle, mTitleLeft, mTitleRight);
    }

    public void setTitleListener_RightImage(TitleListener listener, int titleLayoutRes) {
        initTitle();
        mTitleSetListener = listener;
        mTitleRight.setVisibility(View.GONE);
        mTitleRight2.setVisibility(View.VISIBLE);
        mTitleSetListener.setTitle(mTitle, mTitleLeft, mTitleRight2);
    }

    public void setTitleListener_Noback(TitleListener listener, int titleLayoutRes) {
        initTitle();
        mTitleSetListener = listener;
        mTitleLeft.setVisibility(View.GONE);
        mTitleSetListener.setTitle(mTitle, mTitleLeft, mTitleRight);
    }

    public void setTitleListener_NobackRightImage(TitleListener listener, int titleLayoutRes) {
        //initTitle();
        mTitleSetListener = listener;
        mTitleRight.setVisibility(View.GONE);
        mTitleRight2.setVisibility(View.VISIBLE);
        mTitleLeft.setVisibility(View.GONE);
        mTitleSetListener.setTitle(mTitle, mTitleLeft, mTitleRight2);
    }

    public void initTitle() {
        // title_container = (RelativeLayout) findViewById(R.id.title_container);
        // mTitleLeft = (ImageButton) findViewById(R.id.title_left);
        mTitle = (TextView) findViewById(R.id.title);
        // mTitleRight = findViewById(R.id.title_right);
        // mTitleRight2 = findViewById(R.id.ib_right);
        if (mTitleLeft != null) {
            mTitleLeft.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finishActivity();
                }
            });
        }
    }

    public void setNoTitle() {
        title_container.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        AppUtils.hideSoftInput(BaseActivity.this);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        setContentView(getContentViewId());
        unbinder = ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        initAllMembersView(savedInstanceState);
        verifyStoragePermissions(this);
    }

    protected abstract void initAllMembersView(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        // mViewFinder = null;
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }

    public void finishActivity() {
        finish();
    }

    public View setViewText(int layoutId, String value) {
        TextView v = (TextView) findViewById(layoutId);
        v.setText(value);
        return v;
    }

    public View setViewText(int layoutId, Spanned value) {
        TextView v = (TextView) findViewById(layoutId);
        v.setText(value);
        return v;
    }

    public View setViewText(int layoutId, int res) {
        TextView v = (TextView) findViewById(layoutId);
        v.setText(res);
        return v;
    }

    public View setViewClickListener(int layoutId, View.OnClickListener listener) {
        View v = findViewById(layoutId);
        v.setOnClickListener(listener);
        return v;
    }

    public View setViewVisible(int layoutId, int visibility) {
        View v = findViewById(layoutId);
        v.setVisibility(visibility);
        return v;
    }


    public View setViewImage(int layoutId, int imageId) {
        ImageView v = (ImageView) findViewById(layoutId);
        if (imageId > 0) {
            v.setImageResource(imageId);
        }
        return v;
    }

    protected void hideSoftInputView() {
        try {
            if (this.getCurrentFocus() != null) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(this.getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (mTitle != null) {
            mTitle.setText(title);
        }
    }

    public FragmentActivity getActivity() {
        return this;
    }


    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id
                    .content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }


    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE//0
            , WRITE_EXTERNAL_STORAGE//1
            , Manifest.permission.WRITE_CONTACTS//2
            , Manifest.permission.GET_ACCOUNTS//3
            , Manifest.permission.READ_CONTACTS//4
            , Manifest.permission.READ_CALL_LOG//5
            , Manifest.permission.READ_PHONE_STATE//6
            , Manifest.permission.CALL_PHONE//7
            , Manifest.permission.WRITE_CALL_LOG//8
            , Manifest.permission.USE_SIP//9
            , Manifest.permission.PROCESS_OUTGOING_CALLS//10
            , Manifest.permission.ADD_VOICEMAIL//11
            , Manifest.permission.READ_CALENDAR//12
            , Manifest.permission.WRITE_CALENDAR//13
            , Manifest.permission.CAMERA//14
            , Manifest.permission.BODY_SENSORS//15
            , Manifest.permission.ACCESS_FINE_LOCATION//16
            , Manifest.permission.ACCESS_COARSE_LOCATION//17
            , Manifest.permission.READ_EXTERNAL_STORAGE//18
            , WRITE_EXTERNAL_STORAGE//19
            , Manifest.permission.RECORD_AUDIO//20
            , Manifest.permission.READ_SMS//21
            , Manifest.permission.RECEIVE_WAP_PUSH//22
            , Manifest.permission.RECEIVE_MMS//23
            , Manifest.permission.SEND_SMS//24
    };

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    protected abstract String AllowPression();

    public void verifyStoragePermissions(AppCompatActivity activity) {
        if (AllowPression() == null) {
            return;
        }
        //访问位置的权限
        int WRITE_EXTERNAL_STORAGE = ActivityCompat.checkSelfPermission(activity, AllowPression());
        if (WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            // 我们没有权限，以提示用户
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


}
