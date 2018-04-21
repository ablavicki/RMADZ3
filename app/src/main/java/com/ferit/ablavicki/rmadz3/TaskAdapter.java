package com.ferit.ablavicki.rmadz3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mTasks;
    private ClickCallback mClickCallback;

    public TaskAdapter(List<Task> tasks, ClickCallback onTaskClickListener){
        mTasks = new ArrayList<>();
        this.refreshData(tasks);
        mClickCallback = onTaskClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view, mClickCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.ivPriority.setImageResource(task.getPriority());
        holder.tvName.setText(task.getName());
        holder.tvCategory.setText(task.getCategory());
    }

    void setTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTasks != null)
            return mTasks.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ClickCallback mClickCallback;

        @BindView(R.id.ivPriority)
        ImageView ivPriority;

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvCategory)
        TextView tvCategory;

        public ViewHolder(View itemView, final ClickCallback callback) {
            super(itemView);
            mClickCallback = callback;
            ButterKnife.bind(this, itemView);
        }

        @OnLongClick
        public boolean onTaskLongClick(){
            return mClickCallback.onLongClick(mTasks.get(getAdapterPosition()));
        }
    }

    public void refreshData(List<Task> tasks) {
        mTasks.clear();
        mTasks.addAll(tasks);
        this.notifyDataSetChanged();
    }

}
