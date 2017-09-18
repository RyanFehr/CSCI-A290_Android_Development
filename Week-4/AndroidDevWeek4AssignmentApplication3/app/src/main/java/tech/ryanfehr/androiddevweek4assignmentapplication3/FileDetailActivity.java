package tech.ryanfehr.androiddevweek4assignmentapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FileDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_detail);

        Intent intent = getIntent();
        FileData fileData = (FileData) intent.getSerializableExtra("FILE");

        TextView contentTextView = (TextView) findViewById(R.id.contentsTextView);
        contentTextView.setText(fileData.getContent());
        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        nameTextView.setText(fileData.getName());
    }
}
