package com.porster.badgeview.badgeview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BadgeLinearLayout extends LinearLayout implements IBadgeViewImpl{

	private IBadgeView mBadgeView;

	public BadgeLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
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
}