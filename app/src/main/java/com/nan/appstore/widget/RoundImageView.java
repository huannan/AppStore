package com.nan.appstore.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImageView extends ImageView
{
	private Paint mCirclePaint;
	private int length;
	private int mRadius;
	
	
	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setColor(Color.WHITE);
		mCirclePaint.setStyle(Paint.Style.STROKE);
		mCirclePaint.setStrokeWidth(3.0f);
	}

	public RoundImageView(Context context)
	{
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		length = Math.max(getMeasuredWidth(), getMeasuredHeight());
		mRadius = length/2;
	}

	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawCircle(mRadius, mRadius, mRadius-2, mCirclePaint);
	}
}