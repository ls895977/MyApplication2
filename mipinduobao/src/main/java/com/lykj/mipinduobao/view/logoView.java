package com.lykj.mipinduobao.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.ui.Act_GuidePage;


/**
 * 引导页view
 *  * 2016年1月16日上午10:53:11
 */
public class logoView extends View implements Runnable{
	Paint mPaint;

	Bitmap bitmap[] = new Bitmap[4];
	private int index=0;
	int image[] = { R.drawable.a, R.drawable.b, R.drawable.c,R.drawable.d  };

	public logoView(Context context) {
		super(context);
		mPaint=new Paint();
		// TODO Auto-generated constructor stub
		for (int i = 0; i <1; i++) {
			bitmap[i]=BitmapFactory.decodeResource(getResources(),image[i]);//加载图片
			//缩放图片
			bitmap[i]=Bitmap.createScaledBitmap(bitmap[i], Act_GuidePage.width, Act_GuidePage.height,true);
		}
		 new Thread(this).start();
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setAntiAlias(true);// 给Paint加上抗锯齿标志      
		canvas.drawBitmap(bitmap[index], 0, 0, mPaint);// 画位图
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			try {
				while (true) {
					Thread.sleep(3000);
					index++;
					if(index>0){
						//方案一：
//						LogViewActivity.Logoin.mainstatic();
						break;
					}
					postInvalidate();//刷新页面
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
