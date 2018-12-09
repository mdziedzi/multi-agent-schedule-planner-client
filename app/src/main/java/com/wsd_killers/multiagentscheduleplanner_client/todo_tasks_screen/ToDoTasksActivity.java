package com.wsd_killers.multiagentscheduleplanner_client.todo_tasks_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wsd_killers.multiagentscheduleplanner_client.Data.TaskType;
import com.wsd_killers.multiagentscheduleplanner_client.Data.ToDoTask;
import com.wsd_killers.multiagentscheduleplanner_client.Data.ToDoTaskRepository;
import com.wsd_killers.multiagentscheduleplanner_client.R;
import com.wsd_killers.multiagentscheduleplanner_client.activity.AddTaskActivity;

import java.util.Date;

public class ToDoTasksActivity extends AppCompatActivity implements ToDoTasksContract.View {

    private String agentName;
    private ToDoTasksContract.Presenter configPresenter;
    private FloatingActionButton addNewTaskButton;
    private FloatingActionButton confirmAgendaButton;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ToDoTaskRepository mToDoTaskRepository;
    private String[] tasksMock = {"test", "teeeest", "jeszcze jeden test"};
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

        configPresenter = new ToDoTasksPresenter(this);

        mToDoTaskRepository = ToDoTaskRepository.getInstance();
        initViews();


        agentName = getIntent().getStringExtra("agentName");
        configPresenter.bindAgent(agentName);


    }

    private void initViews() {
        addNewTaskButton = findViewById(R.id.create_fab);
        addNewTaskButton.setOnClickListener(addNewTask);

        confirmAgendaButton = findViewById(R.id.confirm_fab);
        confirmAgendaButton.setOnClickListener(confirmAgenda);
        mRecyclerView = findViewById(R.id.task_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ToDoTask toDoTask = new ToDoTask("Testowe", TaskType.DINNER, 2,
                new Date(0, 0, 0, 12, 15, 00), new Date(0 ,0, 0, 12, 45,0),
                new Date(0 , 0, 0, 0, 30, 0));
        mToDoTaskRepository.addNewTask(toDoTask);

        mAdapter = new ToDoTaskListAdapter(mToDoTaskRepository.getTasks());
        mRecyclerView.setAdapter(mAdapter);

        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDoTasksActivity.this, AddTaskActivity.class);
                startActivityForResult(i, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100) { //TODO
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
