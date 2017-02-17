package com.lykj.mipinduobao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**Newsadapter_newchild_Listview
 * @author Administrator
 *
 * 2016年1月18日下午11:33:49
 */
public class MyListView extends ListView {

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public MyListView(Context context, AttributeSet attrs,
					  int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 */
	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
  /* (non-Javadoc)
 * @see android.widget.ListView#onMeasure(int, int)
 */
@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	 int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 3,
             MeasureSpec.AT_MOST);
	super.onMeasure(widthMeasureSpec, expandSpec);
}
}
