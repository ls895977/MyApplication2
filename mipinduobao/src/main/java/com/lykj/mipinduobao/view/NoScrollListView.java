package com.lykj.mipinduobao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author {GlowWorm}
 * 
 *         2016-2-5上午12:39:26
 */
public class NoScrollListView extends ListView {

	public NoScrollListView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public NoScrollListView(Context context) {
		super(context);
	}

	public NoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 1,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
