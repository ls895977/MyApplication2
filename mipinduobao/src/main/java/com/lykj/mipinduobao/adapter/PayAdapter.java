package com.lykj.mipinduobao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lykj.aextreme.afinal.adapter.MyBaseAdapter;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.CartListBean;

import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/23 0023
 */

public class PayAdapter extends MyBaseAdapter<CartListBean> {

    public PayAdapter(Context context, List<CartListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=inflate(R.layout.item_pay);
            holder=new ViewHolder(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        CartListBean item=getItem(position);
        holder.title.setText(item.getTitle());
        holder.num.setText(String.valueOf(item.getNum()));
        return convertView;
    }

    class ViewHolder{
        TextView title,num;
        public ViewHolder(View v){
            title=getView(v, R.id.pay_title);
            num=getView(v,R.id.pay_num);
            v.setTag(this);
        }
    }
}
