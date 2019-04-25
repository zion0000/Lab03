package com.example.zaiets42ipz_lab_03;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    boolean isRun = false;
    boolean wasRun;
    int sec = 0;
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.textView);
        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("sec");
            isRun = savedInstanceState.getBoolean("Run");
            wasRun = savedInstanceState.getBoolean("dontRun");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sec", sec);
        outState.putBoolean("Run", isRun);
        outState.putBoolean("dontRun", wasRun);
    }

    public void onClickStartTimer(View view) {
        isRun = true;
    }

    public void onClickPauseTimer(View view) {
        isRun = false;
    }

    public void onClickResetTimer(View view) {
        isRun = false;
        sec = 0;
    }

    /*@Override не работает таймер у меня когда я разворачиваю блин!!!
    а так надо было эти методы удалить...
    protected void onStop() {
        super.onStop();
        wasRun = isRun;
        isRun = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRun = wasRun;
    }*/


    @Override
    protected void onPause() {
        super.onPause();
        wasRun = isRun;
        isRun = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRun = wasRun;
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = sec / 3600;
                int mins = (sec % 3600) / 60;
                int seconds = sec % 60;

                String string = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, mins, seconds);
                timer.setText(string);

                if (isRun)
                    sec++;

                handler.postDelayed(this, 1000);
            }
        });
    }
}