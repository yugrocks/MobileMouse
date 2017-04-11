package com.yugal.android.mobilemouse;


import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ActivitySwipeDetector  implements View.OnTouchListener {

    static final String logTag = "ActivitySwipeDetector";
    private Activity activity;
    static final int MIN_DISTANCE = 5;
    private float downX, downY, upX, upY;

    public ActivitySwipeDetector(Activity activity){
        this.activity = activity;
    }

    public void onRightToLeftSwipe(float delta){
        Log.i(logTag, "RightToLeftSwipe!");
        Toast.makeText(activity, String.format("RightToLeftSwipe %s", delta), Toast.LENGTH_SHORT).show();
        //activity.doSomething();
    }

    public void onLeftToRightSwipe(float delta){
        Log.i(logTag, "LeftToRightSwipe!");
        Toast.makeText(activity, String.format("onLeftToRightSwipe %s", delta), Toast.LENGTH_SHORT).show();
        //activity.doSomething();
    }

    public void onTopToBottomSwipe(Float delta){
        Log.i(logTag, "onTopToBottomSwipe!");
        Toast.makeText(activity, String.format("onTopToBottomSwipe %s", delta), Toast.LENGTH_SHORT).show();
        //activity.doSomething();
    }

    public void onBottomToTopSwipe(Float delta){
        Log.i(logTag, "onBottomToTopSwipe!");
        Toast.makeText(activity, String.format("onBottomToTopSwipe %s", delta), Toast.LENGTH_SHORT).show();
        //activity.doSomething();
    }

    public boolean onTouch(View v, MotionEvent event) {


        switch(event.getAction()){

            case MotionEvent.ACTION_MOVE:
                Toast.makeText(activity, String.format("Touched at (%s,%s)",event.getX(),event.getY()), Toast.LENGTH_SHORT).show();

                //FINGER IS MOVING
                //Do your calculations here, using the x and y positions relative to the starting values you get in ACTION_DOWN
                return true;


            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;
                 // swipe horizontal?

                    // left or right
                if(Math.abs(deltaX)>MIN_DISTANCE) {
                    if (deltaX < 0) {
                        this.onLeftToRightSwipe(Math.abs(deltaX));
                    }
                    if (deltaX > 0) {
                        this.onRightToLeftSwipe(deltaX);
                    }
                }
                // swipe vertical?
                if(Math.abs(deltaY)>MIN_DISTANCE) {


                    if (deltaY < 0) {
                        this.onTopToBottomSwipe(deltaY);
                    }
                    if (deltaY > 0) {
                        this.onBottomToTopSwipe(deltaY);
                    }

                }
                else{
                    Toast.makeText(activity, String.format("Touched at (%s,%s)",event.getX(),event.getY()), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        }
        return false;
    }

}