package com.dalakiyainfotech.progressmaterialbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ProgressListener {
    ProgressMaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setProgressListener(this);
        button.setButtonClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                button.showProgressBar(true,0);
                button.postDelayed(() -> button.showProgressBar(false,0), 5000);
                break;
        }
    }

    @Override
    public void onProgressChange(boolean isShowing, int tag) {
        switch (tag){
            case 0:
                if(!isShowing){
                    //Do your stuff
                    Log.e("Button","Progress Finished with tag "+tag);
                }
                break;
        }
    }
}
