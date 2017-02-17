package com.lykj.mipinduobao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**GridView_listview
 * @author Administrator
 *
 * 2016年1月23日上午11:20:15
 */
public class GridView_listview extends GridView {

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public GridView_listview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public GridView_listview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GridView_listview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		 int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 3,
	             MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
