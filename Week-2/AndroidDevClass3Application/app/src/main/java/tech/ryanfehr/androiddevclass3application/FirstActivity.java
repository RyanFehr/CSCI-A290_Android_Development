package tech.ryanfehr.androiddevclass3application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Don't click me", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        Toast.makeText(this, "Welcome, Ryan Fehr", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnClickSumOperandsButtonEventHandler(View view) {
        TextView firstOperand = (TextView) findViewById(R.id.firstOperand);
        TextView secondOperand = (TextView) findViewById(R.id.secondOperand);
        TextView quotient = (TextView) findViewById(R.id.quotient);
        try {
            double firstOperandValue = Double.valueOf(firstOperand.getText().toString());
            double secondOperandValue = Double.valueOf(secondOperand.getText().toString());
            double quotientValue = firstOperandValue + secondOperandValue;
            quotient.setText("    "+quotientValue);
        }
        catch (Exception e) {
            quotient.setText("    Error");
        }
    }
    public void OnClickGoButtonEventHandler(View view) {
        Button goButton = (Button) findViewById(R.id.goButton);
        Intent intent = new Intent(this, SecondActivity.class);
        TextView quotient = (TextView) findViewById(R.id.quotient);
        intent.putExtra("USER_INPUT", quotient.getText().toString());
        startActivity(intent);
    }
}
