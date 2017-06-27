package com.porster.badgeview;

import android.app.Activity;
import android.os.Bundle;

import com.porster.badgeview.badgeview.BadgeLinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BadgeLinearLayout test_linear2= (BadgeLinearLayout) findViewById(R.id.test_linear2);
        test_linear2.setBadgeCount(20);
    }
}
