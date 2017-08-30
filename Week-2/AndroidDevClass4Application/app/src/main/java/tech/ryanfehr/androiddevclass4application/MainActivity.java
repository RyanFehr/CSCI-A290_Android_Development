package tech.ryanfehr.androiddevclass4application;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDisplayButtonEventHandler(View view) {
        Date currentDate = new Date();
        String timeString = "Time: " + currentDate.toString();
        TextView displayTextView = (TextView) findViewById(R.id.displayTextView);
        displayTextView.setText(timeString);

        //Save Shared Preferences
        SharedPreferences appPreferences = getSharedPreferences("Class4Application", MODE_PRIVATE);
        SharedPreferences.Editor appEditor = appPreferences.edit();
        appEditor.putString("CURRENT_TIME", timeString);
        appEditor.commit();
    }

    public void onClickDisplaySavedButtonHandler(View view) {

        //Read Shared Preferences
        SharedPreferences appPreferences = getSharedPreferences("Class4Application", MODE_PRIVATE);
        String timeString = appPreferences.getString("CURRENT_TIME", "No date found");
        TextView displayTextView = (TextView) findViewById(R.id.displaySavedTextView);
        displayTextView.setText(timeString);
    }
}
