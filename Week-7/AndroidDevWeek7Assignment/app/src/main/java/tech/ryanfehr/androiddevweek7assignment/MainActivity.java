package tech.ryanfehr.androiddevweek7assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleUserTouch(event);
                return true;
            }
        });
    }

    private void handleUserTouch(MotionEvent event) {
        float xCoord = event.getX();
        float yCoord = event.getY();
        int action = event.getActionMasked();

        switch(action)  {
            case MotionEvent.ACTION_DOWN:
                // Do Stuff
                break;
            case MotionEvent.ACTION_UP:
                // Do Stuff
                break;
        }
    }
}
