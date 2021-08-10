package com.example.studybudy;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

//Got from youtube tutorial and splash beginner splash screen creation and customized for application
public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar splashProgress;
    int splashTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //plays progress bar
        splashProgress = findViewById(R.id.progressBar);
        playProgress();

        //start next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, splashTime);
    }

    //Progress bar delat
    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(5000)
                .start();
    }
}