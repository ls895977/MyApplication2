package com.lykj.mipinduobao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lykj.aextreme.afinal.adapter.MyBaseAdapter;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.HomeTableBean;

import java.util.List;

/**
 * 最新，最热的adapter 2016年1月23日上午11:33:04
 */
public class Paixu_Gridview_Adapter extends MyBaseAdapter<HomeTableBean.ListBean> {
    Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public Paixu_Gridview_Adapter(Context context, List<HomeTableBean.ListBean> datas) {
        super(context, datas);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflate(R.layout.all_gridview_layout1);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HomeTableBean.ListBean item = getItem(position);
        holder.title.setText(item.getTitle());
        holder.money.setText(item.getYunjiage());
        holder.pb.setMax(Integer.parseInt(item.getZongrenshu()));
        holder.pb.setProgress(Integer.parseInt(item.getCanyurenshu()));
        holder.money.setText("价值：￥" + item.getYunjiage());
        holder.canyu.setText(item.getCanyurenshu());
        holder.shenyu.setText(item.getZongrenshu());
        // 判断是否是设置的默认值
        if (item.getThumb().length() > 5) {
            Glide.with(context).load(item.getThumb())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
        }
        /**
         * 购物车点击事件
         */
        holder.gouwu.setOnClickListener(v -> {
            onClickNum.backNum(position);
        });

        return convertView;
    }

    class ViewHolder {
        ImageView image;
        ImageView gouwu;
        TextView title, money, canyu, shenyu;
        ProgressBar pb;
        public ViewHolder(View v) {
            image = getView(v, R.id.detail_img);
            title = getView(v, R.id.detail_info);
            money = getView(v, R.id.all_need);
            canyu = getView(v, R.id.have_products);
            shenyu = getView(v, R.id.have_products1);
            pb = getView(v, R.id.all_pb);
            gouwu = getView(v, R.id.save_iv);
            v.setTag(this);
        }
    }


    /**
     * 购物车点击事件回调接口
     */
    private OnClickNum onClickNum;
    public void setOnclickNum(OnClickNum onClickNum) {
        this.onClickNum = onClickNum;
    }
    public interface OnClickNum {
        void backNum(int position);
    }
}
