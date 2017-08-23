package tech.ryanfehr.androiddevclass2application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Welcome, Ryan Fehr", Toast.LENGTH_SHORT).show();
    }

    public void OnClickSumOperandsButtonEventHandler(View view) {
        TextView firstOperand = (TextView)findViewById(R.id.firstOperand);
        TextView secondOperand = (TextView)findViewById(R.id.secondOperand);
        TextView quotient = (TextView)findViewById(R.id.quotient);
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
}
