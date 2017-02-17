package com.lykj.mipinduobao.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lykj.aextreme.afinal.adapter.MyBaseAdapter;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.CartListBean;

import java.util.List;


/**
 * 购物车item页 2016-1-23下午11:35:03
 */
public class ShopCartListViewAdapter extends MyBaseAdapter<CartListBean> {

    public ShopCartListViewAdapter(Context context, List<CartListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
		if (null == convertView) {
			convertView =inflate(R.layout.shop_cart_listview_item_layout);
			holder = new Holder(convertView);
		} else {
			holder = (Holder) convertView.getTag();
		}
        CartListBean detail = getItem(position);
        Glide.with(getContext()).load(detail.getThumb()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.shop_lv_item_img);
		String title = detail.getQishu() + detail.getTitle();
		holder.shop_lv_item_title.setText(title);
		holder.shop_lv_item_hava.setText("剩余人数" + detail.getShenyurenshu() + "人次");
		int renqi = 0;
		if (!TextUtils.isEmpty(detail.getCanyurenshu())) {
			renqi = Integer.valueOf(detail.getCanyurenshu());
		}
        holder.shop_lv_item_jian.setOnClickListener(v -> onButtonClick.setReduce(detail));
        holder.shop_num.setText(String.valueOf(detail.getNum()));
        holder.shop_lv_item_add.setOnClickListener(v -> onButtonClick.setOnAdd(detail));
		holder.shop_lv_item_duobao_person.setText("总共夺宝：" + renqi + "人次/" + detail.getYunjiage());
		holder.shop_lv_item_delete.setOnClickListener(v -> new AlertDialog.Builder(getContext()).setTitle("删除数据?").setMessage(detail.getTitle())
                .setPositiveButton("确定", (dialog, which) -> onButtonClick.setDelte(detail)).setNegativeButton("取消", null).create().show());
		return convertView;
	}

    class Holder {
        ImageButton shop_lv_item_img;
        TextView shop_lv_item_title;
        TextView shop_lv_item_hava;
        TextView shop_lv_item_duobao_person;
        TextView shop_num;
        Button shop_lv_item_add,shop_lv_item_jian;
        ImageButton shop_lv_item_delete;

        public Holder(View v) {
            shop_lv_item_img = getView(v, R.id.shop_lv_item_img);
            shop_lv_item_title = getView(v, R.id.shop_lv_item_title);
            shop_lv_item_hava = getView(v, R.id.shop_lv_item_hava);
            shop_lv_item_duobao_person = getView(v, R.id.shop_lv_item_duobao_person);
            shop_lv_item_delete = getView(v, R.id.shop_lv_item_delete);
            shop_num = getView(v, R.id.shop_num);
            shop_lv_item_jian = getView(v, R.id.shop_lv_item_jian);
            shop_lv_item_add = getView(v, R.id.shop_lv_item_add);
            v.setTag(this);
        }
    }
    private OnButtonClick onButtonClick;
    public void setonClic(OnButtonClick onButton){
        this.onButtonClick=onButton;
    }
    public  interface OnButtonClick{
        void setOnAdd(CartListBean item);
        void setReduce(CartListBean item);
        void setDelte(CartListBean item);
    }
}
