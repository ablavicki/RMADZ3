package com.ferit.ablavicki.rmadz3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<Task> mTasks;
    Context context;

    public TaskAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPriority)
        ImageView ivPriority;

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvCategory)
        TextView tvCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        /*@OnClick
        public void onTaskClick(){
            onClick(mTasks.get(getAdapterPosition()));
        }

        @OnLongClick
        public boolean onTaskLongClick(){
            return onLongClick(mTasks.get(getAdapterPosition()));
        }*/


    }
}
