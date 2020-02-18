package com.pointless.spinthewheel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
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
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static double coinvalue = 1;
    public static int costsilverspeed = 1;
    MediaPlayer mediaPlayer;
    MediaPlayer mps;
    public static float start;
    public static int costsilvernumbers = 1;
    public static int silverspeed;
    public static int silvernumbers;
    TextView mult;
    public static double times = 1;
    public static int d;
     TextView adder;
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
    public static float v6;
    public static float sc;
    public int wheel;
    public static int spins;
    public static int pcoins;
    public static int speed = 10000;
    int numbers;
    SharedPreferences mPrefs;
    int first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//////////////////HERE///////////////////////////
        setContentView(R.layout.activity_main);
        mps = MediaPlayer.create(getApplicationContext(), R.raw.seven);
        mps.setLooping(true);
        mps.start();
        ////////////////////////////////////////////////////
Toast.makeText(getApplicationContext(), "Please turn up to max volume", Toast.LENGTH_LONG).show();
        TextView tv = findViewById(R.id.textView7);
        tv.setTextColor(Color.rgb(50,205,50));
        String str = String.valueOf(times) + "X";
        Double db = (double)times;
       // tv.setText(String.format(Locale.CANADA, "%.2f", db) + "X");

        adder = findViewById(R.id.textView6);
        mult = findViewById(R.id.textView7);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPrefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        spins = (mPrefs.getInt("spins", 0));
        v6 = (mPrefs.getFloat("v6", 1));
        sc = (mPrefs.getFloat("sc", 1));
       // pcoins= (mPrefs.getInt("pcoins", 1));
        pcoins = 10;




        first = (mPrefs.getInt("first", 0));

        if (first == 0){
            first = 1;
            SharedPreferences.Editor mEditor = mPrefs.edit();
            mEditor.putInt("first", first).apply();
            Intent intent = new Intent(getApplicationContext(), firstTime.class);
            startActivity(intent);
        }

        wheel = (mPrefs.getInt("wheel",     1));
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
        GoldCoins = (mPrefs.getFloat("goldcoins", 160));

        silvernumbers = (mPrefs.getInt("silvernumbers", 0));
        silverspeed = (mPrefs.getInt("silverspeed", 0));
        speed = (int) (10000 - ((silverspeed * 0.05) * 1000));
        costsilvernumbers = (int) Math.pow(8, silvernumbers);

        TextView textview5 = findViewById(R.id.textView3);
        String realnum = Realnum(NumScore);
        textview5.setText(realnum);
        TextView textView4 = findViewById(R.id.textView4);
        realnum = Realnum(GoldCoins);
        textView4.setText(String.valueOf(realnum));


        final ImageView imageview = (ImageView) findViewById(R.id.imageView);
        level = mPrefs.getInt("level", 1);
        double pow = level -1;
        coinvalue = Math.pow(2,pow);

        progress = (mPrefs.getInt("progress", 0));
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax((int)(50 * Math.pow(level,1.5)));
        setprog(progress);

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
        clockwiserotate.setDuration((long)(speed/v6));
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
                if (anim == 1) {
                    times = (times + 0.01);
                    TextView tv = findViewById(R.id.textView7);

                    String str = String.valueOf(times) + "X";
                    Double db = (double)times;
                    if (db > 1.24){
                        tv.setTextColor(Color.rgb(255, 165, 0));
                    }
                    if (db > 1.39){
                        tv.setTextColor(Color.RED);
                    }
                    tv.setText(String.format(Locale.CANADA, "%.2f", db) + "X");

                }
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

                            }else Toast.makeText(getApplicationContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                    }else{

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


                        }else Toast.makeText(getApplicationContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                    }else{

                    }
                } else {
                    if (anim == 0) {
                        Toast.makeText(this, "Swipe the screen", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
        clockwiserotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                start = NumScore;


                TextView tv = findViewById(R.id.textView7);
                tv.setTextColor(Color.rgb(50,205,50));
                tv.setText("1.00X");
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

                if(number -wheel==1){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.one);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number -wheel==2){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.two);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number-wheel ==3){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.three);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number -wheel==4){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.four);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number -wheel==5){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.five);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number-wheel ==6){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.six);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number -wheel==7){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.seven);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number -wheel==8){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.eight);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }



                NumScore = NumScore + (float) (((number * coinvalue))*times);
                final TextView tv = findViewById(R.id.textView7);



                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("");
                        tv.setTextColor(Color.rgb(50,205,50));
                        times = 1;
                        Double db = (double)times;
                       // tv.setText(String.format(Locale.CANADA, "%.2f", db) + "X");
                        String str = String.valueOf(times) + "X";
                    }
                }, 1000);

                anim = 0;

                TextView textView2 = findViewById(R.id.textView3);
                TextView textView3 = findViewById(R.id.textView6);
                setvalue(start, NumScore);


                SharedPreferences.Editor mEditor = mPrefs.edit();
                ProgressBar progressBar = findViewById(R.id.progressBar);
                float lv = (number * (1+(GoldCoins)/100));
                while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                    int prog =  progressBar.getProgress();
                    lv = lv -  (progressBar.getMax()-prog);
                    level++;
                    progressBar.setProgress(0);
                    TextView textView = findViewById(R.id.textView);
                       textView.setText(String.valueOf(level));
                       double pow = level -1;
                    coinvalue = Math.pow(2,pow);
                    progressBar.setMax((int)(50 * Math.pow(level,1.5)));

                   // NumScore = NumScore + (float) (Math.pow(level, 2));


                }
                setprog(progressBar.getProgress() + (int)lv);
//                progressBar.incrementProgressBy((int)lv);
//                textView.setText(progressBar.getProgress() + "/" + progressBar.getMax());

                mEditor.putInt("spins", spins).apply();
                mEditor.putInt("wheel", wheel).apply();
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
                start = NumScore;
                TextView tv = findViewById(R.id.textView7);
                tv.setTextColor(Color.rgb(50,205,50));
                tv.setText("1.00X");
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {


                if(number-wheel ==1){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.one);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number-wheel ==2){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.two);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number-wheel ==3){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.three);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number -wheel==4){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.four);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number-wheel ==5){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.five);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number-wheel==6){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.six);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number -wheel==7){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.seven);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }
                if(number-wheel ==8){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.eight);
                    mediaPlayer.setVolume(1,1);
                    mediaPlayer.start();
                }


                NumScore = NumScore + (float) (((number * coinvalue))*times);


                final TextView tv = findViewById(R.id.textView7);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("");
                        tv.setTextColor(Color.rgb(50,205,50));
                        String str = String.valueOf(times) + "X";
                        Double db = (double)times;
                  //      tv.setText(String.format(Locale.CANADA, "%.2f", db) + "X");
                    }
                }, 1000);
                times = 1;



                anim = 0;
                TextView textView2 = findViewById(R.id.textView3);
                TextView textView3 = findViewById(R.id.textView6);
                setvalue(start, NumScore);



                SharedPreferences.Editor mEditor = mPrefs.edit();
                ProgressBar progressBar = findViewById(R.id.progressBar);
                float lv = (number * (1+(GoldCoins)/100));
                while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                    int prog =  progressBar.getProgress();
                    lv = lv -  (progressBar.getMax()-prog);
                    level++;

                    progressBar.setProgress(0);
                    TextView textView = findViewById(R.id.textView);
                       textView.setText(String.valueOf(level));
                    double pow = level -1;
                    coinvalue = Math.pow(2,pow);
                    progressBar.setMax((int)(50 * Math.pow(level,1.5)));
                //    NumScore = NumScore + (float) (Math.pow(level, 2));



                }
  //              progressBar.incrementProgressBy((int)lv);
                setprog(progressBar.getProgress() + (int)lv);
//                textView.setText(progressBar.getProgress() + "/" + progressBar.getMax());

                mEditor.putInt("spins", spins).apply();
                mEditor.putInt("wheel", wheel).apply();
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
    public void crates(View view) {
        if (!(anim == 1) || isAuto) {
            if (!isAuto){misAuto = false;
            }else misAuto = true;
            Intent intent = new Intent(this, CrateActivity.class);
            intent.putExtra(String.valueOf(speed), speed);
            intent.putExtra(String.valueOf(numbers), numbers);
            startActivity(intent);
            finish();
        } else Toast.makeText(this, "Wait until wheel is finished", Toast.LENGTH_SHORT).show();
    }

    public static String Realnum(float fl) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        float numValue = fl;
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.00").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }

    public void onBackPressed() {
        if (!(anim == 1) || isAuto) {
            finish();
        } else Toast.makeText(this, "Wait until wheel is finished", Toast.LENGTH_SHORT).show();
    }

    public void setprog (final float prog) {
        final ProgressBar pb = findViewById(R.id.progressBar);
        Float cprog = (float) pb.getProgress();
        Runnable runnable = new Runnable() {
            long num = 600;
            int min;
            int sec;

            @Override
            public void run() {
                try {
                    for (int a = 1; a < 50; a++) {
                        Thread.sleep(10);
                        float av = pb.getProgress() + (prog - pb.getProgress()) / (50/a);
                        pb.setProgress((int) av);
                    }
                    progress = pb.getProgress();
                    SharedPreferences.Editor mEditor = mPrefs.edit();
                    mEditor.putInt("progress", progress).apply();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }

            ;
        };
        Thread mthread = new Thread(runnable);
        mthread.start();

        }
        public void setvalue (final float start, final float end){
            final TextView two = findViewById(R.id.textView3);
            final TextView one = findViewById(R.id.textView6);
            long starttime = System.currentTimeMillis();
            d = 1;
            Handler handler1 = new Handler();
            for (int a = 1; a<=50 ;a++) {
                handler1.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        float av = start + (end - start) / (50 / d);
                        bdsa(end, start);
//                        one.setText(Realnum(((end - start) / (50 / d))));
                        two.setText(Realnum(av));

                        d++;
                    }
                }, 0 * d);


            }

        }
        public void bdsa(float end, float av){
            final TextView two = findViewById(R.id.textView3);
            final TextView one = findViewById(R.id.textView6);
//            one.setText(Realnum(((end - start) / (50 / d))));
            two.setText(Realnum(av));
    }


    }




