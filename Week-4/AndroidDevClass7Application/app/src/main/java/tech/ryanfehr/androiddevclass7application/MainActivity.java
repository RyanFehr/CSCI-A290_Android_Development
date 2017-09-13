package tech.ryanfehr.androiddevclass7application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("LIFE_CYCLE", "Inside onCreate()");
        Toast.makeText(getBaseContext(), "Inside onCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("LIFE_CYCLE", "Inside onStart()");
        Toast.makeText(getBaseContext(), "Inside onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("LIFE_CYCLE", "Inside onResume()");
        Toast.makeText(getBaseContext(), "Inside onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("LIFE_CYCLE", "Inside onPause()");
        Toast.makeText(getBaseContext(), "Inside onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("LIFE_CYCLE", "Inside onStop()");
        Toast.makeText(getBaseContext(), "Inside onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("LIFE_CYCLE", "Inside onDestroy()");
        Toast.makeText(getBaseContext(), "Inside onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v("LIFE_CYCLE", "Inside onSaveInstanceState()");
        Toast.makeText(getBaseContext(), "Inside onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        EditText editText = (EditText) findViewById(R.id.editText);
        String dynamicValue = editText.getText().toString();
        outState.putString("DYNAMIC_VALUE", dynamicValue);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v("LIFE_CYCLE", "Inside onRestoreInstanceState()");
        Toast.makeText(getBaseContext(), "Inside onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(savedInstanceState.getString("DYNAMIC_VALUE"));
    }
}
