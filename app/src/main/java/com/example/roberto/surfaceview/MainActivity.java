package com.example.roberto.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.layout);
        final MyView myView = new MyView(getApplicationContext());
        relativeLayout.addView(myView);
    }



    private class MyView extends SurfaceView implements SurfaceHolder.Callback{

        private final Paint mPainter = new Paint();
        private final SurfaceHolder mSurfaceHolder;
        private final Bitmap bitmap;
        private Thread thread;

        public MyView(Context context) {
            super(context);
            mSurfaceHolder = getHolder();
            mSurfaceHolder.addCallback(this);
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.mail);

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

/*
            Canvas canvas = mSurfaceHolder.lockCanvas();
            if (canvas!=null)
                Draw(canvas,100,100);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
*/

            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Canvas canvas;
                    for(int i=0;i<10;i++){
                    canvas = mSurfaceHolder.lockCanvas();
                    if (canvas!=null)
                        Draw(canvas,10*i,10*i);
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {};
                    }
                }
            });

            thread.start();



        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

        protected void Draw(Canvas canvas,int left,int top){
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, left, top, mPainter);

            Log.i("INFO",Integer.toString(left)+" "+Integer.toString(top));

        }

    }
}
