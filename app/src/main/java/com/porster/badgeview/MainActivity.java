package com.porster.badgeview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.porster.badgeview.badgeview.BadgeLinearLayout;
import com.porster.badgeview.badgeview.BadgeTextView;

import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BadgeLinearLayout test_linear2= (BadgeLinearLayout) findViewById(R.id.test_linear2);
        test_linear2.setBadgeCount(20);


        final BadgeTextView badge_tv1= (BadgeTextView) findViewById(R.id.badge_tv1);
        badge_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge_tv1.setBadgeCount(87);
            }
        });
        final BadgeTextView badge_tv2= (BadgeTextView) findViewById(R.id.badge_tv2);
        badge_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge_tv2.setBadgeColor(Color.BLACK);
                badge_tv2.setBadgeCount(new Random().nextInt(100));
            }
        });
    }
}
