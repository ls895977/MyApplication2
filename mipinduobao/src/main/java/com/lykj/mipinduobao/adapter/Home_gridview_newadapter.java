package com.lykj.mipinduobao.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.lykj.aextreme.afinal.adapter.MyBaseAdapter;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.Home_gridview_new;

import java.security.MessageDigest;
import java.util.List;

/**
 * 最新揭晓adapter 2016年1月17日下午11:55:52
 */
public class Home_gridview_newadapter extends MyBaseAdapter<Home_gridview_new.ListBean> {
    List<Home_gridview_new> list;
    Context context;

    public Home_gridview_newadapter(Context context, List<Home_gridview_new.ListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.home_gridview_list, null);
//			holder.image = (AsyncImageView) convertView
//					.findViewById(R.id.home_gridview_list_image);
            holder = new viewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.home_gridview_list_image);
            holder.tv = (TextView) convertView
                    .findViewById(R.id.home_gridview_list_text);
            holder.gifview = (ImageView) convertView.findViewById(R.id.home_gridview_list_gif2);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        Home_gridview_new.ListBean item = getItem(position);
        holder.tv.setText(item.getQ_username());
//        Glide.with(context).load().into(holder.gifview);
        //判断是否是设置的默认值
        if (item.getThumb().length() > 5) {
            holder.gifview.setVisibility(View.GONE);
            holder.image.setVisibility(View.VISIBLE);
            Glide.with(parent.getContext()).load(item.getThumb()).placeholder(R.drawable.appmofen).into(holder.image);
        } else {
            holder.image.setVisibility(View.GONE);
            holder.gifview.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class viewHolder {
        //		AsyncImageView image;
        ImageView image;
        TextView tv;
        ImageView gifview;
    }

    /*
     * 判断网络是否可用
     */
    public boolean getIntentStatic() {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        } else {
            // 得到网络类型
            int type = info.getType();
            if (type == ConnectivityManager.TYPE_MOBILE) {

            } else if (type == ConnectivityManager.TYPE_WIFI) {
            }
            return true;
        }
    }

    /**
     * MD5加密算法 在这里主要是为了格式化保存的图片的文件名（将Http://.........jpg 转化成不含特殊字符的文件名）
     * 加密后得到的文件名是唯一的
     *
     * @param s
     * @return
     */
    public static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
