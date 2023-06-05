package dev.sheltonfrancisco.studentassistent.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.sheltonfrancisco.studentassistent.R;
import dev.sheltonfrancisco.studentassistent.models.Task;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private ArrayList<Task> tasks;

    public TaskListAdapter( ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }

    public void updateList( ArrayList<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView title, description, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.task_name);
            description = itemView.findViewById(R.id.task_description);
            date = itemView.findViewById(R.id.task_date);
        }

        public void bind(Task task) {

            String month = task.getDeadline().getMonth().toString();
            int day = task.getDeadline().getDayOfMonth();


            title.setText(task.getTitle());
            description.setText(task.getDescription());
            date.setText(day + " " + month.toLowerCase());
        }
    }
}
