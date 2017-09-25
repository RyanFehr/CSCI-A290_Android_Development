package tech.ryanfehr.androiddevweek5assignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentEditTask.OnTaskUpdateListener, FragmentNewTask.OnTaskNewListener{

    private List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateTaskList();
        populateTaskListView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences savedTasks = getSharedPreferences("SAVED_TASKS", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = savedTasks.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskList); // myObject - instance of MyObject
        prefsEditor.putString("SAVED_TASKS", json);
        prefsEditor.commit();
    }


    private void populateTaskList() {
        SharedPreferences savedTasks = getSharedPreferences("SAVED_TASKS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = savedTasks.getString("SAVED_TASKS", "");
        Task[] tArray  = gson.fromJson(json, Task[].class);
        taskList = new ArrayList<>(Arrays.asList(tArray));
        if(taskList.size() == 0) {
            taskList = new ArrayList<>();
            taskList.add(new Task("Example Task", "* Create a new task by clicking the +\n* Edit this task by clicking it", new Date()));
        }
    }

    private void populateTaskListView() {
        ArrayAdapter<Task> contactArrayAdapter = new TaskAdaptor(taskList);
        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        taskListView.setAdapter(contactArrayAdapter);

        // Setup onClick listener for imageGalleryListView items
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                findViewById(R.id.taskListView).setVisibility(View.INVISIBLE);
                findViewById(R.id.addButton).setVisibility(View.INVISIBLE);
                // Add Fragment Manager
                FragmentManager fragmentManager = getFragmentManager();

                // Setup Transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Create chosen fragment
                FragmentEditTask fragmentEditTask = new FragmentEditTask();

                // Pass object to fragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("TASK", taskList.get(position));
                taskList.remove(position);

                // set Fragment Arguments
                fragmentEditTask.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_content, fragmentEditTask);

                // Commit transaction
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void taskUpdated(String taskTitle, String taskContent, Date taskDate) {
        if(taskDate != null) {  // We updated a task
            Task task = new Task(taskTitle, taskContent, taskDate);
            taskList.add(task);
        }
        populateTaskListView();
        findViewById(R.id.taskListView).setVisibility(View.VISIBLE);
        findViewById(R.id.addButton).setVisibility(View.VISIBLE);
    }

    @Override
    public void taskAdded(String taskTitle, String taskContent, Date taskDate) {
        Task task = new Task(taskTitle, taskContent, taskDate);
        taskList.add(task);
        populateTaskListView();
        findViewById(R.id.taskListView).setVisibility(View.VISIBLE);
        findViewById(R.id.addButton).setVisibility(View.VISIBLE);
    }

    public class TaskAdaptor extends ArrayAdapter<Task> {

        public TaskAdaptor(List<Task> imageList) {
            super(MainActivity.this, R.layout.task_item_layout, taskList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View viewItem = convertView;
            if(viewItem == null) {
                viewItem = getLayoutInflater().inflate(R.layout.task_item_layout, parent, false);
            }
            // get the current view
            Task currentTask = taskList.get(position);

            TextView taskTitleTextView = (TextView) viewItem.findViewById(R.id.taskTitleTextView);
            taskTitleTextView.setText(currentTask.getTitle());
            TextView taskContentTextView = (TextView) viewItem.findViewById(R.id.taskContentTextView);
            taskContentTextView.setText(currentTask.getContent());

            Date today = new Date();
            Calendar todayCalendar = Calendar.getInstance();
            Calendar taskCalendar = Calendar.getInstance();
            taskCalendar.setTime(currentTask.getFinishByDate());
            todayCalendar.setTime(today);

            if (taskCalendar.before(todayCalendar)) {
                Toast.makeText(MainActivity.this, "You have ran out of time to complete " + currentTask.getTitle(), Toast.LENGTH_SHORT).show();
            }

            return viewItem;
        }
    }

    public void addTaskButtonOnClickListener(View view) {
        findViewById(R.id.taskListView).setVisibility(View.INVISIBLE);
        findViewById(R.id.addButton).setVisibility(View.INVISIBLE);
        // Add Fragment Manager
        FragmentManager fragmentManager = getFragmentManager();

        // Setup Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create chosen fragment
        FragmentNewTask fragmentNewTask = new FragmentNewTask();

        // set Fragment Arguments
        fragmentTransaction.replace(R.id.main_content, fragmentNewTask);

        // Commit transaction
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
