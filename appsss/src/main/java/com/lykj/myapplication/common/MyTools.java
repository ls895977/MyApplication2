package com.lykj.myapplication.common;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/9/28 0028.
 */

public class MyTools {
    /**
     * 返回drawable
     * @param context
     * @param in
     * @return
     */
    public static Drawable getDrawable(Context context, int in) {
        Drawable drawable = context.getResources().getDrawable(in);
        return drawable;
    }
}
