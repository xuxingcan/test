package com.xuxingcan.gradualshowpicture;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GradualShowImageView mGradualShowImageView = (GradualShowImageView) findViewById(R.id.test);
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (times) {
                    case 0:
                        mGradualShowImageView.startGradualShowAnimator(GradualShowImageView.SLIP_TOP, 3000);
                        break;
                    case 1:
                        mGradualShowImageView.startGradualShowAnimator(GradualShowImageView.SLIP_BOTTOM, 3000);
                        break;
                    case 2:
                        mGradualShowImageView.startGradualShowAnimator(GradualShowImageView.SLIP_LEFT, 3000);
                        break;
                    case 3:
                    default:
                        mGradualShowImageView.startGradualShowAnimator(GradualShowImageView.SLIP_RIGHT, 3000);
                        times = -1;
                        break;
                }
                times++;
            }
        });
    }
}
