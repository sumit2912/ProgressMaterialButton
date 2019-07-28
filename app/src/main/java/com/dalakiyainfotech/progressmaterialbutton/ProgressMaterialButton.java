package com.dalakiyainfotech.progressmaterialbutton;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

public class ProgressMaterialButton extends RelativeLayout {

    public MaterialButton materialButton;
    public ProgressBar progressBar;
    private Context context;
    private String text;
    private int buttonWidth, buttonHeight;
    private int radius, cornerRadius;
    private ValueAnimator widthAnimator = null;
    private View.OnClickListener onClickListener;
    private RelativeLayout.LayoutParams paramsButton, paramsProgress;
    private float buttonElevation = 0;

    public ProgressMaterialButton(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressMaterialButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressMaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        paramsButton = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        materialButton = new MaterialButton(context);
        materialButton.setFocusable(false);
        materialButton.setFocusableInTouchMode(false);
        materialButton.setClickable(false);
        paramsButton.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        materialButton.setLayoutParams(paramsButton);
        materialButton.setElevation(buttonElevation);
        paramsProgress = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        progressBar = new ProgressBar(context);
        progressBar.setFocusable(false);
        progressBar.setFocusableInTouchMode(false);
        progressBar.setClickable(false);
        paramsProgress.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        progressBar.setLayoutParams(paramsProgress);
        progressBar.setVisibility(GONE);
        progressBar.setIndeterminate(true);
        addView(materialButton, 0);
        addView(progressBar, 1);
    }

    public void setButtonText(String text) {
        this.text = text;
        materialButton.setText(this.text);
    }

    public void setButtonText(int resId) {
        this.text = context.getResources().getString(resId);
        materialButton.setText(this.text);
    }

    public void setButtonElevation(float elevation) {
        this.buttonElevation = elevation;
        materialButton.setElevation(elevation);
    }

    public float getButtonElevation() {
        return buttonElevation;
    }

    public void setButtonCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        materialButton.setCornerRadius(this.cornerRadius);
    }

    public int getButtonCornerRadius() {
        return this.cornerRadius;
    }

    public void setButtonClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        setOnClickListener(this.onClickListener);
    }

    public void setRefresh(boolean refresh) {
        buttonWidth = getWidth();
        buttonHeight = getHeight();
        radius = buttonHeight;
        Log.e("Button", "Width = " + buttonWidth);
        Log.e("Button", "Height = " + buttonHeight);
        Log.e("Button", "radius = " + radius);
        if (refresh) {
            widthAnimator = ValueAnimator.ofInt(buttonWidth, buttonHeight);
            widthAnimator.setDuration(250);
            materialButton.setText("");
            this.postDelayed(() -> {
                materialButton.setCornerRadius(radius);
                progressBar.setVisibility(VISIBLE);
                progressBar.setElevation(getButtonElevation()+5);
            }, 250);
            setOnClickListener(null);
        } else {
            widthAnimator = ValueAnimator.ofInt(buttonHeight, buttonWidth);
            widthAnimator.setDuration(250);
            this.postDelayed(() -> {
                setOnClickListener(onClickListener);
                materialButton.setCornerRadius(cornerRadius);
                materialButton.setText(text);
                progressBar.setVisibility(GONE);
                progressBar.setElevation(0);
            }, 250);
        }
        if (widthAnimator != null) {
            widthAnimator.addUpdateListener(value -> {
                Log.e("Button = " + refresh, "value = " + (int) value.getAnimatedValue());
                materialButton.getLayoutParams().width = (int) value.getAnimatedValue();
                materialButton.requestLayout();
            });
            widthAnimator.start();
        }
    }
}
