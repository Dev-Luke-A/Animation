package com.pointless.spinthewheel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.pointless.spinthewheel.MainActivity.GoldCoins;
import static com.pointless.spinthewheel.MainActivity.NumScore;

import static com.pointless.spinthewheel.MainActivity.Realnum;
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
        isAuto = MainActivity.misAuto;
        mPrefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        Button Button1 = findViewById(R.id.button);
        wheel = mPrefs.getInt("wheel", 0);
        Button Button3 = findViewById(R.id.button3);



        costsilverspeed = (int)Math.pow(silverspeed, 2);
        goldnums = mPrefs.getInt("goldnums", 0);
        String realnum = Realnum(costsilverspeed);
        Button1.setText("Upgrade: " + realnum + " silver coins");

        TextView textview2 = findViewById(R.id.textView9);
        float triangular = ((level*level)/2) + (level/2);
        textview2.setText((float) (10 - (silverspeed * 0.05) ) + "  Seconds");
        goldGain = (NumScore / 1000) + triangular;
        realnum = Realnum(goldGain);

        Button b1 = findViewById(R.id.button6);
        b1.setText("Reset for: " + String.valueOf(realnum) + " Gold coins");
        Button b3 = findViewById(R.id.button3);
        upgcost =  Math.pow(((wheel+2)), 5);
        b3.setText("Upgrade: " + upgcost + " Gold coins");
    }

    public void silverspeedclick(View view) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        int speed = MainActivity.speed;
        if (NumScore >= costsilverspeed) {

            if (silverspeed < 190) {
                speed = speed - 20;
                Button Button1 = findViewById(R.id.button);

                Button Button3 = findViewById(R.id.button3);


                MainActivity.speed = speed;
                NumScore = NumScore - costsilverspeed;
                silverspeed++;
                costsilverspeed = (int)Math.pow(silverspeed, 3);


                mEditor.putInt("spins", spins).apply();
                mEditor.putFloat("silvercoins", NumScore).apply();
                mEditor.putInt("silverspeed", silverspeed).apply();
                mEditor.putInt("silvernumbers", silvernumbers).apply();

                mEditor.putFloat("goldcoins", gold).apply();
                TextView textView13 = findViewById(R.id.textView9);
                String realnum = Realnum(costsilverspeed);
                Button1.setText("Upgrade: " + realnum + " silver coins");
                textView13.setText((float) (10 - (silverspeed * 0.05)  ) + "  Seconds");
                mEditor.putInt("speed", (int) speed);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Not enough coins", Toast.LENGTH_LONG).show();
        }
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }






    public void resetForGold(View view) {
        MainActivity.progress = 0;
        level = 1;
        isAuto = false;
        MainActivity.isAuto = false;
        costgoldspeed = 1;
        silvernumbers = 0;
        NumScore = 0;
        gold = gold + goldGain;
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("progress", MainActivity.progress).apply();
        mEditor.putInt("spins", spins).apply();
        mEditor.putInt("level", level).apply();
        mEditor.putFloat("silvercoins", NumScore).apply();
        mEditor.putInt("silverspeed", silverspeed).apply();
        mEditor.putInt("silvernumbers", silvernumbers).apply();
        mEditor.putFloat("goldcoins", gold).apply();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }


    public void wheelupgrade (View view) {
        if(GoldCoins >= upgcost) {

         if(wheel == 22) wheel =42;
         if(wheel == 12) wheel =22;
         if(wheel == 7) wheel =12;
         if(wheel == 1) wheel =7;
            if(wheel == 0)wheel++;
            gold = (float)(gold - upgcost);
            upgcost =   Math.pow(((wheel+2)), 5);
            Button b3 = findViewById(R.id.button3);
            b3.setText("Upgrade: " + upgcost + " Gold coins");
                SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putFloat("goldcoins", gold).apply();
                mEditor.putInt("wheel", wheel).apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }else Toast.makeText(getApplicationContext(), "Not enough gold", Toast.LENGTH_SHORT).show();
    }
}


