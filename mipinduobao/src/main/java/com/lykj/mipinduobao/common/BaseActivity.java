package com.lykj.mipinduobao.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


public abstract class BaseActivity extends com.lykj.aextreme.afinal.common.BaseActivity {
    protected InputMethodManager inputMethodManager;
    public Bundle mSavedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTranslucentStatus(false);
        mSavedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        if(!isTaskRoot()){
            Intent intent = getIntent();
            String action = intent.getAction();
            if(intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)){
                finish();
                return;
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
    /**
     * 初始化头,默认返回按钮
     * @param title 标题
     * @param right 右边图标
     */
    protected void initHeaderBack(@StringRes int title, @DrawableRes int right) {
//        setHeaderLeft(R.mipmap.icon_back);
        setHeaderRight(right);
        setTitle(title);
    }
    protected  void setTitleback(int title ){
        setTitle(title);
//        setToolbarBack(R.color.colorAccent);
    }
    /**
     * 初始化头,默认返回按钮
     * @param title 标题
     * @param right 右边文字
     */
    protected void initHeaderBackTxt(@StringRes int title, @StringRes int right) {
//        setHeaderLeft(R.mipmap.icon_back);
        setHeaderRightTxt(right);
        setTitle(title);
//        setToolbarBack(R.color.colorAccent);
    }
}
