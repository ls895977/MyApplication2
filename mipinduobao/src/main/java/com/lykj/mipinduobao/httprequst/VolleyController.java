package com.lykj.mipinduobao.httprequst;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/10/22 0022.
 */

public class VolleyController {
    public static final String TAG = VolleyController.class.getSimpleName();
    private static volatile VolleyController mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleyController(Context context) {
        mContext = context.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public static VolleyController getInstance(Context context) {
        if (mInstance == null) {
            synchronized (VolleyController.class) {
                if (mInstance == null) {
                    mInstance = new VolleyController(context);
                }
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <t> void addToRequestQueue(Request<t> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        mRequestQueue.add(req);
    }

    public <t> void addToRequestQueue(Request<t> req) {
        req.setTag(TAG);
        mRequestQueue.add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
