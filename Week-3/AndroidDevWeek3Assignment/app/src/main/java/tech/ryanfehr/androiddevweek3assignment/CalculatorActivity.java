package tech.ryanfehr.androiddevweek3assignment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class CalculatorActivity extends AppCompatActivity {

    private static boolean acceptingOperation = false;
    private static final Character[] OPERATION_SET = new Character[]{'+', '-', '/', '*'};
    private static HashSet<Character> OPERATIONS = new HashSet(Arrays.asList(OPERATION_SET));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Displays the contents of shared preferences on the screen
        SharedPreferences appPreferences = getSharedPreferences("Calculator", MODE_PRIVATE);
        String firstName = appPreferences.getString("FIRST_NAME", "No First Name");
        String lastName = appPreferences.getString("LAST_NAME", "No Last Name");
        String email = appPreferences.getString("EMAIL", "No Email");
        String backgroundColor = appPreferences.getString("BACKGROUND_COLOR", "No Color");
        TextView welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcomeTextView.setText(" Welcome, " + firstName + " " + lastName + "\n " + email);

        // Sets background color according to shared preferences
        setPreferredBackgroundColor(backgroundColor);
    }

    // Sets background color of the root view based  on a string
    private void setPreferredBackgroundColor(String backgroundColor) {
        switch (backgroundColor) {
            case "Red":
                findViewById(R.id.calculatorConstraintLayout).getRootView().setBackgroundColor(Color.RED);
                break;
            case "Green":
                findViewById(R.id.calculatorConstraintLayout).getRootView().setBackgroundResource(android.R.color.holo_green_light);
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

    // Parses the equation in the equation edit text
    private void equationParse() {
        TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
        String equation = equationTextView.getText().toString();


        for (int i = equation.length() - 1; i >= 0; i--) {
            if (OPERATIONS.contains(equation.charAt(i))) {
                acceptingOperation = false;
                return;
            }
        }
        acceptingOperation = true;
        return;
    }

    // Adds a operand to the equation
    public void addOperand(String operand) {
        TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
        String equation = equationTextView.getText().toString();
        if (equation.length() > 0) {
            if (equation.charAt(equation.length() - 1) == '²') {
                Toast.makeText(CalculatorActivity.this, "x² must be followed by an operation", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        equationTextView.setText(equationTextView.getText().toString() + operand);
    }

    // Adds a operation to the equation if it is valid syntax
    public void addOperation(String operation) {
        equationParse();
        if (acceptingOperation) {
            TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
            equationTextView.setText(equationTextView.getText().toString() + operation);
            acceptingOperation = false;
        } else
            // Toast that we are expecting an operand
            Toast.makeText(CalculatorActivity.this, "An operation has already been used", Toast.LENGTH_SHORT).show();
    }

    // Removes the last operand/operation from the equation
    public void backButtonOnClickEventListener(View view) {
        TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
        String equation = equationTextView.getText().toString();
        if (equation.length() > 0) {
            char lastChar = equation.charAt(equation.length() - 1);
            if (lastChar == ' ') equation = equation.substring(0, equation.length() - 3);
            else equation = equation.substring(0, equation.length() - 1);
        }
        equationTextView.setText(equation);
    }

    // Clears the current equation
    public void clearButtonOnClickEventListener(View view) {
        TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
        equationTextView.setText("");
        acceptingOperation = true;
    }

    public void additionButtonOnClickEventListener(View view) {
        addOperation(" + ");
    }

    public void subtractionButtonOnClickEventListener(View view) {
        addOperation(" - ");
    }

    public void divisionButtonOnClickEventListener(View view) {
        addOperation(" / ");
    }

    public void multiplicationButtonOnClickEventListener(View view) {
        addOperation(" * ");
    }

    // Adds a power of 2 symbol if it is valid
    public void squareButtonOnClickEventListener(View view) {
        TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
        String equation = equationTextView.getText().toString();
        if (equation.length() > 0) {
            if (Character.isDigit(equation.charAt(equation.length() - 1))) {
                equationTextView.setText(equationTextView.getText().toString() + "²");
            } else {
                Toast.makeText(CalculatorActivity.this, "² must come after operands", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    // Adds a square root symbol if it is valid
    public void squareRootButtonOnClickEventListener(View view) {
        TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
        String equation = equationTextView.getText().toString();
        if (equation.length() > 0) {
            if (Character.isDigit(equation.charAt(equation.length() - 1))) {
                Toast.makeText(CalculatorActivity.this, "√ must come before operands", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        equationTextView.setText(equationTextView.getText().toString() + "√");
    }

    public void button0OnClickEventListener(View view) {
        addOperand("0");
    }

    public void button1OnClickEventListener(View view) {
        addOperand("1");
    }

    public void button2OnClickEventListener(View view) {
        addOperand("2");
    }

    public void button3OnClickEventListener(View view) {
        addOperand("3");
    }

    public void button4OnClickEventListener(View view) {
        addOperand("4");
    }

    public void button5OnClickEventListener(View view) {
        addOperand("5");
    }

    public void button6OnClickEventListener(View view) {
        addOperand("6");
    }

    public void button7OnClickEventListener(View view) {
        addOperand("7");
    }

    public void button8OnClickEventListener(View view) {
        addOperand("8");
    }

    public void button9OnClickEventListener(View view) {
        addOperand("9");
    }

    public void equalButtonOnClickEventListener(View view) {
        TextView equationTextView = (TextView) findViewById(R.id.equationTextView);
        String equation = equationTextView.getText().toString();
        if (equation.indexOf('=') > -1) {
            Toast.makeText(CalculatorActivity.this, "Remove = before re-evaluating", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] equationSplit = equation.split("\\s");
        if (equationSplit.length > 2 && operandValid(equationSplit[0]) && operandValid(equationSplit[2])) {
            double operandOne = operandParse(equationSplit[0]);
            String operation = equationSplit[1];
            double operandTwo = operandParse(equationSplit[2]);
            double result = 0;
            switch (operation) {
                case "+":
                    result = operandOne + operandTwo;
                    break;
                case "-":
                    result = operandOne - operandTwo;
                    break;
                case "/": {
                    if (operandTwo == 0) {
                        Toast.makeText(CalculatorActivity.this, "You can't divide by 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = operandOne / operandTwo;
                    break;
                }
                case "*":
                    result = operandOne * operandTwo;
                    break;
            }
            equation = equation + " = " + result;
            equationTextView.setText(equation);
        } else {
            Toast.makeText(CalculatorActivity.this, "Invalid equation formatting", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public double operandParse(String operand) {
        if (operand.charAt(operand.length() - 1) == '²') {
            if (operand.charAt(0) == '√') {
                return Double.valueOf(operand.substring(1, operand.length() - 1));
            } else {
                double num = Double.valueOf(operand.substring(0, operand.length() - 1));
                return num * num;
            }
        }
        if (operand.charAt(0) == '√') {
            return Math.sqrt(Double.valueOf(operand.substring(1, operand.length())));
        }
        return Double.valueOf(operand);
    }

    public boolean operandValid(String operand) {
        if (operand.length() > 1) {
            return operand.substring(1, operand.length()).indexOf('√') == -1
                    && operand.substring(0, operand.length() - 1).indexOf('²') == -1;
        } else {
            return !operand.equals("√");
        }

    }

    public void dateButtonOnClickEventListener(View view) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        EditText dateEditText = (EditText) findViewById(R.id.dateEditText);
        String dateString = dateEditText.getText().toString();
        Date pastDate = null;
        try {
            pastDate = df.parse(dateString);
        } catch (ParseException e) {
            Toast.makeText(CalculatorActivity.this, "Invalid date formatting", Toast.LENGTH_SHORT).show();
            return;
        }
        Date today = new Date();
        Calendar pastCalendar = Calendar.getInstance();
        Calendar todayCalendar = Calendar.getInstance();
        pastCalendar.setTime(pastDate);
        todayCalendar.setTime(today);

        int weekdays = 0;
        int weekends = 0;
        if (!pastCalendar.before(todayCalendar)) {
            Toast.makeText(CalculatorActivity.this, "Please choose a date in the past", Toast.LENGTH_SHORT).show();
            return;
        }
        while (pastCalendar.before(todayCalendar)) {
            if ((Calendar.SATURDAY == pastCalendar.get(Calendar.DAY_OF_WEEK))
                    || (Calendar.SUNDAY == pastCalendar.get(Calendar.DAY_OF_WEEK))) {
                weekends++;
            } else {
                weekdays++;
            }
            pastCalendar.add(Calendar.DATE, 1);
        }
        TextView dateTextView = (TextView) findViewById(R.id.dayTextView);
        dateTextView.setText("Days between - Working days: " + weekdays + " Weekend days: " + weekends);
    }
}
