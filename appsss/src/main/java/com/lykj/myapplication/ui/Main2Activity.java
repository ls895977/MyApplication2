package com.lykj.myapplication.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lykj.myapplication.R;
import com.lykj.myapplication.ui.fragment.TabListFragment;

/**
 * 作者：Marshon.Chen on 2016/9/8 09:43
 * 邮箱：itmarshon@163.com
 * 功能描述：slidingtab模板
 */
public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
