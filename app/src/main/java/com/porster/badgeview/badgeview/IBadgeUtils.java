package com.porster.badgeview.badgeview;

import android.content.Context;
import android.util.DisplayMetrics;

public class IBadgeUtils {
	
	public static int dip2px(Context context,float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	/**
	 * 得到默认的红点宽高
	 * @param mContext
	 * @return
	 */
	public static  int getCircleDp(Context mContext){
		if(mContext.getResources().getDisplayMetrics().densityDpi<=DisplayMetrics.DENSITY_HIGH){
			return 10;
		}else{
			return 15;
		}
	}
	/**
	 * 得到大于10的红点宽
	 * @param mContext
	 * @return
	 */
	public static  int getRectWidthDp(Context mContext){
		if(mContext.getResources().getDisplayMetrics().densityDpi<=DisplayMetrics.DENSITY_HIGH){
			return 15;
		}else{
			return 25;
		}
	}
	/**
	 * 得到无红点的dp大小
	 * @param mContext
	 * @return
	 */
	public static  int getNoneDp(Context mContext){
		if(mContext.getResources().getDisplayMetrics().densityDpi<=DisplayMetrics.DENSITY_HIGH){
			return 6;
		}else{
			return 7;
		}
	}
}