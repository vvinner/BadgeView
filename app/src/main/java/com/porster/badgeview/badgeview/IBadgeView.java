package com.porster.badgeview.badgeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.porster.badgeview.R;


/**
 * 红点适配,可以添加到任何View上,默认依附在右上角<p>
 * 若要在xml中查看,请写入自定义属性app:badge_count<p>
 * 通过padding来扩大View的范围,通过app:badge_padding_top、right来校准红点的位置<p>
 * 必须重写:<P>
 * draw(Canvas canvas)<p>
 * onSizeChanged(int w,int h)<p>
 * @author Porster
 */
public class IBadgeView {
	public int DEF_COLOR= Color.parseColor("#FD3737");

	private Paint mPaint;
	private Paint mTextPaint;

	private int mCount;
	/**是否显示红点*/
	boolean isShowBadge;

	/**红点宽度*/
	private int mBadgeWidth;
	/**红点高度*/
	private int mBadgeHeight;
	/**红点半径*/
	private int circleRadius;
	private int dp1;

	private RectF rectF;
	/**视图宽度*/
	private int mViewWidth;
	@SuppressWarnings("unused")
	private int mViewHeight;

	/**红点距离视图上边距*/
	private int mPaddingTop;
	/**红点距离视图右边距*/
	private int mPaddingRight;


	public IBadgeView setPaddingTop(int paddingTop) {
		mPaddingTop = paddingTop;
		mView.invalidate();
		return this;
	}

	public IBadgeView setPaddingRight(int paddingRight) {
		mPaddingRight = paddingRight;
		mView.invalidate();
		return this;
	}

	/**
	 * 请重写
	 * @param canvas
	 */
	protected void draw(Canvas canvas) {
		if(isShowBadge){
			if(mCount<10){//圆
				canvas.drawCircle(mViewWidth-mBadgeWidth/2-mPaddingRight,mBadgeHeight/2+mPaddingTop,circleRadius,mPaint);
			}else{//椭圆
				rectF=new RectF(mViewWidth-mBadgeWidth-mPaddingRight,
						mPaddingTop,
						mViewWidth-mPaddingRight,
						mBadgeHeight+mPaddingTop);
				canvas.drawRoundRect(rectF,(int)(mBadgeWidth*0.6),(int)(mBadgeWidth*0.6),mPaint);
			}
			if(mCount>0){
				FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
				int baseline = (mBadgeHeight + 0 - fontMetrics.bottom - fontMetrics.top) / 2;
				canvas.drawText(mCount+"",mViewWidth-mBadgeWidth/2-mPaddingRight,baseline+mPaddingTop, mTextPaint);
			}
		}
	}
	/**
	 * 请重写
	 * @param w
	 * @param h
	 */
	protected void onSizeChanged(int w,int h) {
		mViewWidth=w;
		mViewHeight=h;
	}
	private View mView;
	private Context mContext;

	public IBadgeView(View view,Context mContext) {
		this.mView=view;
		this.mContext=mContext;
		init();
	}
	public IBadgeView(View view,Context mContext,AttributeSet attrs) {
		this.mView=view;
		this.mContext=mContext;

		TypedArray mArray=mContext.obtainStyledAttributes(attrs, R.styleable.BadgeView);

		mPaddingTop=mArray.getDimensionPixelOffset(R.styleable.BadgeView_badge_padding_top, 0);
		mPaddingRight=mArray.getDimensionPixelOffset(R.styleable.BadgeView_badge_padding_right, 0);
		mCount=mArray.getInteger(R.styleable.BadgeView_badge_count, 0);
		isShowBadge=mArray.getBoolean(R.styleable.BadgeView_badge_none_show,false);
		DEF_COLOR=mArray.getColor(R.styleable.BadgeView_badge_color,DEF_COLOR);
		if(mCount>0){
			isShowBadge=true;
		}

		init();

		mArray.recycle();
	}
	/**
	 * 设置小红点
	 * @param count
	 * @return
	 */
	public IBadgeView setCount(int count){
		this.mCount=count;
		resetCount();
		return this;
	}
	public IBadgeView setShown(boolean isShowBadge){
		this.isShowBadge=isShowBadge;
		mView.invalidate();
		return this;
	}
	/**
	 * 设置颜色
	 * @param color
	 * @return
	 */
	public IBadgeView setColor(int color){
		mPaint.setColor(color);
		mView.invalidate();
		return this;
	}
	private void init() {
		dp1=IBadgeUtils.dip2px(mContext, 1);

		mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(DEF_COLOR);

		mTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setFakeBoldText(true);

		resetCount();
	}

	private void resetCount(){
		if (mCount > 99) {
			mCount = 99;
		}

		if(mCount>=10){
			mBadgeWidth=dp1*IBadgeUtils.getRectWidthDp(mContext);
			mBadgeHeight=dp1*IBadgeUtils.getCircleDp(mContext);
		}else if(mCount>0){
			mBadgeWidth=dp1*IBadgeUtils.getCircleDp(mContext);
			mBadgeHeight=dp1*IBadgeUtils.getCircleDp(mContext);
		}else{
			mBadgeHeight=mBadgeWidth=dp1*IBadgeUtils.getNoneDp(mContext);
		}
		circleRadius=mBadgeWidth/2;

		mTextPaint.setTextSize(mBadgeHeight*0.8f);

		mView.invalidate();
	}
}