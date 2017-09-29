package tech.ryanfehr.androiddevclass9application;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager SM;
    private HashMap<Integer, String> sensorMap = new HashMap<>();
    {
        sensorMap.put(Sensor.TYPE_ACCELEROMETER, Sensor.STRING_TYPE_ACCELEROMETER);
        sensorMap.put(Sensor.TYPE_AMBIENT_TEMPERATURE, Sensor.STRING_TYPE_AMBIENT_TEMPERATURE);
        sensorMap.put(Sensor.TYPE_GYROSCOPE, Sensor.STRING_TYPE_GYROSCOPE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView listTextView = (TextView) findViewById(R.id.listTextView);
        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SensorActivity.class));
            }
        });

        // Get Sensor Manager
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get list of all Sensors in your device
        List<Sensor> sensorList = SM.getSensorList(Sensor.TYPE_ALL);
        StringBuilder message = new StringBuilder();
        message.append("Sensors on this device are:\n\n");
        for(Sensor sensor : sensorList){
            message.append("Name: "+sensor.getName()+"\nType: "+sensorMap.get(sensor.getType())+"\n");
        }

        listTextView.setText(message);
    }
}
