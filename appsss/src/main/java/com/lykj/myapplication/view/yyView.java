package com.lykj.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

/**
 * author：LiShan
 * Creation time：2016/12/3 0003
 */

public class yyView extends View {
    Paint point, point1;

    public yyView(Context context) {
        super(context);
        init();
    }

    public yyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public yyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        point = new Paint(Paint.ANTI_ALIAS_FLAG);
        point1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        point.setColor(Color.BLUE);
        point.setTextSize(32);
        point1.setColor(Color.RED);
        point1.setTextSize(32);
        setWriteColor();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                invalidate();
                break;
            case MotionEvent.ACTION_UP://放开

                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (myStatic) {
            canvas.drawText("哈哈哈", 0, 100, point);
        } else {
            canvas.drawText("哈哈哈", 0, 100, point1);
        }

    }

    boolean myStatic = true;
    Timer timer = new Timer(true);
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (myStatic) {
                myStatic = false;
            } else {
                myStatic = true;
            }
            Message message = handler.obtainMessage();
            handler.sendMessage(message);
        }
    };
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();

        }
    };

    public void setWriteColor() {
        timer.schedule(task, 100, 100);
    }

}
