package com.lykj.mipinduobao.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lykj.aextreme.afinal.common.BaseDialog;
import com.lykj.mipinduobao.R;

/**
 * Created by Administrator on 2016/10/29 0029.
 */

public class LoadingDialog extends BaseDialog {
    private Context mContext;
    private ProgressBar mBar;
    private TextView mMessage;

    public LoadingDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dialog_loadinglayout;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
    }

    @Override
    protected void initView() {
        mBar = (ProgressBar) findViewById(R.id.bar);
        mMessage = (TextView) findViewById(R.id.message);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
    public void show(String msg) {
        super.show();
        if (mMessage != null) {
            mMessage.setText(msg);
        }
    }
    //设置进度图片
    public void setIndeterminateDrawable(int drawable) {
        mBar.setIndeterminateDrawable(mContext.getResources().getDrawable(drawable));
    }
    //设置字体颜色
    public void setTextColor(int color) {
        mMessage.setTextColor(color);
    }
}
