package com.wsd_killers.multiagentscheduleplanner_client.config_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wsd_killers.multiagentscheduleplanner_client.R;

public class ConfigActivity extends AppCompatActivity implements ConfigContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }
}
