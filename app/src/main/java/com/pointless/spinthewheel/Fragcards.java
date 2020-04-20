package com.pointless.spinthewheel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.pointless.spinthewheel.MainActivity.put;


public class Fragcards extends Fragment {
    public static Float sc;
    public static Float v6;
    public static Float ten;
    public static Float cloud;
    public static Float stripe;
    public static Float key;
    public static Float control;
    public static Float hammer;

    public static Float bank;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cards, container, false);

        return inflater.inflate(R.layout.frag_cards, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv2 = view.findViewById(R.id.textView2);
        tv2.setText(String.valueOf(MainActivity.pcoins));
      sc = MainActivity.sc;
        v6 = MainActivity.v6;


        final ImageView iv = view.findViewById(R.id.imageView5);
       final ImageButton b1 = view.findViewById(R.id.button7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView iv = getView().findViewById(R.id.imageView5);
                iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.crate1));
                float pivotY = b1.getHeight() / 2 + 3;
                float pivotX = b1.getWidth() / 2 + 3;
                int clockwiseangle = 720;
                int anticlockwiseangle = 30;
                float lastangle = 0;
                Animation clockwiserotate = new RotateAnimation(lastangle == -1 ? 360 : lastangle, lastangle + clockwiseangle, pivotX, pivotY);
                Animation anticlockwiserotate = new RotateAnimation(lastangle == -1 ? 0 : lastangle, lastangle - anticlockwiseangle, pivotX, pivotY);

                clockwiserotate.setDuration((long) (5000));
                clockwiserotate.setFillAfter(true);



                if(MainActivity.pcoins > 0) {


                    iv.startAnimation(clockwiserotate);

                    clockwiserotate.setAnimationListener(new Animation.AnimationListener() {
                                                             @Override
                                                             public void onAnimationStart(Animation arg0) {

                                                             }


                                                             public void onAnimationEnd(Animation arg0){
                                                                 MainActivity.pcoins = MainActivity.pcoins - 1;
                                                            //     TextView tv = getView().findViewById(R.id.textView5);
                                                                 ImageView iv = getView().findViewById(R.id.imageView5);

                                                                 Random r = new Random();
                                                                 Integer rand = r.nextInt(7);


                                                                  if(rand == 2){

                                                                     iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.s_bank));
//                                                                     tv.setText("BANK - 10% MORE COINS");
                                                                 }
                                                                 else if(rand == 3){

                                                                     iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.s_hammer));
//                                                                     tv.setText("HAMMER - DOUBLE TAP EFFECTIVENESS")e;
                                                                 }
                                                                 else if(rand == 4){

                                                                     iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.b_bonuseveryten));
//                                                                     tv.setText("BONUS 10 - BONUS MONEY EVERY 10 SPINS");
                                                                 }
                                                                 else if(rand == 5){

                                                                     iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.b_gofaster));
//                                                                     tv.setText("GO FASTER STRIPES - 100% FASTER BUT EARN 50% COINS");
                                                                 }
                                                                 else if(rand == 6){

                                                                     iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.b_keyrebind));
//                                                                     tv.setText("KEY REBIND - 50% FASTER COOLDOWN");
                                                                 }
                                                                 else if(rand == 1){

                                                                     iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.b_controller));
//                                                                     tv.setText("CONTROLLER - SPIN JUST BY TAPPING");
                                                                 }
                                                                 else if(rand == 0){

                                                                     iv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.b_cloud));
//                                                                     tv.setText("CLOUD - 50% SLOWER BUT TAP IS 200% MORE EFFECTIVE");
                                                                 }
                                                                 MainActivity.put2();

                                                                 TextView tv2 = getView().findViewById(R.id.textView2);
                                                                 tv2.setText(String.valueOf(MainActivity.pcoins));
                                                             }

                                                             public void onAnimationRepeat(Animation arg0){

                                                             }


                                                         });

                }else Toast.makeText(getContext(), "Not enough coins", Toast.LENGTH_LONG).show();
            }
        });

    }


}


