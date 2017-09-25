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
 * Created by Ryan on 9/25/2017.
 */

public class FragmentNewTask extends Fragment{

    OnTaskNewListener taskNewCallback;

    public interface OnTaskNewListener {
        public void taskAdded( String taskTitle, String taskContent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
//            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.new_task_layout, container, false);
        final EditText newTitleEditText = (EditText) view.findViewById(R.id.newTitleEditText);
        final EditText newContentEditText = (EditText) view.findViewById(R.id.newContentEditText);

        Button saveNewButton = (Button) view.findViewById(R.id.saveNewButton);
        saveNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskNewCallback.taskAdded(newTitleEditText.getText().toString(), newContentEditText.getText().toString());
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            taskNewCallback  = (OnTaskNewListener) getActivity();
        }
        catch (Exception e) {
            throw new ClassCastException(context.toString()+ " must implement OnTaskNewListener");
        }
    }
}
