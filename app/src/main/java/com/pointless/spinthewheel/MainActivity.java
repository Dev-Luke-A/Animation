package com.pointless.spinthewheel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static double coinvalue = 1;
    public static int costsilverspeed = 1;
    public static int costsilvernumbers = 1;
    public static int silverspeed;
    public static int silvernumbers;
    public static boolean isAuto = false;
    public static int costgoldspeed = 1;
    public static int costgoldnumbers = 1;
    public static float NumScore = 0;
    public static boolean misAuto;
    public static int level;
    public static int progress;
    Random random = new Random();
    private float x1;
    private float x2;
    static final int MIN_DISTANCE = 150;
    int anim = 0;
    private int lastangle = -1;
    int rotation;
    public static float GoldCoins;
    int number;
    public int wheel;
    public static int spins;
    public static int speed = 10000;
    int numbers;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPrefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        spins = (mPrefs.getInt("spins", 0));
        wheel = (mPrefs.getInt("wheel", 0));
        if(wheel== 1){
            ImageView iv = findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheeltwo));
        }else if (wheel ==7){
            ImageView iv = findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheelthree));
        }else if(wheel== 12){
            ImageView iv = findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheelfour));
        }else if (wheel ==22){
            ImageView iv = findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheelfive));
        }else if (wheel ==42){
            ImageView iv = findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheelsix));
        }
        NumScore = (mPrefs.getFloat("silvercoins", 0));
        GoldCoins = (mPrefs.getFloat("goldcoins", 0));

        silvernumbers = (mPrefs.getInt("silvernumbers", 0));
        silverspeed = (mPrefs.getInt("silverspeed", 0));
        speed = (int) (10000 - ((silverspeed * 0.05) * 1000));
        costsilvernumbers = (int) Math.pow(8, silvernumbers);
        coinvalue = Math.pow(2,level-1);
        TextView textview5 = findViewById(R.id.textView3);
        String realnum = Realnum(NumScore);
        textview5.setText(realnum);
        TextView textView4 = findViewById(R.id.textView4);
        realnum = Realnum(GoldCoins);
        textView4.setText(String.valueOf(realnum));


        final ImageView imageview = (ImageView) findViewById(R.id.imageView);
        level = mPrefs.getInt("level", 1);
        progress = (mPrefs.getInt("progress", 0));
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax((int)(50 * Math.pow(level,2)));
        progressBar.setProgress(progress);
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(level));

    //    TextView tv = findViewById(R.id.textView);
    //    tv.setText(progressBar.getProgress() + "/" + progressBar.getMax());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ImageView imageview = (ImageView) findViewById(R.id.imageView);
        float pivotY = imageview.getHeight() / 2 + 3;
        float pivotX = imageview.getWidth() / 2 + 3;
        int clockwiseangle = Math.abs(random.nextInt(1800) + 1800);
        int randantc =  Math.abs(random.nextInt(1800));
        int anticlockwiseangle = Math.abs(random.nextInt(1800) + 1800);

        Animation clockwiserotate = new RotateAnimation(lastangle == -1 ? 360 : lastangle, lastangle + clockwiseangle, pivotX, pivotY);
        Animation anticlockwiserotate = new RotateAnimation(lastangle == -1 ? 0 : lastangle, lastangle - anticlockwiseangle, pivotX, pivotY);
        final TextView textView = findViewById(R.id.textView);
        clockwiserotate.setDuration(speed);
        clockwiserotate.setFillAfter(true);
        anticlockwiserotate.setDuration(speed);
        anticlockwiserotate.setFillAfter(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (deltaX > MIN_DISTANCE) {
                    if (anim == 0) {
                        if (!isAuto){
                        anim = 1;
                        imageview.startAnimation(clockwiserotate);
                        lastangle = lastangle + clockwiseangle;
                        spins++;
                        rotation = 360 - (lastangle % 360);
                        if (0 <= rotation && rotation < 45) number = 1+wheel;
                        if (45 <= rotation && rotation < 90) number = 2+wheel;
                        if (90 <= rotation && rotation < 135) number = 3+wheel;
                        if (135 <= rotation && rotation < 180) number = 4+wheel;
                        if (180 <= rotation && rotation < 225) number = 5+wheel;
                        if (225 <= rotation && rotation < 270) number = 6+wheel;
                        if (270 <= rotation && rotation < 315) number = 7+wheel;
                        if (315 <= rotation && rotation < 360) number = 8+wheel;
                        NumScore = NumScore + (int) (number * coinvalue);
                            }else Toast.makeText(getApplicationContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                    }


                } else if (deltaX < -MIN_DISTANCE) {

                    if (anim == 0) {
                        if (!isAuto){
                        anim = 1;
                        imageview.startAnimation(anticlockwiserotate);
                        lastangle = lastangle - anticlockwiseangle;
                        spins++;
                        rotation = 360 - (lastangle % 360);
                        if (rotation > 360) rotation = rotation - 360;
                        if (0 <= rotation && rotation < 45) number = 1 + wheel;
                        if (45 <= rotation && rotation < 90) number = 2 + wheel;
                        if (90 <= rotation && rotation < 135) number = 3 + wheel;
                        if (135 <= rotation && rotation < 180) number = 4 + wheel;
                        if (180 <= rotation && rotation < 225) number = 5 + wheel;
                        if (225 <= rotation && rotation < 270) number = 6 + wheel;
                        if (270 <= rotation && rotation < 315) number = 7 + wheel;
                        if (315 <= rotation && rotation < 360) number = 8 + wheel;
                        NumScore = NumScore + (int) (number * coinvalue);
                        }else Toast.makeText(getApplicationContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                    }
                } else {
                    Toast.makeText(this, "Swipe the screen", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        clockwiserotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                anim = 0;
                TextView textView2 = findViewById(R.id.textView3);
                String realnum = Realnum(NumScore);
                textView2.setText(realnum);
                SharedPreferences.Editor mEditor = mPrefs.edit();
                ProgressBar progressBar = findViewById(R.id.progressBar);
                float lv = (number * (GoldCoins+1));
                while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                    int prog =  progressBar.getProgress();
                    lv = lv -  (progressBar.getMax()-prog);
                    level++;
                    progressBar.setProgress(0);
                    TextView textView = findViewById(R.id.textView);
                       textView.setText(String.valueOf(level));

                    coinvalue = Math.pow(2,level-1);
                    progressBar.setMax((int)(50 * Math.pow(level,2)));
                    NumScore = NumScore + (float) (Math.pow(level, 2));
                    realnum = Realnum(NumScore);
                    textView2.setText(realnum);


                }
                progressBar.incrementProgressBy((int)lv);
//                textView.setText(progressBar.getProgress() + "/" + progressBar.getMax());

                mEditor.putInt("spins", spins).apply();
                mEditor.putFloat("coinvalue", (float)coinvalue);
                mEditor.putFloat("silvercoins", NumScore).apply();
                mEditor.putInt("level", level).apply();

                progress = progressBar.getProgress();
                mEditor.putInt("progress", progressBar.getProgress()).apply();
                TextView textView = findViewById(R.id.textView);
                   textView.setText(String.valueOf(level));

            }
        });
        anticlockwiserotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                anim = 0;
                TextView textView2 = findViewById(R.id.textView3);
                String realnum = Realnum(NumScore);
                textView2.setText(realnum);


                SharedPreferences.Editor mEditor = mPrefs.edit();
                ProgressBar progressBar = findViewById(R.id.progressBar);
                float lv = (number * (GoldCoins+1));
                while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                    int prog =  progressBar.getProgress();
                    lv = lv -  (progressBar.getMax()-prog);
                    level++;
                    progressBar.setProgress(0);
                    TextView textView = findViewById(R.id.textView);
                       textView.setText(String.valueOf(level));
                    coinvalue = Math.pow(2,level-1);
                    progressBar.setMax((int)(50 * Math.pow(level,2)));
                    NumScore = NumScore + (float) (Math.pow(level, 2));
                    realnum = Realnum(NumScore);
                    textView2.setText(realnum);


                }
                progressBar.incrementProgressBy((int)lv);
//                textView.setText(progressBar.getProgress() + "/" + progressBar.getMax());

                mEditor.putInt("spins", spins).apply();
                mEditor.putFloat("coinvalue", (float)coinvalue);
                mEditor.putFloat("silvercoins", NumScore).apply();
                mEditor.putInt("level", level).apply();

                progress = progressBar.getProgress();
                mEditor.putInt("progress", progressBar.getProgress()).apply();
                TextView textView = findViewById(R.id.textView);
                   textView.setText(String.valueOf(level));

            }
        });


        return super.onTouchEvent(event);
    }

    public void Upgrades(View view) {
        if (!(anim == 1) || isAuto) {
            if (!isAuto){misAuto = false;
            }else misAuto = true;
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra(String.valueOf(speed), speed);
            intent.putExtra(String.valueOf(numbers), numbers);
            startActivity(intent);
            finish();
        } else Toast.makeText(this, "Wait until wheel is finished", Toast.LENGTH_SHORT).show();
    }

    public static String Realnum(float f1) {
        String knum;
        if (f1 < 999) {
            knum = String.valueOf((int)f1);
        } else {
            if (f1 < 1000000 && f1 > 999) {
                knum =  String.valueOf((int) (f1 / 1000)) + "K";
            } else if (f1 < 1000000000 && f1 > 999999) {
                knum =  String.valueOf((int)(f1/1000000)) + "M";
            } else if (f1 < 1000000000000L && f1 > 999999999) {
                knum =  String.valueOf(((int)(f1/1000000000))) + "B";
            } else if (f1 < 1000000000000000L && f1 > 999999999999L) {
                knum = String.valueOf(((int) (f1 / 1000000000000L))) + "T";
            } else if (f1 < 10000000000000000L && f1 > 999999999999999L) {
                knum = String.valueOf(((int) (f1 / 1000000000000000L))) + "Qua";

            }else knum = "error";

        }
        if(knum.endsWith(".0")){
          knum = knum.substring(knum.length()-2, knum.length());
        }
        return knum;
    }

    public void onBackPressed() {
        if (!(anim == 1) || isAuto) {
            finish();
        } else Toast.makeText(this, "Wait until wheel is finished", Toast.LENGTH_SHORT).show();
    }

}


