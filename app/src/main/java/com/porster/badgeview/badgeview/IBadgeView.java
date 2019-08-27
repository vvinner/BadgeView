package com.porster.badgeview.badgeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.text.TextPaint;
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
 *
 * @author Porster
 */
public class IBadgeView {

	public static final int CIRCLR_SMALL = 0;
	public static final int CIRCLR_MIDDLE = 1;
	public static final int ROUNDED_RECT = 2;
	private int DEF_COLOR = 0xffFD3737;

	private Paint mPaint;
	private Paint mTextPaint;

	private int mCount;
	/**
	 * 是否显示红点
	 */
	private boolean isShowBadge;

	/**
	 * 红点宽度
	 */
	private float mBadgeWidth;
	/**
	 * 红点高度
	 */
	private float mBadgeHeight;

	private RectF rectF;
	/**
	 * 视图宽度
	 */
	private int mViewWidth;

	/**
	 * 红点距离视图上边距
	 */
	private int mPaddingTop;
	/**
	 * 红点距离视图右边距
	 */
	private int mPaddingRight;

	/**
	 * 向左偏移
	 */
	int offSetX;

	/**
	 * 请重写
	 *
	 * @param canvas
	 */
	protected void draw(Canvas canvas) {

		if (isShowBadge) {
			canvas.drawRoundRect(rectF, mBadgeHeight / 2, mBadgeHeight / 2, mPaint);
			if (mCount > 0) {
				FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
				float baseline = (mBadgeHeight - fontMetrics.bottom - fontMetrics.top) / 2;
				canvas.drawText(mCount + "", mViewWidth - mBadgeWidth / 2 - mPaddingRight-offSetX , baseline + mPaddingTop, mTextPaint);
			}

		}
	}

	/**
	 * 请重写
	 *
	 * @param w
	 * @param h
	 */
	protected void onSizeChanged(int w, int h) {
		mViewWidth = w;
		reMeasure(false);
	}

	private View mView;
	private Context mContext;

	public IBadgeView(View view, Context mContext) {
		this.mView = view;
		this.mContext = mContext;
		init();
	}

	public IBadgeView(View view, Context mContext, AttributeSet attrs) {
		this.mView = view;
		this.mContext = mContext;

		TypedArray mArray = mContext.obtainStyledAttributes(attrs, R.styleable.BadgeView);

		mPaddingTop = mArray.getDimensionPixelOffset(R.styleable.BadgeView_badge_padding_top, 0);
		mPaddingRight = mArray.getDimensionPixelOffset(R.styleable.BadgeView_badge_padding_right, 0);
		mCount = mArray.getInteger(R.styleable.BadgeView_badge_count, 0);
		isShowBadge = mArray.getBoolean(R.styleable.BadgeView_badge_none_show, false);
		DEF_COLOR = mArray.getColor(R.styleable.BadgeView_badge_color, DEF_COLOR);
		if (mCount > 0) {
			isShowBadge = true;
		}

		init();

		mArray.recycle();
	}

	/**
	 * 设置小红点
	 *
	 * @param count
	 * @return
	 */
	public IBadgeView setCount(int count) {
		this.mCount = count;
		this.isShowBadge = count > 0;

		reMeasure(true);
		return this;
	}

	public IBadgeView setShown(boolean isShowBadge) {
		this.isShowBadge = isShowBadge;
		reMeasure(true);
		return this;
	}

	public IBadgeView setPaddingRight(int mPaddingRight) {
		this.mPaddingRight = mPaddingRight;
		return this;
	}

	public IBadgeView setPaddingTop(int mPaddingTop) {
		this.mPaddingTop = mPaddingTop;
		return this;
	}

	/**
	 * 设置颜色
	 *
	 * @param color
	 * @return
	 */
	public IBadgeView setColor(int color) {
		mPaint.setColor(color);
		return this;
	}

	private void init() {

		rectF = new RectF();

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(DEF_COLOR);

		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setFakeBoldText(true);
		mTextPaint.setTextSize(IBadgeUtils.dip2px(mContext,12));

	}

	private void reMeasure(boolean validate) {

		float textWidth = mTextPaint.measureText(mCount > 0 ? String.valueOf(mCount) : "");

		mBadgeWidth = textWidth + IBadgeUtils.dip2px(mContext,8);
		mBadgeHeight = IBadgeUtils.dip2px(mContext,15);

		int shape;
		if (mCount > 9) {
			offSetX=0;
			shape = ROUNDED_RECT;
		} else if (mCount > 0) {
			offSetX=IBadgeUtils.dip2px(mContext,5);
			shape = CIRCLR_MIDDLE;
		} else {
			offSetX=0;
			shape=CIRCLR_SMALL;
		}
		switch (shape) {
			case CIRCLR_SMALL://0
				rectF.set(mViewWidth - mBadgeWidth - mPaddingRight,
						mPaddingTop,
						mViewWidth - mPaddingRight,
						mBadgeWidth + mPaddingTop);
				break;
			case CIRCLR_MIDDLE://1-9
				rectF.set(mViewWidth - mBadgeHeight - mPaddingRight-offSetX,
						mPaddingTop,
						mViewWidth - mPaddingRight-offSetX,
						mBadgeHeight + mPaddingTop);
				break;
			case ROUNDED_RECT://10+
				rectF.set(mViewWidth - mBadgeWidth - mPaddingRight,
						mPaddingTop,
						mViewWidth - mPaddingRight,
						mBadgeHeight + mPaddingTop);
				break;
		}
		if (validate)
			mView.invalidate();
	}

}