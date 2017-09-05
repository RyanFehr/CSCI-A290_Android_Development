package tech.ryanfehr.androiddevweek3assignment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        SharedPreferences appPreferences = getSharedPreferences("Calculator", MODE_PRIVATE);
        String firstName = appPreferences.getString("FIRST_NAME", "No First Name");
        String lastName = appPreferences.getString("LAST_NAME", "No Last Name");
        String email = appPreferences.getString("EMAIL", "No Email");
        String backgroundColor = appPreferences.getString("BACKGROUND_COLOR", "No Color");
        TextView welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcomeTextView.setText(" Welcome, " + firstName + " " + lastName + "\n " + email);
        setPreferredBackgroundColor(backgroundColor);
    }

    private void setPreferredBackgroundColor(String backgroundColor) {
        switch(backgroundColor) {
            case "Red":
                findViewById(R.id.calculatorConstraintLayout).getRootView().setBackgroundColor(Color.RED);
                break;
            case "Green":
                findViewById(R.id.calculatorConstraintLayout).getRootView().setBackgroundColor(Color.GREEN);
                break;
            case "Blue":
                findViewById(R.id.calculatorConstraintLayout).getRootView().setBackgroundColor(Color.BLUE);
                break;
            case "White":
                findViewById(R.id.calculatorConstraintLayout).getRootView().setBackgroundColor(Color.WHITE);
                break;
            case "Black":
                findViewById(R.id.calculatorConstraintLayout).getRootView().setBackgroundColor(Color.BLACK);
                break;
        }
    }
}
