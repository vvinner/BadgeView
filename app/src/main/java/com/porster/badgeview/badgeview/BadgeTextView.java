package com.porster.badgeview.badgeview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class BadgeTextView extends TextView implements IBadgeViewImpl{

	private IBadgeView mBadgeView;
	
	public BadgeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mBadgeView=new IBadgeView(this, context,attrs);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBadgeView.onSizeChanged(w, h);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mBadgeView.draw(canvas);
	}
	@Override
	public IBadgeView setBadgeCount(int count) {
		return mBadgeView.setCount(count);
	}
	@Override
	public IBadgeView setBadgeShown(boolean isShowBadge) {
		return mBadgeView.setShown(isShowBadge);
	}
	@Override
	public IBadgeView setBadgeColor(int color) {
		return mBadgeView.setColor(color);
	}
	public IBadgeView setPaddingTop(int paddingTop) {
		return mBadgeView.setPaddingTop(paddingTop);
	}
	public IBadgeView setPaddingRight(int paddingRight) {
		return mBadgeView.setPaddingRight(paddingRight);
	}


}