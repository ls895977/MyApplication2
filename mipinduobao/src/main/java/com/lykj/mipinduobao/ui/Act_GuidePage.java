package com.lykj.mipinduobao.ui;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.adapter.Log_ViewPager;
import com.lykj.mipinduobao.common.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 引导页 2016年1月16日上午11:10:19
 */
public class Act_GuidePage extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    public static int width;
    public static int height;
    public static Act_GuidePage Logoin;
    ViewPager vp;
    List<View> list;
    int imagetu[] = {R.drawable.b, R.drawable.c, R.drawable.d};
    int pager_yeshu;

    @Override
    public int initLayoutId() {
        return R.layout.act_guidepage;
    }

    @Override
    public void initView() {
        hideHeader();
    }

    @Override
    public void initData() {
        //判断是否创建立数据库
        File file = new File(getFilesDir().getAbsolutePath() + "/etc");
        if (!file.exists()) {
            file.mkdirs();
            list = new ArrayList<>();
            for (int i = 0; i < imagetu.length; i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.log_view,
                        null);
                ImageView image = (ImageView) view
                        .findViewById(R.id.log_view_image);
//                image.setBackgroundResource(imagetu[i]);
                Glide.with(this).load(imagetu[i]).into(image);
                list.add(view);
            }
            Log_ViewPager pager = new Log_ViewPager(this, list);
            vp = (ViewPager) findViewById(R.id.logview_viewpager);
            vp.setAdapter(pager);
            vp.setOnPageChangeListener(this);
            vp.setOnTouchListener(this);
        } else {
            startAct(Act_LoadData.class);
            finish();
        }
    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        pager_yeshu = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    int x1 = 0, x2 = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (pager_yeshu == 2) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 当手指按下的时候
                x1 = (int) event.getX();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // 当手指离开的时候
                x2 = (int) event.getX();
                if (x1 - x2 > 100) {
                    startAct(Act_LoadData.class);
                    finish();
                }

            }

        }
        return false;
    }
}
