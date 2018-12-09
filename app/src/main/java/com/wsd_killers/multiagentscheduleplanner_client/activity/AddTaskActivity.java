package com.wsd_killers.multiagentscheduleplanner_client.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.wsd_killers.multiagentscheduleplanner_client.Data.TaskType;
import com.wsd_killers.multiagentscheduleplanner_client.Data.ToDoTask;
import com.wsd_killers.multiagentscheduleplanner_client.Data.ToDoTaskRepository;
import com.wsd_killers.multiagentscheduleplanner_client.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    private TextView mTaskName;
    private Spinner mTaskType;
    private TextView mTaskSlots;
    private TextView mTaskBeginning;
    private TextView mTaskEnd;
    private TextView mTaskDuration;
    private FloatingActionButton confirmFab;
    private ToDoTaskRepository mTodoTaskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initializeViews();
        mTodoTaskRepository = ToDoTaskRepository.getInstance();
    }

    private void initializeViews() {
        mTaskName = findViewById(R.id.task_name);
        mTaskType = findViewById(R.id.task_type);
        mTaskSlots = findViewById(R.id.task_slots);
        mTaskBeginning = findViewById(R.id.task_begin);
        mTaskEnd = findViewById(R.id.task_end);
        mTaskDuration = findViewById(R.id.task_duration);
        confirmFab = findViewById(R.id.task_confirm_fab);

        mTaskType.setAdapter(new ArrayAdapter<TaskType>(this, android.R.layout.simple_spinner_item, TaskType.values()));
    }

    public void onConfirmClicked(View view) {
        try {
            ToDoTask task = new ToDoTask(
                    mTaskName.getText().toString(),
                    (TaskType) mTaskType.getSelectedItem(),
                    Integer.valueOf(mTaskSlots.getText().toString()),
                    parseDate(mTaskBeginning.getText().toString()),
                    parseDate(mTaskEnd.getText().toString()),
                    parseDate(mTaskDuration.getText().toString()));

            mTodoTaskRepository.addNewTask(task);
            finish();
        } catch (ParseException e) {
            //TODO
        }
    }

    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("hh:mm").parse(date);
    }
}
