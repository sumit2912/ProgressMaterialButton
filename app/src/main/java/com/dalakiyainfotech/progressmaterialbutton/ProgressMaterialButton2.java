package com.dalakiyainfotech.progressmaterialbutton;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

public class ProgressMaterialButton2 extends MaterialButton {

    public ProgressBar progressBar;

    public ProgressMaterialButton2(Context context) {
        super(context);
        init(context, null);

    }

    public ProgressMaterialButton2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressMaterialButton2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.progressbar, ((Activity) context).findViewById(android.R.id.content), true);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setElevation(context.getResources().getDimensionPixelSize(R.dimen.elevation));
        progressBar.setMinimumWidth(50);
        progressBar.setMinimumHeight(50);
        Log.e(this.getClass().getSimpleName(), "isProgressBar = " + (progressBar == null));
        Log.e(this.getClass().getSimpleName(), "isProgressBar Elevation = " + progressBar.getElevation());
    }

    public void setRefresh(boolean refresh) {
        progressBar.setVisibility(refresh ? VISIBLE : GONE);
        setVisibility(refresh ? GONE : VISIBLE);
    }


}
