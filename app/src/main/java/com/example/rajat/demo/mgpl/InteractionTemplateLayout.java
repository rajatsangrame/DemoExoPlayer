package com.example.rajat.demo.mgpl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.rajat.demo.R;
import com.example.rajat.demo.model.Gfs;


/**
 * Created by Sourabh Gupta on 26/12/19.
 */
public class InteractionTemplateLayout extends FrameLayout {

    private static final String TAG = "InteractionTemplateLayo";
    int templateId = 1;
    int colorId = 1;
    String font = "";
    CustomPoppinsBoldTextView quesEditText;
    CustomPoppinsBoldTextView firstOptionEditText;
    CustomPoppinsBoldTextView secondOptionEditText;

    public InteractionTemplateLayout(@NonNull Context context) {
        super(context);
    }

    public InteractionTemplateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InteractionTemplateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InteractionTemplateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void addGf(Gfs gif) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        CustomPoppinsBoldTextView textView = (CustomPoppinsBoldTextView) layoutInflater.inflate(R.layout.inetraction_template_layout, null);
        int width = (int) (gif.getW() / 100f * App.getScreenWidth());
        int height = (int) (gif.getH() / 100f * App.getScreenHeight());
        LayoutParams layoutParams = new LayoutParams(width, height);
//        layoutParams.leftMargin = (int)((MyApplication.screenWidth*gif.getX())/100f);
//        layoutParams.topMargin = (int)((MyApplication.screenHeight*gif.getY())/100f);

        textView.setLayoutParams(layoutParams);
        textView.setPivotX(width / 2f);
        textView.setPivotY(height / 2f);
//        textView.setCursorVisible(false);
        textView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gif.getNxt() != -1) {
//                    if (gestureDetector.onTouchEvent(event)) {
//                        if (interactiveItemClickListener != null) {
//                            interactiveItemClickListener.interactiveItemClicked(gif.getNxt());
//                        }
//                    }
                    v.clearAnimation();
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            v.animate().scaleX(0.93f).scaleY(0.93f).setDuration(50).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            v.animate().scaleX(1f).scaleY(1f).setDuration(50).withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    if (interactiveItemClickListener != null) {
                                        interactiveItemClickListener.interactiveItemClicked(gif.getNxt(), gif.getTrackId());
                                    }
                                }
                            }).start();
                            break;
                    }
                }
                return true;
            }
        });
        if (gif.getNxt() == -1) {
//            textView.setMaxTextSize(AppUtils.dpToPx(70));
//            textView.setMinTextSize(AppUtils.dpToPx(18));
            switch (templateId) {

                case 0:
                    textView.setBackgroundResource(R.drawable.ic_template_one_ques_background);
                    break;
                case 1:
                    textView.setBackgroundResource(R.drawable.ic_template_two_ques_background);
                    break;
                case 2:
                    textView.setBackgroundResource(R.drawable.ic_template_three_ques_background);
                    break;
            }
            textView.setPadding(AppUtils.dpToPx(28), AppUtils.dpToPx(15), AppUtils.dpToPx(28), AppUtils.dpToPx(15));
        } else {
//            textView.setMaxTextSize(AppUtils.dpToPx(22));
//            textView.setMinTextSize(AppUtils.dpToPx(16));
            textView.setPadding(AppUtils.dpToPx(16), 0, AppUtils.dpToPx(16), 0);
            textView.setBackgroundDrawable(getTemplateBackground(Color.parseColor(gif.getColor())));
        }
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (gif.getFontSize() * App.getScreenWidth()) / 100);
                textView.setText(gif.getTxt());
                textView.setX((App.getScreenWidth() * gif.getX()) / 100);
                textView.setY((App.getScreenHeight() * gif.getY()) / 100);
                textView.setRotation(gif.getRotation());
                textView.setScaleX(gif.getScale());
                textView.setScaleY(gif.getScale());
                Log.e("padding_abc", quesEditText.getPaddingLeft() + "");
            }
        });
        textView.setFont(this.font);
        if (gif.getNxt() == -1) {
            quesEditText = textView;
        } else if (gif.getNxt() == 1) {
            firstOptionEditText = textView;
        } else if (gif.getNxt() == 2) {
            secondOptionEditText = textView;
        }

        addView(textView);

        Log.e("edittext_w", (gif.getW() * App.getScreenWidth()) / 100 + "");
        Log.e("edittext_h", ((gif.getH() * App.getScreenHeight()) / 100) + "");
    }

    InteractiveItemClickListener interactiveItemClickListener;

    public void setInteractiveItemClickListener(InteractiveItemClickListener interactiveItemClickListener) {
        this.interactiveItemClickListener = interactiveItemClickListener;
    }

    public void setTemplateId(int templateID) {
        this.templateId = templateID;
    }

    public interface InteractiveItemClickListener {
        void interactiveItemClicked(int position, String trackId);
    }


//    public void setColorId(int colorId) {
//        this.colorId = colorId;
//    }

    public void setFont(String font) {
        this.font = font;
    }

    //    public void changeFont(YovoEditText editText) {
//        String font = getContext().getString(R.string.catamaranThin);
//        editText.setFont(font);
//    }
    public void changeFont(String font) {
        quesEditText.setFont(font);
        firstOptionEditText.setFont(font);
        secondOptionEditText.setFont(font);
    }

    public void setTemplate(int templateId) {
        this.templateId = templateId;
        switch (templateId) {
            case 0:
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).gravity = Gravity.LEFT;
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).leftMargin = AppUtils.dpToPx(24);
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).topMargin = AppUtils.dpToPx(337);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).gravity = Gravity.RIGHT;
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).topMargin = AppUtils.dpToPx(337);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).rightMargin = AppUtils.dpToPx(24);
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).width = AppUtils.dpToPx(138);
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).height = AppUtils.dpToPx(73);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).width = AppUtils.dpToPx(138);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).height = AppUtils.dpToPx(73);
                quesEditText.setBackgroundResource(R.drawable.ic_template_one_ques_background);
                secondOptionEditText.post(new Runnable() {
                    @Override
                    public void run() {
                        firstOptionEditText.setPadding(AppUtils.dpToPx(16), AppUtils.dpToPx(0), AppUtils.dpToPx(16), AppUtils.dpToPx(0));
                        secondOptionEditText.setPadding(AppUtils.dpToPx(16), AppUtils.dpToPx(0), AppUtils.dpToPx(16), AppUtils.dpToPx(0));
                    }
                });
                break;
            case 1:
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).topMargin = AppUtils.dpToPx(336);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).topMargin = AppUtils.dpToPx(428);
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).width = AppUtils.dpToPx(209);
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).height = AppUtils.dpToPx(70);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).width = AppUtils.dpToPx(209);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).height = AppUtils.dpToPx(70);
                quesEditText.setBackgroundResource(R.drawable.ic_template_two_ques_background);
                secondOptionEditText.post(new Runnable() {
                    @Override
                    public void run() {
                        firstOptionEditText.setPadding(AppUtils.dpToPx(16), AppUtils.dpToPx(0), AppUtils.dpToPx(16), AppUtils.dpToPx(20));
                        secondOptionEditText.setPadding(AppUtils.dpToPx(16), AppUtils.dpToPx(0), AppUtils.dpToPx(16), AppUtils.dpToPx(20));
                        quesEditText.setPadding(AppUtils.dpToPx(28), AppUtils.dpToPx(15), AppUtils.dpToPx(28), AppUtils.dpToPx(15));
                    }
                });
                break;
            case 2:
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).topMargin = AppUtils.dpToPx(338);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).topMargin = AppUtils.dpToPx(423);
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).width = AppUtils.dpToPx(180);
//                ((LayoutParams)firstOptionEditText.getLayoutParams()).height = AppUtils.dpToPx(50);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).width = AppUtils.dpToPx(180);
//                ((LayoutParams)secondOptionEditText.getLayoutParams()).height = AppUtils.dpToPx(50);
                quesEditText.setBackgroundResource(R.drawable.ic_template_three_ques_background);
                secondOptionEditText.post(new Runnable() {
                    @Override
                    public void run() {
                        firstOptionEditText.setPadding(AppUtils.dpToPx(8), AppUtils.dpToPx(0), AppUtils.dpToPx(8), AppUtils.dpToPx(0));
                        secondOptionEditText.setPadding(AppUtils.dpToPx(8), AppUtils.dpToPx(0), AppUtils.dpToPx(8), AppUtils.dpToPx(0));
                        quesEditText.setPadding(AppUtils.dpToPx(16), AppUtils.dpToPx(0), AppUtils.dpToPx(16), AppUtils.dpToPx(0));
                    }
                });
                break;
        }
    }
//    public void changeColor(int colorId) {
//        this.colorId = colorId;
//        ColorTemplate colorTemplate = TemplateModel.getColorTemplate(colorId);
//        firstOptionEditText.setBackgroundDrawable(getTemplateBackground(Color.parseColor(colorTemplate.getFirstButtonColor())));
//        secondOptionEditText.setBackgroundDrawable(getTemplateBackground(Color.parseColor(colorTemplate.getSecondButtonColor())));
//    }

    public Drawable getTemplateBackground(int buttonColor) {
        Drawable layer1;
        Drawable layer2;
        layer1 = getResources().getDrawable(R.drawable.ic_template_one_colored_sample);
        layer2 = getResources().getDrawable(R.drawable.ic_template_one_button_overlay);
        switch (templateId) {
            case 0:
                layer1 = getResources().getDrawable(R.drawable.ic_template_one_colored_sample);
                layer2 = getResources().getDrawable(R.drawable.ic_template_one_button_overlay);
                break;
            case 1:
                layer1 = getResources().getDrawable(R.drawable.ic_template_two_colored_background);
                layer2 = getResources().getDrawable(R.drawable.ic_template_two_overlay);
                break;
            case 2:
                layer1 = getResources().getDrawable(R.drawable.ic_template_three_colored_background);
                layer2 = getResources().getDrawable(R.drawable.ic_template_three_overlay);
                break;
        }
        Drawable drw1 = layer1.getConstantState().newDrawable().mutate();
        drw1.setTint(buttonColor);
        Drawable drw2 = layer2.getConstantState().newDrawable().mutate();
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]
                {drw1, drw2});
        return layerDrawable;
    }
}
