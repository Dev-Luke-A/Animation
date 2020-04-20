package com.pointless.spinthewheel;

import android.appwidget.AppWidgetHostView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;

import static com.pointless.spinthewheel.Activity2.gold;
import static com.pointless.spinthewheel.MainActivity.GoldCoins;
import static com.pointless.spinthewheel.MainActivity.NumScore;
import static com.pointless.spinthewheel.MainActivity.Realnum;
import static com.pointless.spinthewheel.MainActivity.SWITCH;
import static com.pointless.spinthewheel.MainActivity.costsilverspeed;
import static com.pointless.spinthewheel.MainActivity.level;
import static com.pointless.spinthewheel.MainActivity.mPrefs;
import static com.pointless.spinthewheel.MainActivity.put3;
import static com.pointless.spinthewheel.MainActivity.silvernumbers;
import static com.pointless.spinthewheel.MainActivity.silverspeed;
import static com.pointless.spinthewheel.MainActivity.spins;
import static com.pointless.spinthewheel.MainActivity.wheel;

public class Fragupgrades extends Fragment {
    public static int finish = 0;
    public int wheel;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_upgrades, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressBar pb = getView().findViewById(R.id.progressBarspeed);
        pb.setMax(10);
        pb.setProgress((silverspeed));
        wheel = MainActivity.wheel;
        Button Button1 = getView().findViewById(R.id.speedup);
        Button Button3 = getView().findViewById(R.id.button3);

        TextView textview5 = getView().findViewById(R.id.textView3);
        String realnum = Realnum(NumScore);
        textview5.setText(realnum);
        TextView textView4 = getView().findViewById(R.id.textView4);
        realnum = Realnum(GoldCoins);
        textView4.setText(String.valueOf(realnum));

        costsilverspeed = (int) ((Math.pow(silverspeed, 3) + 1) / MainActivity.sc);
        goldnums = mPrefs.getInt("goldnums", 0);
        realnum = Realnum(costsilverspeed);
        Button1.setText(String.valueOf(realnum ));

      // final  TextView textview2 = getView().findViewById(R.id.speednum);
        float triangular = Math.round(((float) (level * level) / 2) + level) - 2;
      //  textview2.setText((float) (10 - (silverspeed * 0.05)) + "  Seconds");
        //textview2.setText((int) (((silverspeed))*10) + "%");
        pb.setProgress((silverspeed));
        goldGain = triangular;
        realnum = Realnum(goldGain);

   //     Button b1 = getView().findViewById(R.id.button6);
//        b1.setText("Reset for: " + String.valueOf(realnum) + " Gold coins");
      //  Button b3 = getView().findViewById(R.id.button3);
        upgcost = Math.pow(((wheel + 2)), 3);
        String real = Realnum((float) upgcost);
//        b3.setText("Upgrade wheel:\n " + real + " Gold coins");

        Button b09 = getView().findViewById(R.id.speedup);
        b09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor mEditor = mPrefs.edit();
                int speed = MainActivity.speed;
                if (NumScore >= costsilverspeed) {

                    if (silverspeed < 10) {
                        speed = speed - 5;
                        Button Button1 = getView().findViewById(R.id.speedup);

                        Button Button3 = getView().findViewById(R.id.button3);


                        MainActivity.speed = speed;
                        NumScore = NumScore - costsilverspeed;
                        silverspeed++;
                        costsilverspeed = (int) ((Math.pow(silverspeed, 3) + 1) / MainActivity.sc);
                  //      textview2.setText((int)(((silverspeed))*10) + "%");

                        mEditor.putInt("spins", spins).apply();
                        mEditor.putFloat("silvercoins", NumScore).apply();
                        mEditor.putInt("silverspeed", silverspeed).apply();
                        mEditor.putInt("silvernumbers", silvernumbers).apply();

                        mEditor.putFloat("goldcoins", gold).apply();
                       // TextView textView13 = getView().findViewById(R.id.textView9);
                        String realnum = Realnum(costsilverspeed);
                        Button1.setText(String.valueOf(realnum));
                   //     textView13.setText((float) (10 - (silverspeed * 0.05)) + "  Seconds");
                        mEditor.putInt("speed", (int) speed);
                    }

                } else {
                    Toast.makeText(getContext(), "Not enough coins", Toast.LENGTH_LONG).show();
                }
                TextView textview5 = getView().findViewById(R.id.textView3);
                String realnum = Realnum(NumScore);
                textview5.setText(realnum);
                TextView textView4 = getView().findViewById(R.id.textView4);
                realnum = Realnum(GoldCoins);
                textView4.setText(String.valueOf(realnum));


            }
        });


//        Button b2 = getView().findViewById(R.id.button6);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MainActivity.progress = 0;
//                level = 1;
//                isAuto = false;
//                MainActivity.isAuto = false;
//                costgoldspeed = 1;
//                silvernumbers = 0;
//                NumScore = 0;
//                gold = gold + goldGain;
//                SharedPreferences.Editor mEditor = mPrefs.edit();
//                mEditor.putInt("progress", MainActivity.progress).apply();
//                mEditor.putInt("spins", spins).apply();
//                mEditor.putInt("level", level).apply();
//                mEditor.putFloat("silvercoins", NumScore).apply();
//                mEditor.putInt("silverspeed", silverspeed).apply();
//                mEditor.putInt("silvernumbers", silvernumbers).apply();
//                mEditor.putFloat("goldcoins", gold).apply();
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });

        Button b99 = getView().findViewById(R.id.coinup);
        View.OnClickListener oc = (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GoldCoins >= upgcost) {

                    if (wheel == 22) wheel = 42;
                    if (wheel == 12) wheel = 22;
                    if (wheel == 7) wheel = 12;
                    if (wheel == 1) wheel = 7;
                    if (wheel == 0) wheel++;
                    gold = (float) (gold - upgcost);
                    upgcost = Math.pow(((wheel + 2)), 3);
//                    Button b3 = getView().findViewById(R.id.button3);
                    String real = Realnum((float) upgcost);
//                    b3.setText("Upgrade wheel:\n " + real + " Gold coins");
                    put3();

                } else Toast.makeText(getContext(), "Not enough gold", Toast.LENGTH_SHORT).show();
            }
        });


    }
}


