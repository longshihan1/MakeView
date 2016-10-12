package com.longshihan.makeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.longshihan.makeview.R;

/**
 * @author Administrator
 * @time 2016/10/10 10:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CircleViewList extends View {
    private int width;
    private int height;
    private Paint mPaintLine;
    private Paint mPaintCircle;
    private Paint mPaintSec;
    private Paint mPaintText, mPaintText2;
    public static final int NEED_INVALIDATE = 0X23;
    RectF area;
    private int mSweepValue = 270;
    private float size = 10;

    //每隔一秒，在handler中调用一次重新绘制方法
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case NEED_INVALIDATE:
                    invalidate();//告诉UI主线程重新绘制
                    //handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 1000);
                    break;
                default:
                    break;
            }
        }
    };

    public CircleViewList(Context context) {
        super(context);
    }

    public CircleViewList(Context context, AttributeSet attrs) {
        super(context, attrs);

        //正常圆弧
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.BLACK);
        mPaintLine.setStrokeWidth(7);
        mPaintLine.setAntiAlias(true);//设置是否抗锯齿
        mPaintLine.setStyle(Paint.Style.STROKE);//设置绘制风格

        mPaintCircle = new Paint();
        mPaintCircle.setColor(context.getResources().getColor(R.color.yellow));//设置颜色
        mPaintCircle.setStrokeWidth(10);//设置线宽
        mPaintCircle.setAntiAlias(true);//设置是否抗锯齿
        mPaintCircle.setStyle(Paint.Style.STROKE);//设置绘制风格


        mPaintText = new Paint();
        mPaintText.setColor(Color.WHITE);
        mPaintText.setStrokeWidth(10);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(40);

        //透明圆弧
        mPaintText2 = new Paint();
        mPaintText2.setColor(Color.YELLOW);
        mPaintText2.setStrokeWidth(7);
        mPaintText2.setAntiAlias(true);//设置是否抗锯齿
        mPaintText2.setStyle(Paint.Style.STROKE);//设置绘制风格

        //滑针
        mPaintSec = new Paint();
        mPaintSec.setStrokeWidth(10);
        mPaintSec.setColor(Color.YELLOW);


    }

    public CircleViewList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);


        int circleRadius = 200;//大圆半径
        //画出大圆
        canvas.drawCircle(width / 2, height / 2, circleRadius, mPaintCircle);
        area = new RectF((width - 2 * circleRadius) / 2 - 40, (height - 2 * circleRadius) / 2 -
                40, width / 2 + circleRadius + 40, height / 2 + circleRadius + 40);

        for (int i = 0; i <= 71; i++) {//一共有72个
            canvas.save();//保存当前画布
            canvas.rotate(360 / 72 * i, width / 2, height / 2);
            canvas.drawCircle(width / 2, (height / 2) - circleRadius - 40, 7, mPaintLine);
            canvas.restore();
        }
        mSweepValue+=size;
        canvas.drawArc(area, mSweepValue%360, size, false, mPaintLine);    //绘制圆弧


    }

    public void setProgress(float size) {
        this.size = size;
        handler.sendEmptyMessage(NEED_INVALIDATE);
    }


}
