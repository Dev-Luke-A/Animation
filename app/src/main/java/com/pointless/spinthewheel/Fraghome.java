package com.pointless.spinthewheel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Random;
import java.util.zip.Inflater;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

import static com.pointless.spinthewheel.Activity2.finish;

public class Fraghome extends Fragment {
    public static double coinvalue = 1;
    public int costsilverspeed = 1;
    MediaPlayer mediaPlayer;
    public MediaPlayer mps;
    public float start;
    public int costsilvernumbers = 1;
    public int silverspeed;
    public int silvernumbers;
    TextView mult;
    public double times = 1;
    public int SWITCH;
    public int d;
    TextView adder;
    public boolean isAuto = false;
    public int costgoldspeed = 1;
    public int costgoldnumbers = 1;
    static public float NumScore ;
    public boolean misAuto;
    public static int level;
    public static int progress;
    Random random = new Random();
    private float x1;
    private float x2;
    final int MIN_DISTANCE = 150;
    int anim = 0;
    private int lastangle = -1;
    int rotation;
    public float GoldCoins;
    int number;
    public float v6;
    public float sc;
    public int wheel;
    public int spins;
    public int pcoins;
    public int speed = 10000;
    public BottomNavigationView bc;
    int numbers;

    int first;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cards, container, false);
        return inflater.inflate(R.layout.frag_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView tv = view.findViewById(R.id.textView7);
        tv.setTextColor(Color.rgb(50, 205, 50));
        String str = String.valueOf(times) + "X";
        Double db = (double) times;
        // tv.setText(String.format(Locale.CANADA, "%.2f", db) + "X");


        mult = getView().findViewById(R.id.textView7);


        spins = MainActivity.spins;
        v6 = MainActivity.v6;
        sc = MainActivity.sc;

        pcoins = 10;


        wheel = MainActivity.wheel;
        if (wheel == 1) {
            ImageView iv = getView().findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wheeltwo));
        } else if (wheel == 7) {
            ImageView iv = getView().findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wheelthree));
        } else if (wheel == 12) {
            ImageView iv = getView().findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wheelfour));
        } else if (wheel == 22) {
            ImageView iv = getView().findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wheelfive));
        } else if (wheel == 42) {
            ImageView iv = getView().findViewById(R.id.imageView);

            iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wheelsix));
        }
        NumScore = MainActivity.NumScore;
        GoldCoins = MainActivity.GoldCoins;

        silvernumbers = MainActivity.silvernumbers;
        silverspeed = MainActivity.silverspeed;
        speed = (int) (10000 - ((silverspeed * 0.05) * 1000));
        costsilvernumbers = (int) Math.pow(8, silvernumbers);

        TextView textview5 = getView().findViewById(R.id.textView3);
        String realnum = Realnum(NumScore);
        textview5.setText(realnum);
        TextView textView4 = getView().findViewById(R.id.textView4);
        realnum = Realnum(GoldCoins);
        textView4.setText(String.valueOf(realnum));


        final ImageView imageview = (ImageView) getView().findViewById(R.id.imageView);
        level = MainActivity.level;
        double pow = level - 1;
        coinvalue = Math.pow(2, pow);

        progress = MainActivity.progress;
        ProgressBar progressBar = getView().findViewById(R.id.progressBar);
        progressBar.setMax((int) (50 * Math.pow(level, 1.5)));
        setprog(progress);

        TextView textView = getView().findViewById(R.id.textView);
        textView.setText(String.valueOf(level));


        view.setOnTouchListener(new View.OnTouchListener() {


            public boolean onTouch(View v, MotionEvent event) {
                ImageView imageview = (ImageView) getView().findViewById(R.id.imageView);
                float pivotY = imageview.getHeight() / 2 + 3;
                float pivotX = imageview.getWidth() / 2 + 3;
                int clockwiseangle = Math.abs(random.nextInt(1800) + 1800);
                int randantc = Math.abs(random.nextInt(1800));
                int anticlockwiseangle = Math.abs(random.nextInt(1800) + 1800);

                Animation clockwiserotate = new RotateAnimation(lastangle == -1 ? 360 : lastangle, lastangle + clockwiseangle, pivotX, pivotY);
                Animation anticlockwiserotate = new RotateAnimation(lastangle == -1 ? 0 : lastangle, lastangle - anticlockwiseangle, pivotX, pivotY);
                final TextView textView = getView().findViewById(R.id.textView);
                clockwiserotate.setDuration((long) (speed / v6));
                clockwiserotate.setFillAfter(true);
                anticlockwiserotate.setDuration((long) (speed / v6));
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
                            TextView tv = getView().findViewById(R.id.textView7);

                            String str = String.valueOf(times) + "X";
                            Double db = (double) times;
                            if (db > 1.24) {
                                tv.setTextColor(Color.rgb(255, 165, 0));
                            }
                            if (db > 1.39) {
                                tv.setTextColor(Color.RED);
                            }
                            tv.setText(String.format(Locale.CANADA, "%.2f", db) + "X");

                        }
                        if (deltaX > MIN_DISTANCE) {
                            if (anim == 0) {
                                if (!isAuto) {
                                    anim = 1;
                                    imageview.startAnimation(clockwiserotate);
                                    lastangle = lastangle + clockwiseangle;
                                    spins++;
                                    rotation = 360 - (lastangle % 360);
                                    if (0 <= rotation && rotation < 45) number = 1 + wheel;
                                    if (45 <= rotation && rotation < 90) number = 2 + wheel;
                                    if (90 <= rotation && rotation < 135) number = 3 + wheel;
                                    if (135 <= rotation && rotation < 180) number = 4 + wheel;
                                    if (180 <= rotation && rotation < 225) number = 5 + wheel;
                                    if (225 <= rotation && rotation < 270) number = 6 + wheel;
                                    if (270 <= rotation && rotation < 315) number = 7 + wheel;
                                    if (315 <= rotation && rotation < 360) number = 8 + wheel;

                                } else
                                    Toast.makeText(getContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                            } else {

                            }


                        } else if (deltaX < -MIN_DISTANCE) {

                            if (anim == 0) {
                                if (!isAuto) {
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


                                } else
                                    Toast.makeText(getContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                            } else {

                            }
                        } else {
                            if (anim == 0) {

                            }
                        }

                        break;
                }
                clockwiserotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation arg0) {
                        start = NumScore;


                        TextView tv = getView().findViewById(R.id.textView7);
                        tv.setTextColor(Color.rgb(50, 205, 50));
                        tv.setText("1.00X");
                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {

                        if (number - wheel == 1) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.one);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 2) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.two);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 3) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.three);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 4) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.four);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 5) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.five);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 6) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.six);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 7) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.seven);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 8) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.eight);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }


                        NumScore = NumScore + (float) (((number * coinvalue)) * times);
                        final TextView tv = getView().findViewById(R.id.textView7);


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText("");
                                tv.setTextColor(Color.rgb(50, 205, 50));
                                times = 1;
                                Double db = (double) times;

                                String str = String.valueOf(times) + "X";
                            }
                        }, 1000);

                        anim = 0;

                        TextView textView2 = getView().findViewById(R.id.textView3);

                        setvalue(start, NumScore);



                        ProgressBar progressBar = getView().findViewById(R.id.progressBar);
                        float lv = (number * (1 + (GoldCoins) / 100));
                        while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                            int prog = progressBar.getProgress();
                            lv = lv - (progressBar.getMax() - prog);
                            level++;
                            progressBar.setProgress(0);
                            TextView textView = getView().findViewById(R.id.textView);
                            textView.setText(String.valueOf(level));
                            double pow = level - 1;
                            coinvalue = Math.pow(2, pow);
                            progressBar.setMax((int) (50 * Math.pow(level, 1.5)));


                        }
                        setprog(progressBar.getProgress() + (int) lv);



                        progress = progressBar.getProgress();
                        MainActivity.put(NumScore, level, progress, coinvalue);
                        TextView textView = getView().findViewById(R.id.textView);
                        textView.setText(String.valueOf(level));

                    }
                });
                anticlockwiserotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation arg0) {
                        start = NumScore;
                        TextView tv = getView().findViewById(R.id.textView7);
                        tv.setTextColor(Color.rgb(50, 205, 50));
                        tv.setText("1.00X");
                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {


                        if (number - wheel == 1) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.one);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 2) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.two);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 3) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.three);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 4) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.four);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 5) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.five);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 6) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.six);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 7) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.seven);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }
                        if (number - wheel == 8) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.eight);
                            mediaPlayer.setVolume(1, 1);
                            mediaPlayer.start();
                        }


                        NumScore = NumScore + (float) (((number * coinvalue)) * times);


                        final TextView tv = getView().findViewById(R.id.textView7);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText("");
                                tv.setTextColor(Color.rgb(50, 205, 50));
                                String str = String.valueOf(times) + "X";
                                Double db = (double) times;

                            }
                        }, 1000);
                        times = 1;


                        anim = 0;
                        TextView textView2 = getView().findViewById(R.id.textView3);

                        setvalue(start, NumScore);



                        ProgressBar progressBar = getView().findViewById(R.id.progressBar);
                        float lv = (number * (1 + (GoldCoins) / 100));
                        while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                            int prog = progressBar.getProgress();
                            lv = lv - (progressBar.getMax() - prog);
                            level++;

                            progressBar.setProgress(0);
                            TextView textView = getView().findViewById(R.id.textView);
                            textView.setText(String.valueOf(level));
                            double pow = level - 1;
                            coinvalue = Math.pow(2, pow);
                            progressBar.setMax((int) (50 * Math.pow(level, 1.5)));


                        }

                        setprog(progressBar.getProgress() + (int) lv);


                      MainActivity.put(NumScore, level, progress, coinvalue);

                        progress = progressBar.getProgress();

                        TextView textView = getView().findViewById(R.id.textView);
                        textView.setText(String.valueOf(level));

                    }
                });


                return true;
            }
        });


    }


    public boolean Touch(MotionEvent event) {

        ImageView imageview = (ImageView) getView().findViewById(R.id.imageView);
        float pivotY = imageview.getHeight() / 2 + 3;
        float pivotX = imageview.getWidth() / 2 + 3;
        int clockwiseangle = Math.abs(random.nextInt(1800) + 1800);
        int randantc = Math.abs(random.nextInt(1800));
        int anticlockwiseangle = Math.abs(random.nextInt(1800) + 1800);

        Animation clockwiserotate = new RotateAnimation(lastangle == -1 ? 360 : lastangle, lastangle + clockwiseangle, pivotX, pivotY);
        Animation anticlockwiserotate = new RotateAnimation(lastangle == -1 ? 0 : lastangle, lastangle - anticlockwiseangle, pivotX, pivotY);
        final TextView textView = getView().findViewById(R.id.textView);
        clockwiserotate.setDuration((long) (speed / v6));
        clockwiserotate.setFillAfter(true);
        anticlockwiserotate.setDuration((long) (speed / v6));
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
                    TextView tv = getView().findViewById(R.id.textView7);

                    String str = String.valueOf(times) + "X";
                    Double db = (double) times;
                    if (db > 1.24) {
                        tv.setTextColor(Color.rgb(255, 165, 0));
                    }
                    if (db > 1.39) {
                        tv.setTextColor(Color.RED);
                    }
                    tv.setText(String.format(Locale.CANADA, "%.2f", db) + "X");

                }
                if (deltaX > MIN_DISTANCE) {
                    if (anim == 0) {
                        if (!isAuto) {
                            anim = 1;
                            imageview.startAnimation(clockwiserotate);
                            lastangle = lastangle + clockwiseangle;
                            spins++;
                            rotation = 360 - (lastangle % 360);
                            if (0 <= rotation && rotation < 45) number = 1 + wheel;
                            if (45 <= rotation && rotation < 90) number = 2 + wheel;
                            if (90 <= rotation && rotation < 135) number = 3 + wheel;
                            if (135 <= rotation && rotation < 180) number = 4 + wheel;
                            if (180 <= rotation && rotation < 225) number = 5 + wheel;
                            if (225 <= rotation && rotation < 270) number = 6 + wheel;
                            if (270 <= rotation && rotation < 315) number = 7 + wheel;
                            if (315 <= rotation && rotation < 360) number = 8 + wheel;

                        } else Toast.makeText(getContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                    } else {

                    }


                } else if (deltaX < -MIN_DISTANCE) {

                    if (anim == 0) {
                        if (!isAuto) {
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


                        } else Toast.makeText(getContext(), "Auto spin is on", Toast.LENGTH_SHORT);
                    } else {

                    }
                } else {
                    if (anim == 0) {

                    }
                }

                break;
        }
        clockwiserotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                start = NumScore;


                TextView tv = getView().findViewById(R.id.textView7);
                tv.setTextColor(Color.rgb(50, 205, 50));
                tv.setText("1.00X");
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

                if (number - wheel == 1) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.one);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 2) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.two);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 3) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.three);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 4) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.four);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 5) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.five);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 6) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.six);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 7) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.seven);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 8) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.eight);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }


                NumScore = NumScore + (float) (((number * coinvalue)) * times);
                final TextView tv = getView().findViewById(R.id.textView7);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("");
                        tv.setTextColor(Color.rgb(50, 205, 50));
                        times = 1;
                        Double db = (double) times;

                        String str = String.valueOf(times) + "X";
                    }
                }, 1000);

                anim = 0;

                TextView textView2 = getView().findViewById(R.id.textView3);

                setvalue(start, NumScore);



                ProgressBar progressBar = getView().findViewById(R.id.progressBar);
                float lv = (number * (1 + (GoldCoins) / 100));
                while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                    int prog = progressBar.getProgress();
                    lv = lv - (progressBar.getMax() - prog);
                    level++;
                    progressBar.setProgress(0);
                    TextView textView = getView().findViewById(R.id.textView);
                    textView.setText(String.valueOf(level));
                    double pow = level - 1;
                    coinvalue = Math.pow(2, pow);
                    progressBar.setMax((int) (50 * Math.pow(level, 1.5)));


                }
                setprog(progressBar.getProgress() + (int) lv);


                progress = progressBar.getProgress();
                MainActivity.put(NumScore, level, progress, coinvalue);
                TextView textView = getView().findViewById(R.id.textView);
                textView.setText(String.valueOf(level));

            }
        });
        anticlockwiserotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                start = NumScore;
                TextView tv = getView().findViewById(R.id.textView7);
                tv.setTextColor(Color.rgb(50, 205, 50));
                tv.setText("1.00X");
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {


                if (number - wheel == 1) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.one);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 2) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.two);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 3) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.three);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 4) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.four);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 5) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.five);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 6) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.six);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 7) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.seven);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }
                if (number - wheel == 8) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.eight);
                    mediaPlayer.setVolume(1, 1);
                    mediaPlayer.start();
                }


                NumScore = NumScore + (float) (((number * coinvalue)) * times);


                final TextView tv = getView().findViewById(R.id.textView7);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("");
                        tv.setTextColor(Color.rgb(50, 205, 50));
                        String str = String.valueOf(times) + "X";
                        Double db = (double) times;

                    }
                }, 1000);
                times = 1;


                anim = 0;
                TextView textView2 = getView().findViewById(R.id.textView3);

                setvalue(start, NumScore);



                ProgressBar progressBar = getView().findViewById(R.id.progressBar);
                float lv = (number * (1 + (GoldCoins) / 100));
                while (progressBar.getProgress() + lv >= progressBar.getMax()) {
                    int prog = progressBar.getProgress();
                    lv = lv - (progressBar.getMax() - prog);
                    level++;

                    progressBar.setProgress(0);
                    TextView textView = getView().findViewById(R.id.textView);
                    textView.setText(String.valueOf(level));
                    double pow = level - 1;
                    coinvalue = Math.pow(2, pow);
                    progressBar.setMax((int) (50 * Math.pow(level, 1.5)));


                }

                setprog(progressBar.getProgress() + (int) lv);




                progress = progressBar.getProgress();
                MainActivity.put(NumScore, level, progress, coinvalue);
                TextView textView = getView().findViewById(R.id.textView);
                textView.setText(String.valueOf(level));

            }
        });


        return true;
    }


    public String Realnum(float fl) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'Q', 'P'};
        float numValue = fl;
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.00").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }


    public void setprog(final float prog) {
        final ProgressBar pb = getView().findViewById(R.id.progressBar);
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
                        float av = pb.getProgress() + (prog - pb.getProgress()) / (50 / a);
                        pb.setProgress((int) av);
                    }
                    progress = pb.getProgress();
                    MainActivity.put(NumScore, level, progress, coinvalue);

//                    mEditor.putInt("progress", progress).apply();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }

            ;
        };
        Thread mthread = new Thread(runnable);
        mthread.start();

    }

    public void setvalue(final float start, final float end) {
        final TextView two = getView().findViewById(R.id.textView3);

        long starttime = System.currentTimeMillis();
        d = 1;
        Handler handler1 = new Handler();
        for (int a = 1; a <= 50; a++) {
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {

                    float av = start + (end - start) / (50 / d);
                    bdsa(end, start);

                    two.setText(Realnum(av));

                    d++;
                }
            }, 0 * d);


        }

    }

    public void bdsa(float end, float av) {
        final TextView two = getView().findViewById(R.id.textView3);


        two.setText(Realnum(av));
    }
}





