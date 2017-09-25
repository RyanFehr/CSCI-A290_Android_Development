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

/**
 * Created by Ryan on 9/23/2017.
 */

public class FragmentEditTask extends Fragment {

    OnTaskUpdateListener taskCallback;

    public interface OnTaskUpdateListener {
        public void taskUpdated( String taskTitle, String taskContent);
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

        Button saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskCallback.taskUpdated(titleEditText.getText().toString(), contentEditText.getText().toString());
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
