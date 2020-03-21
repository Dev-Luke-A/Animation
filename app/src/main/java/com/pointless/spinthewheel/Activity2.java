package com.pointless.spinthewheel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.pointless.spinthewheel.MainActivity.GoldCoins;
import static com.pointless.spinthewheel.MainActivity.NumScore;

import static com.pointless.spinthewheel.MainActivity.Realnum;
import static com.pointless.spinthewheel.MainActivity.SWITCH;
import static com.pointless.spinthewheel.MainActivity.level;
import static com.pointless.spinthewheel.MainActivity.progress;
import static com.pointless.spinthewheel.MainActivity.spins;

public class Activity2 extends AppCompatActivity {
    public static int finish = 0;
    public int wheel = 0;
    boolean isAuto = MainActivity.isAuto;
    public static double coinvalue = MainActivity.coinvalue;
    public static int costsilverspeed = MainActivity.costsilverspeed;
    public static int costsilvernumbers = MainActivity.costsilvernumbers;
    public static int costgoldspeed = MainActivity.costgoldspeed;
    public static int costgoldnumbers = MainActivity.costgoldnumbers;
    public static int silvernumbers = MainActivity.silvernumbers;
    public static int goldnums;
    double upgcost;

    public static float gold = MainActivity.GoldCoins;
    public static int silverspeed = MainActivity.silverspeed;
    float goldGain;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mPrefs = getSharedPreferences("data", Context.MODE_PRIVATE);


    }
}




