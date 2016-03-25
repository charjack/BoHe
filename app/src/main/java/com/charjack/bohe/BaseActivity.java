package com.charjack.bohe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charjack.bohe.utils.MyActivityManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyActivityManager.getActivityManager().addActivity(this);
    }

}
