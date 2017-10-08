package tech.ryanfehr.androiddevweek7assignment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PointF firstTouch;
    Matrix ball = new Matrix();
    Matrix temp = new Matrix();
    ImageView imageViewBall;
    TextView textView;

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
}
