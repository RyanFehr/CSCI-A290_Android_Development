package tech.ryanfehr.androiddevweek3assignment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String[] COLORS = new String[]{"Red", "Green", "Blue", "White", "Black"};
    private static final int MAX_LOGINS = 3;
    private static int loginAttempts = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeBackgroundColorAutoCompleteTextView();
    }

    private void initializeBackgroundColorAutoCompleteTextView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COLORS);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.backgroundColorAutoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundColorAutoCompleteTextViewOnDismissListener();
            }
        });
    }

    private void backgroundColorAutoCompleteTextViewOnDismissListener() {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.backgroundColorAutoCompleteTextView);

        switch (autoCompleteTextView.getText().toString()) {
            case "Red":
                findViewById(R.id.loginConstraintLayout).getRootView().setBackgroundColor(Color.RED);
                break;
            case "Green":
                findViewById(R.id.loginConstraintLayout).getRootView().setBackgroundColor(Color.GREEN);
                break;
            case "Blue":
                findViewById(R.id.loginConstraintLayout).getRootView().setBackgroundColor(Color.BLUE);
                break;
            case "White":
                findViewById(R.id.loginConstraintLayout).getRootView().setBackgroundColor(Color.WHITE);
                break;
            case "Black":
                findViewById(R.id.loginConstraintLayout).getRootView().setBackgroundColor(Color.BLACK);
                break;
        }
    }


    public void loginButtonOnClickEventListener(View view) {
        EditText firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
        EditText backgroundColorEditText = (EditText) findViewById(R.id.backgroundColorAutoCompleteTextView);

        if (verifyUser()) {
            if(backgroundColorEditText.getText().toString().equals("")) {
                Toast.makeText(this, "Please select a favorite color", Toast.LENGTH_SHORT).show();
                return;
            }
            SharedPreferences appPreferences = getSharedPreferences("Calculator", MODE_PRIVATE);
            SharedPreferences.Editor appEditor = appPreferences.edit();
            appEditor.putString("FIRST_NAME", firstNameEditText.getText().toString());
            appEditor.putString("LAST_NAME", lastNameEditText.getText().toString());
            appEditor.putString("EMAIL", emailEditText.getText().toString());
            appEditor.putString("BACKGROUND_COLOR", backgroundColorEditText.getText().toString());
            appEditor.commit();
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivityForResult(intent, 40);
        } else {
            loginAttempts++;
            TextView loginAttemptsTextView = (TextView) findViewById(R.id.loginAttemptsTextView);
            loginAttemptsTextView.setText("Login Attempts: " + loginAttempts);
            if (loginAttempts >= MAX_LOGINS) { // Disables button if 3 or more failed login attempts
                Button loginButton = (Button) findViewById(R.id.loginButton);
                loginButton.setEnabled(false);
            }
        }


    }

    private boolean verifyUser() {
        EditText firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Verifies that all fields are non-empty, email is valid, and password is password
        if (!firstName.equals("") && !lastName.equals("") && password.equals("password")) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return true;
            else
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    // Handles when a activity is resolved
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        switch (requestCode) {
            case 40:
                break;
        }
    }

    // Navigates to activity 2 without the need for credentials for ease of testing
    public void quickButtonOnClickEventListener(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivityForResult(intent, 40);
    }
}
