package tech.ryanfehr.androiddevclass11application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    TextView fileTextView;
    EditText fileEditText;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileTextView = (TextView) findViewById(R.id.fileTextView);
        File dir = getFilesDir();
        path = dir.getAbsolutePath();
        fileTextView.setText(path);
    }

    public void writeButtonOnClickEventListener(View view) throws IOException {
        fileEditText = (EditText) findViewById(R.id.fileEditText);
        String content = fileEditText.getText().toString();
        FileOutputStream fos = openFileOutput("outputText.txt", MODE_PRIVATE);
        fos.write(content.getBytes());
        fos.close();
        fileTextView.setText("File written in internal storage\n"+ path);
    }

    public void readButtonOnClickEventListener(View view) throws IOException {
        FileInputStream fis = openFileInput("outputText.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer sb = new StringBuffer();
        while(bis.available() != 0) {
            char c = (char) bis.read();
            sb.append(c);
        }
        fileTextView.setText("Contents of the read file " + sb.toString());
        bis.close();
        fis.close();
    }

    public void writeJSONButtonOnClickEventListener(View view) throws JSONException, IOException {
        JSONArray data = new JSONArray();
        JSONObject student = new JSONObject();
        student.put("NAME", "Jusin");
        student.put("ID", 34);
        student.put("GRADE", 58.9);
        data.put(student);

        student = new JSONObject();
        student.put("NAME", "Ryan");
        student.put("ID", 8);
        student.put("GRADE", .26);
        data.put(student);

        String JSONText = data.toString();

        fileEditText = (EditText) findViewById(R.id.fileEditText);
        String content = fileEditText.getText().toString();
        FileOutputStream fos = openFileOutput("jsonFile", MODE_PRIVATE);
        fos.write(content.getBytes());
        fos.close();
        fileTextView.setText("JSON File stored\n"+ path);
    }

    public void readJSONButtonOnClickEventListener(View view) throws IOException, JSONException {
        FileInputStream fis = openFileInput("jsonFile");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer sb = new StringBuffer();
        while(bis.available() != 0) {
            char c = (char) bis.read();
            sb.append(c);
        }
        bis.close();
        fis.close();

        String output = "";
        JSONArray data = new JSONArray(sb.toString());
        for(int i = 0; i < data.length(); i++) {
            JSONObject student = data.getJSONObject(i);
            output += student.get("NAME").toString();

        }


        fileTextView.setText("Contents of the json file read" + output);



    }
}
