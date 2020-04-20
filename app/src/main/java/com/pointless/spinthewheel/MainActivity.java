package com.pointless.spinthewheel;

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
import android.support.v4.app.FragmentTransaction;
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

import static com.pointless.spinthewheel.Fragupgrades.gold;

public class MainActivity extends AppCompatActivity {
    public static double coinvalue = 1;
    public static int costsilverspeed = 1;
    MediaPlayer mediaPlayer;
    public static MediaPlayer mps;
    public static float start;
    public static int costsilvernumbers = 1;
    public static int silverspeed;
    public static int silvernumbers;
    TextView mult;
    public static double times = 1;
    public static int SWITCH;
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
    public static int wheel;
    public static int spins;
    public static int pcoins;
    public static int speed = 10000;
     public BottomNavigationView bc;
    int numbers;
    public static SharedPreferences mPrefs;
    int first;
    int selgrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SWITCH = 0;
        selgrag = 1;
        setContentView(R.layout.activity_main);
         bc = (BottomNavigationView) findViewById(R.id.bottom_navigation);
         bc.setSelectedItemId(R.id.action_spin);
        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_navigationcon, new Fraghome()).commit();
//////////////////HERE///////////////////////////



        bc.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedfragment = null;
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        if (selgrag == 3){}else{
                            selectedfragment = new Fragupgrades();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.righttoleft, R.anim.lefttoright);
                            ft.replace(R.id.bottom_navigationcon, selectedfragment).commit();
                            selgrag = 3;
                            wheel = mPrefs.getInt("wheel", 0);
                        }
                        break;
                    case R.id.action_spin:
                        if (selgrag == 1){}else{
                        selectedfragment = new Fraghome();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            if (selgrag == 2) {
                               ft.setCustomAnimations(R.anim.righttoleft, R.anim.lefttoright);
                            }else{
                                ft.setCustomAnimations(R.anim.righttolefte, R.anim.lefttorighte);
                            }
                            mps.stop();
                            mps = MediaPlayer.create(getApplicationContext(), R.raw.maintheme);
                            mps.start();
                            ft.replace(R.id.bottom_navigationcon, selectedfragment).commit();
                         selgrag = 1;
                        }
                        break;
                    case R.id.action_nearby:
                        if (selgrag == 2){}else{
                            selectedfragment = new Fragcards();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.righttolefte, R.anim.lefttorighte);
                            ft.replace(R.id.bottom_navigationcon, selectedfragment).commit();
                            selgrag = 2;
                            mps.stop();
                            mps = MediaPlayer.create(getApplicationContext(), R.raw.calmtwo);
                            mps.start();
                        }
                        break;
                }


                return true;
            }
        });



        if(selgrag == 2){
            mps = MediaPlayer.create(getApplicationContext(), R.raw.calmtwo);
        }else {
            mps = MediaPlayer.create(getApplicationContext(), R.raw.maintheme);
        }
        mps.setLooping(true);
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

// Request audio focus for playback
        int result = am.requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
                                              @Override
                                              public void onAudioFocusChange(int i) {

                                                      mps.pause();
                                                      mps.stop();
                                              }
                                          },
// Use the music stream.
                AudioManager.STREAM_MUSIC,
// Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);


        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mps.start();
            Intent i = new Intent("com.android.music.musicservicecommand");
            i.putExtra("command", "pause");
            sendBroadcast(i);
        }



        ////////////////////////////////////////////////






        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPrefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        spins = (mPrefs.getInt("spins", 0));
        v6 = (mPrefs.getFloat("v6", 1));
        sc = (mPrefs.getFloat("sc", 1));
        level = (mPrefs.getInt("level", 1));
        progress = (mPrefs.getInt("progress", 0));
       // pcoins= (mPrefs.getInt("pcoins", 1));
        pcoins = 10;
        wheel = mPrefs.getInt("wheel", 1);




        first = (mPrefs.getInt("first", 0));

        if (first == 0){
            first = 1;
            SharedPreferences.Editor mEditor = mPrefs.edit();
            mEditor.putInt("first", first).apply();
            Intent intent = new Intent(getApplicationContext(), firstTime.class);
            startActivity(intent);
        }


        NumScore = (mPrefs.getFloat("silvercoins", 0));
        GoldCoins = (mPrefs.getFloat("goldcoins", 160));

        silvernumbers = (mPrefs.getInt("silvernumbers", 0));
        silverspeed = (mPrefs.getInt("silverspeed", 0));
        speed = (int) (10000 - ((silverspeed * 0.05) * 1000));
        costsilvernumbers = (int) Math.pow(8, silvernumbers);






    //    TextView tv = findViewById(R.id.textView);
    //    tv.setText(progressBar.getProgress() + "/" + progressBar.getMax());

    }




    public static String Realnum(float fl) {
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

    public void onBackPressed() {
        if (!(anim == 1) || isAuto) {
            finish();
        } else Toast.makeText(this, "Wait until wheel is finished", Toast.LENGTH_SHORT).show();
    }







    @Override
    public void onPause(){
        super.onPause();
        if(SWITCH == 0) {
            mps.pause();
        }
        finish();
    }
    @Override
    public void onResume(){
        super.onResume();
        mps.start();
    }

public static void put(float n, int l, int p, double c){



    SharedPreferences.Editor mEditor = mPrefs.edit();
    mEditor.putInt("spins", spins).apply();
    mEditor.putInt("wheel", wheel).apply();
    mEditor.putFloat("coinvalue", (float) c);
    mEditor.putFloat("silvercoins", n).apply();
    mEditor.putInt("level", l).apply();
    mEditor.putInt("progress", p).apply();

    NumScore = n;
    level = l;
    progress = p;
    coinvalue = c;


    }
    public static void put2(){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("pcoins", pcoins).apply();
        mEditor.putFloat("v6", Fragcards.v6).apply();
        mEditor.putFloat("sc", Fragcards.sc).apply();
    }
    public static void put3(){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putFloat("goldcoins", gold).apply();
        mEditor.putInt("wheel", wheel).apply();
    }
    public void ft(View view){
        Intent intent = new Intent(getApplicationContext(), firstTime.class);
        startActivity(intent);
    }
    }




