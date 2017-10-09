package tech.ryanfehr.androiddevweek7assignment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PointF firstTouch;
    Matrix ball = new Matrix();
    Matrix temp = new Matrix();
    ImageView imageViewBall;
    TextView textView;
    OrientationEventListener myOrientationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        imageViewBall = (ImageView) findViewById(R.id.imageViewBall);

        imageViewBall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleUserTouch(event);
                return true;
            }
        });

        myOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL){
            @Override
            public void onOrientationChanged(int arg0) {
                Log.e("ORNAMENT", arg0+"");
                getRotation(MainActivity.this, arg0);
            }};

        if (myOrientationEventListener.canDetectOrientation()){
            myOrientationEventListener.enable();
        }
        else{
            finish();
        }




//        Log.d("ORIENTATION", getRotation(MainActivity.this));
    }

    private void handleUserTouch(MotionEvent event) {
        int action = event.getActionMasked();

        switch(action)  {
            case MotionEvent.ACTION_DOWN:
            {
                temp.set(ball);
                textView.setText("Down");
                firstTouch = new PointF();
                firstTouch.set(event.getX(), event.getY());
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                ball.set(temp);
                textView.setText("Move");
                PointF move = new PointF();
                move.set(event.getX(), event.getY());
                ball.postTranslate(event.getX() - firstTouch.x, event.getY() - firstTouch.y);

                break;
            }
        }
        imageViewBall.setImageMatrix(ball);
        Bitmap bmp = Bitmap.createBitmap(imageViewBall.getWidth(), imageViewBall.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        imageViewBall.draw(canvas);
    }



    public String getRotation(Context context, int angle){
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        Log.e("rotation " , angle+"");
        RelativeLayout main = (RelativeLayout) findViewById(R.id.mainLayout);
        switch (angle) {
            case 0:
                main.setBackground(getDrawable(R.drawable.arrow));
                return "portrait 0";
            case 90:
                main.setBackground(getDrawable(R.drawable.arrow_right));
                return "landscape 90";
            case 180:
                main.setBackground(getDrawable(R.drawable.arrow_right));
                return "reverse portrait 180";
            default:
                main.setBackground(getDrawable(R.drawable.arrow_left));
                return "reverse landscape 270";
        }
    }
}
