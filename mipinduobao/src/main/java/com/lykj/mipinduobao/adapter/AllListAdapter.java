package com.lykj.mipinduobao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lykj.aextreme.afinal.adapter.MyBaseAdapter;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.AllListBean;

import java.util.List;

/**
 * Created by lishan on 2017/1/15.
 */

public class AllListAdapter extends MyBaseAdapter<AllListBean.ListBean> {
    private OnCheageStats oncheage;

    public AllListAdapter(Context context, List<AllListBean.ListBean> datas, OnCheageStats onCheageStats) {
        super(context, datas);
        this.oncheage = onCheageStats;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if (convertView == null) {
            convertView = inflate(R.layout.item_alllist);
            v = new ViewHolder(convertView);
        } else {
            v = (ViewHolder) convertView.getTag();
        }
        v.myLiner.removeAllViews();
        AllListBean.ListBean item = getItem(position);
        v.shopName.setText(item.getName());
        v.shopName.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        v.shopName.setTextColor(getContext().getResources().getColor(R.color.title_back));
        if (item.getListzi() != null && item.getListzi().size() > 0 && item.isStata() == true) {
            Debug.e("哈俣====>"+item.getListzi().size()+"==========="+item.isStata());

            v.shopName.setBackgroundColor(getContext().getResources().getColor(R.color.all_item0));
            v.shopName.setTextColor(getContext().getResources().getColor(R.color.white));
            for (int i = 0; i < item.getListzi().size(); i++) {//循环多个二级目录
                addView(v.myLiner, item.getListzi().get(i).getName(), item.getListzi().get(i).getCateid(), i, item);//添加二级目录
                if (item.getListzi().get(i).getListsun() != null && item.getListzi().get(i).getListsun().size() > 0 && item.getListzi().get(i).isZiStatas() == true) {
                    for (int j = 0; j < item.getListzi().get(i).getListsun().size(); j++) {//循环多个三级目录
                        AllListBean.ListBean.ListziBean.ListsunBean item1 = item.getListzi().get(i).getListsun().get(j);
                        addViewSun(v.myLiner, item1.getName(), item1.getCateid(), j);//添加三级目录
                    }
                    oncheage.setOnZiStats(i);//控制三级目录开关
                }
            }
            oncheage.setOnstats();
        }
        return convertView;
    }

    public void addView(LinearLayout ll, String name, String id, int forID, AllListBean.ListBean item) {
        View view = inflate(R.layout.item_alllist);
        TextView shopName = getView(view, R.id.list_name);
        shopName.setBackgroundColor(getContext().getResources().getColor(R.color.all_item2));
        shopName.setTextColor(getContext().getResources().getColor(R.color.white));
        shopName.setText(name);
        shopName.setTag(id);
        shopName.setOnClickListener(v -> {
                if(item.getListzi().get(forID).getListsun().size()>0){//有三级目录二级目录不可以被点击
                    return;
                }
            oncheage.backOnclickView(v, forID);
        });
        ll.addView(view);
    }

    public void addViewSun(LinearLayout ll, String name, String id, int forID) {
        View view = inflate(R.layout.item_alllist);
        TextView shopName = getView(view, R.id.list_name);
        shopName.setBackgroundColor(getContext().getResources().getColor(R.color.all_item3));
        shopName.setTextColor(getContext().getResources().getColor(R.color.white));
        shopName.setText(name);
        shopName.setTag(id);
        shopName.setOnClickListener(v -> {
            oncheage.backSanOnclickView(v, forID);
        });
        ll.addView(view);
    }

    class ViewHolder {
        LinearLayout myLiner;
        TextView shopName;

        public ViewHolder(View view) {
            myLiner = getView(view, R.id.allListItem);
            shopName = getView(view, R.id.list_name);
            view.setTag(this);
        }

    }

    public interface OnCheageStats {
        void setOnstats();

        void backOnclickView(View view, int forID);

        void backSanOnclickView(View view, int forID);

        void setOnZiStats(int i);
    }
}
