package com.example.rajat.demo.mgpl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;


public class AppUtils {

    public static int getVersionCode(Context mContext) {
        if (mContext != null) {
            try {
                return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }
        return 0;
    }

    public static String getVersionName(Context mContext) {
        if (mContext != null) {
            try {
                return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }

        return "";
    }
    public static int convertPixelsToDp(Context context,int px) {
        return (int) (px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    public static int pxToDp(float px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().getDisplayMetrics());
    }

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static float dpToPxFloat(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static Integer log2(int num) {
        double result = (Math.log(num) / Math.log(2));
        if (Math.abs(result - Math.floor(result)) == 0) {
            return Double.valueOf(result).intValue();
        } else return -1;
    }
}
