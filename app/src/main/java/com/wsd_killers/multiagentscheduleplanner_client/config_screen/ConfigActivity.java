package com.wsd_killers.multiagentscheduleplanner_client.config_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wsd_killers.multiagentscheduleplanner_client.R;

public class ConfigActivity extends AppCompatActivity implements ConfigContract.View {

    private String agentName;
    private ConfigContract.Presenter configPresenter;
    private Button addNewTaskButton;
    private Button confirmAgendaButton;
    private View.OnClickListener addNewTask = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };
    private View.OnClickListener confirmAgenda = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            configPresenter.confirmAgenda();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        configPresenter = new ConfigPresenter(this);

        initViews();

        agentName = getIntent().getStringExtra("agentName");
        configPresenter.bindAgent(agentName);

    }

    private void initViews() {
        addNewTaskButton = findViewById(R.id.addNewTaskButton);
        addNewTaskButton.setOnClickListener(addNewTask);

        confirmAgendaButton = findViewById(R.id.confirmAgendaButton);
        confirmAgendaButton.setOnClickListener(confirmAgenda);

    }
}
