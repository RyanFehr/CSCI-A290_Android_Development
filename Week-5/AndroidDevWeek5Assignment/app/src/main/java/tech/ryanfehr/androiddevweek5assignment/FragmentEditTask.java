package tech.ryanfehr.androiddevweek5assignment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Ryan on 9/23/2017.
 */

public class FragmentEditTask extends Fragment {

    OnTaskUpdateListener taskCallback;
    String originalTitle;
    String originalContent;
    Date originalDate;

    public interface OnTaskUpdateListener {
        public void taskUpdated( String taskTitle, String taskContent, Date taskDate);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {

        }
        View view = inflater.inflate(R.layout.edit_task_layout, container, false);
        Task task = (Task) getArguments().getSerializable("TASK");
        final EditText titleEditText = (EditText) view.findViewById(R.id.titleEditText);
        titleEditText.setText(task.getTitle());
        final EditText contentEditText = (EditText) view.findViewById(R.id.contentEditText);
        contentEditText.setText(task.getContent());
        originalTitle = task.getTitle();
        originalContent = task.getContent();
        originalDate = task.getFinishByDate();

        Button saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titleEditText.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "You must have a title for this Tasks", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(contentEditText.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "You must have content for this Tasks", Toast.LENGTH_SHORT).show();
                    return;
                }
                taskCallback.taskUpdated(titleEditText.getText().toString(), contentEditText.getText().toString(), originalDate);
                getFragmentManager().popBackStack();
            }
        });

        Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskCallback.taskUpdated(originalTitle, originalContent, originalDate);
                getFragmentManager().popBackStack();
            }
        });

        Button deleteButton = (Button) view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskCallback.taskUpdated("delete", "delete", null); // null date deletes
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            taskCallback  = (OnTaskUpdateListener) getActivity();
        }
        catch (Exception e) {
            throw new ClassCastException(context.toString()+ " must implement OnTaskUpdateListener");
        }
    }

}
