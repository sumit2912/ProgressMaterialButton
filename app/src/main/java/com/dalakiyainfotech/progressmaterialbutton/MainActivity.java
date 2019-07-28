package com.dalakiyainfotech.progressmaterialbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressMaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setButtonText("Submit");
        button.setButtonElevation(10);
        button.setButtonCornerRadius(20);
        button.setButtonClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                button.setRefresh(true);
                button.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setRefresh(false);
                    }
                }, 5000);
                break;
        }
    }
}
