package tech.ryanfehr.androiddevweek5assignment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ryan on 9/23/2017.
 */

public class FragmentTaskList extends Fragment {
    FragmentTaskList.OnTaskEditListener taskEditCallback;
    private List<Task> taskList = new ArrayList<>();

    public interface OnTaskEditListener {
        public void taskUpdated( String taskTitle, String taskContent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_view_list_layout, container, false);
        ListView taskListView = (ListView) view.findViewById(R.id.taskListView);
        taskList = (List<Task>) getArguments().getSerializable("TASK_LIST");
        taskListView.setAdapter(new ArrayAdapter<Task>(inflater.getContext(), R.layout.task_item_layout, taskList));
        return view;
//        if (container != null) {
//            container.removeAllViews();
//        }


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            taskEditCallback  = (FragmentTaskList.OnTaskEditListener) getActivity();
        }
        catch (Exception e) {
            throw new ClassCastException(context.toString()+ " must implement OnTaskEditListener");
        }
    }
}
