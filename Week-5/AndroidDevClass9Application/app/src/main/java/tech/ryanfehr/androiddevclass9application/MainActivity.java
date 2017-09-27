package tech.ryanfehr.androiddevclass9application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TOUCH_TEST = "";
    private static final String GESTURE_TEST = "";
    TextView singleTextView;
    TextView doubleTextView;
    TextView scaleTextView;
    GestureDetector gestureDetector;
    ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleTextView = (TextView) findViewById(R.id.singleTextView);
        doubleTextView = (TextView) findViewById(R.id.doubleTextView);
        scaleTextView = (TextView) findViewById(R.id.scaleTextView);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);

        mainLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleUserTouch(event);
                return false;
            }

            private void handleUserTouch(MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                String actionString;
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        actionString = "DOWN";
                        Log.v(TOUCH_TEST, "ACTION WAS DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        actionString = "POINTER_DOWN";
                        Log.v(TOUCH_TEST, "ACTION WAS POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        actionString = "POINTER_UP";
                        Log.v(TOUCH_TEST, "ACTION WAS POINTER_UP");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        actionString = "MOVE";
                        Log.v(TOUCH_TEST, "ACTION WAS MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        actionString = "UP";
                        Log.v(TOUCH_TEST, "ACTION WAS UP");
                        break;
                    default:
                        actionString = "NONE";
                        Log.v(TOUCH_TEST, "ACTION WAS NONE");
                        break;
                }
                String touchStatus = "Action " + actionString + " X:" + x + ", Y:" + y;
                singleTextView.setText(touchStatus);
            }
        });
    }
}
