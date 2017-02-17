package com.lykj.mipinduobao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lykj.aextreme.afinal.adapter.MyBaseAdapter;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.NewsAdapterListview_base;
import com.lykj.mipinduobao.common.ComantUtils;

import java.util.List;

/**
 * 最新揭晓子页 2016年1月18日下午9:52:54
 */
public class NewsAdapter_Chlidlistview extends MyBaseAdapter<NewsAdapterListview_base.ListItemsBean> {
	Context context;
	List<NewsAdapterListview_base> newsList;

	public NewsAdapter_Chlidlistview(Context context, List<NewsAdapterListview_base.ListItemsBean> datas) {
		super(context, datas);
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflate(R.layout.newadapter_child);
			holder = new ViewHolder(convertView);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		NewsAdapterListview_base.ListItemsBean item=getItem(position);

//		holder.rel.setOnClickListener(v -> {
//            if(getIntentStatic()){
//            if (item.getId().equals("123")) {
//                Toast.makeText(context, "请稍等一下！", Toast.LENGTH_LONG).show();
//            } else {
//                if (BasePager.UserIsNo) {
//                    Intent intent = new Intent(context,
//                            New_Member_Activity.class);
//                    intent.putExtra("indext", 12);
//                    intent.putExtra("id",item.getQ_uid());
//                    context.startActivity(intent);
//                } else {
//                    Intent intent = new Intent(context,
//                            New_WebView_Nomember_Activity.class);
//                    intent.putExtra("indext", 12);
//                    intent.putExtra("id", item.getQ_uid());
//                    context.startActivity(intent);
//                }
//            }
//            } else {
//                MyToast.show(context,"请检查--您的网络---");
//            }});
//		holder.iamge1.setOnClickListener(v -> {
//
//                if (BasePager.UserIsNo) {
//                    Intent intent = new Intent(context,
//                            New_Member_Activity.class);
//                    intent.putExtra("indext", 11);
//                    intent.putExtra("id",item.getId());
//                    context.startActivity(intent);
//                } else {
//                    Intent intent = new Intent(context,
//                            New_WebView_Nomember_Activity.class);
//                    intent.putExtra("indext", 11);
//                    intent.putExtra("id",item.getId());
//                    context.startActivity(intent);
//                }
//              }else{
//                  MyToast.show(context,"请检查--您的网络---");
//              }});
		holder.tv1.setText(item.getQ_user());
		holder.tv2.setText(item.getGonumber());
		holder.tv3.setText(item.getQ_user_code());
		holder.tv4.setText(item.getQ_end_time());
		Glide.with(getContext()).load(ComantUtils.ImageUrl+item.getThumb()).placeholder(R.drawable.defalut_product_png).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iamge1);
		Glide.with(getContext()).load(ComantUtils.ImageUrl+item.getUserphoto()).placeholder(R.drawable.zuixinrentou).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image2);
		return convertView;
	}

	class ViewHolder {
		ImageView iamge1, image2;
		TextView tv1, tv2, tv3, tv4;
		RelativeLayout rel;
		public ViewHolder (View view){
			iamge1 = getView(view,R.id.showping_image);
			image2 = getView(view,R.id.showping_rentou_image);
			tv1 = getView(view,R.id.news_listview_huodezhe);
			tv2 = getView(view,R.id.news_listview_benqi);
			tv3 = getView(view,R.id.news_listview_xinyunbianma);
			tv4 = getView(view,R.id.news_listview_time);
			rel = getView(view,R.id.newapdater_child_id);
			view.setTag(this);
		}
	}
}
