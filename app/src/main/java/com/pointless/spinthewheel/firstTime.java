package com.pointless.spinthewheel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class firstTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        TextView tv = findViewById(R.id.textViewX);
        tv.setText("Hello!  \nI see it is your first time playing. \n\nThis is a game about spinning a wheel. " +
                "Just swipe the screen! \n\nYou will earn experience depending on the score you get. " +
                "When you level up, the coins earned are doubled when you level up e.g. score of 7 x 2 ^ 3 level-ups (level 4) = 3 x 2 x 2 x 2 = 56 coins. \n\n" +
                "You can upgrade the speed of your wheel in the shop \n\nAfter you level up, you can reset your progress for " +
                "gold coins. Gold coins increasse your experience gain by 1% each! \nThat's all for now. \nEnjoy!");
    }
    public void back2 (View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }
}
