package com.example.rajat.demo.mgpl;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;

import androidx.core.widget.TextViewCompat;

import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.example.rajat.demo.R;


public class CustomPoppinsBoldTextView extends androidx.appcompat.widget.AppCompatTextView {
    private boolean isDrawing = false;
    private int strokeColor = Color.parseColor("#00000000");
    private float strokeWidth = 0f;
    Shader textShader;

    public CustomPoppinsBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomPoppinsBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CustomPoppinsBoldTextView(Context context, int strokeColor, float strokeWidth, String fontName) {
        super(context);
        try {
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public CustomPoppinsBoldTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        setIncludeFontPadding(false);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RazorpayCustomTextView);

            String fontName = a.getString(R.styleable.RazorpayCustomTextView_fontName);

            try {
                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                    setTypeface(myTypeface);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            strokeColor = a.getColor(R.styleable.RazorpayCustomTextView_ctvStrokeColor, strokeColor);
            strokeWidth = a.getFloat(R.styleable.RazorpayCustomTextView_ctvStrokeWidth, strokeWidth);
        }
        setStrokeWidth(strokeWidth);
    }

    public void setFont(String fontName) {
        try {
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrency(String currency) {
        if (currency.equalsIgnoreCase("inr")) {
//            setBackgroundResource(R.drawable.ic_inr_background);
        } else if (currency.equalsIgnoreCase("bcn")) {
//            setBackgroundResource(R.drawable.ic_bcn_background);
        }
    }

    private void setStrokeWidth(float width) {
        strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, Resources.getSystem().getDisplayMetrics());
    }


    @Override
    public void invalidate() {
        if (isDrawing) return;
        super.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (strokeWidth > 0) {
            isDrawing = true;
            TextPaint p = getPaint();
            p.setStyle(Paint.Style.FILL);
            super.onDraw(canvas);
            int currentTextColor = getCurrentTextColor();
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(strokeWidth);
            setTextColor(strokeColor);
            super.onDraw(canvas);
            setTextColor(currentTextColor);
            isDrawing = false;
        } else {
            super.onDraw(canvas);
        }
    }

    public void setGradient(int startColor, int midColor, int endColor) {
        textShader = new LinearGradient(0, 0, 0, dpToPx((int) getTextSize()),
                new int[]{startColor, midColor, endColor},
                new float[]{0, 1, 2}, Shader.TileMode.CLAMP);
        getPaint().setShader(textShader);
    }

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public void setStroke(float strokeWidth, int strokeColor) {
        setStrokeWidth(strokeWidth);
        this.strokeColor = strokeColor;
        invalidate();
    }

    public void setAutoSize(boolean autoSizing) {
        if (autoSizing) {
            TextViewCompat.setAutoSizeTextTypeWithDefaults(this, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        }
    }
}


