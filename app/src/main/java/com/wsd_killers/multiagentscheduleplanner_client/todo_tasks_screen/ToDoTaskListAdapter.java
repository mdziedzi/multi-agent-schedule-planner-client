package com.wsd_killers.multiagentscheduleplanner_client.todo_tasks_screen;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsd_killers.multiagentscheduleplanner_client.R;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;

import java.util.ArrayList;
import java.util.Date;

public class ToDoTaskListAdapter extends RecyclerView.Adapter<ToDoTaskListAdapter.TaskViewHolder> {

    private ArrayList<ToDoTask> tasks;

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private CardView mCard;
        private TextView mTaskText;
        private TextView mTaskTime;
        public TaskViewHolder(View v) {
            super(v);
            mCard = v.findViewById(R.id.card_view);
            mTaskText = v.findViewById(R.id.task);
            mTaskTime = v.findViewById(R.id.task_time);
        }
    }

    public ToDoTaskListAdapter(ArrayList<ToDoTask> myDataset) {
        tasks = myDataset;
    }


    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_layout, parent, false);

        TaskViewHolder vh = new TaskViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
//        holder.mTaskText.setText(tasks.get(position));
        holder.mTaskText.setText(tasks.get(position).getName());
        holder.mTaskTime.setText(getTextString(tasks.get(position)));
    }

    private String getTextString(ToDoTask toDoTask) {
        Date begin = toDoTask.getTimeIntervalBegin();
        Date end = toDoTask.getTimeIntervalEnd();

        return begin.getHours() + ":"
                + begin.getMinutes() + "-" + end.getHours() + ":" + end.getMinutes();

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
