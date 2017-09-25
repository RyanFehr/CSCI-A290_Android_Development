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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ryan on 9/25/2017.
 */

public class FragmentNewTask extends Fragment{

    OnTaskNewListener taskNewCallback;

    public interface OnTaskNewListener {
        public void taskAdded( String taskTitle, String taskContent, Date taskDate);
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
        final EditText newDateEditText = (EditText) view.findViewById(R.id.newDateEditText);

        Button saveNewButton = (Button) view.findViewById(R.id.saveNewButton);
        saveNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newTitleEditText.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "You must have a title for new Tasks", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(newContentEditText.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "You must have content for new Tasks", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(newDateEditText.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "You must have a date for this Task", Toast.LENGTH_SHORT).show();
                    return;
                }
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                String dateString = newDateEditText.getText().toString();
                Date futureDate = null;
                try {
                    futureDate = df.parse(dateString);
                } catch (ParseException e) {
                    Toast.makeText(getActivity(), "Invalid date formatting", Toast.LENGTH_SHORT).show();
                    return;
                }
                Date today = new Date();
                Calendar futureCalendar = Calendar.getInstance();
                Calendar todayCalendar = Calendar.getInstance();
                futureCalendar.setTime(futureDate);
                todayCalendar.setTime(today);

                if (!todayCalendar.before(futureCalendar)) {
                    Toast.makeText(getActivity(), "Please choose a date in the future", Toast.LENGTH_SHORT).show();
                    return;
                }


                taskNewCallback.taskAdded(newTitleEditText.getText().toString(), newContentEditText.getText().toString(), futureDate);
                getFragmentManager().popBackStack();
            }
        });

        Button cancelNewButton = (Button) view.findViewById(R.id.cancelNewButton);
        cancelNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
