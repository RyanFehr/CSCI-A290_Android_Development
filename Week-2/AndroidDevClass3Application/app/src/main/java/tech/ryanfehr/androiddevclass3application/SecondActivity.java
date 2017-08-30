package tech.ryanfehr.androiddevclass3application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        TextView transitionMessage = (TextView) findViewById(R.id.receivedValueText);
        transitionMessage.setText(intent.getStringExtra("USER_INPUT"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void OnClickDoubleButtonEventHandler(View view) {
        double updatedValue = Double.parseDouble(((TextView) findViewById(R.id.receivedValueText)).getText().toString());
        updatedValue *= 2;
        Intent intent = new Intent();
        intent.putExtra("UPDATED_VALUE", updatedValue);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void OnClickBackButtonEventHandler(View view) {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
