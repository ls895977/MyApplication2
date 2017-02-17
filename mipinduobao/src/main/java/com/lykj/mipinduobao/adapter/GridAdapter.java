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
import com.lykj.mipinduobao.bean.ShoPinDeatil;

import java.util.List;

/**
 * @author {GlowWorm}
 *         <p>
 *         2016-1-18下午4:36:58
 */
public class GridAdapter extends MyBaseAdapter<ShoPinDeatil.ListBean> {
    private onAddShoping onadd;
    public GridAdapter(Context context, List<ShoPinDeatil.ListBean> datas, onAddShoping onAddShoping) {
        super(context, datas);
        this.onadd=onAddShoping;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (null == convertView) {
            convertView = inflate(R.layout.all_gridview_layout);
            holder = new Holder(convertView);
        } else {
            holder = (Holder) convertView.getTag();
        }
        ShoPinDeatil.ListBean item = getItem(position);
        Glide.with(getContext()).load(item.getThumb()).placeholder(R.drawable.defalut_product_png).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.detail_img);
        String info = "(第" + item.getQishu() + "期)" + item.getTitle();
        holder.detail_info.setText(info);
        holder.all_need.setText("总需：" + item.getZongrenshu());
        holder.all_pb.setMax(Integer.valueOf(item.getZongrenshu()));
        holder.all_pb.setProgress(Integer.valueOf(item.getShenyurenshu()));
        holder.have_products.setText("剩余：" + item.getShenyurenshu());
        if (Double.valueOf(item.getYunjiage()) == 1) {
            holder.special_price.setVisibility(View.VISIBLE);
            holder.special_price.setImageResource(R.drawable.special_zero);
        } else if (Double.valueOf(item.getYunjiage()) == 10) {
            holder.special_price.setVisibility(View.VISIBLE);
            holder.special_price.setImageResource(R.drawable.special_ten);
        } else if (Double.valueOf(item.getYunjiage()) == 100) {
            holder.special_price.setVisibility(View.VISIBLE);
            holder.special_price.setImageResource(R.drawable.special_hundred);
        } else {
            holder.special_price.setVisibility(View.GONE);
        }
		holder.save_iv.setOnClickListener(v -> {
            onadd.addShoping(position);
        });
        return convertView;
    }

    class Holder {
        public ImageView special_price;
        public ImageView detail_img;
        public TextView detail_info;
        public TextView all_need;
        public ProgressBar all_pb;
        public TextView have_products;
        public ImageView save_iv;

        public Holder(View v) {
            detail_img = getView(v, R.id.detail_img);
            detail_info = getView(v, R.id.detail_info);
            all_need = getView(v, R.id.all_need);
            all_pb = getView(v, R.id.all_pb);
            have_products = getView(v, R.id.have_products);
            save_iv = getView(v, R.id.save_iv);
            special_price = getView(v, R.id.special_price);
            v.setTag(this);
        }
    }
    public interface onAddShoping{

       void addShoping(int position);
    }

}
