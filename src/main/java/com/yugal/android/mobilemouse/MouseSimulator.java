package com.yugal.android.mobilemouse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MouseSimulator extends AppCompatActivity {

    public TextView mousepad;
    public Socket client;
    public DataOutputStream output;
    public Button vol_down,mute,up,down,vol_up,left,right;
    public boolean vol_down_stop;
    public boolean vol_up_stop,up_stop,down_stop,right_stop,left_stop;
    ExecutorService threadExecutor = Executors.newCachedThreadPool();
    public String ip_address;
    SharedPreferences sharedPref;

    public void onDestroy(){
        super.onDestroy();
        try{
        output.writeBytes("__sbk__400");}catch(IOException r){}catch(NullPointerException k){}
        try {
            if (client !=null && output!=null){
                client.close();
                output.close();}
        }catch(IOException e){}
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivitySwipeDetector activitySwipeDetector = new ActivitySwipeDetector(this);
        final ExecutorService threadExecutor = Executors.newCachedThreadPool();
        vol_down_stop=false;
        vol_up_stop=false;
        up_stop=false;
        down_stop=false;
        left_stop=false;
        right_stop=false;
        mousepad = (TextView)this.findViewById(R.id.mousepad);
        mousepad.setOnTouchListener(activitySwipeDetector);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        ip_address=sharedPref.getString("ip_addr","");

        run_client();

        Button myButton1=(Button)findViewById(R.id.lmb);
        Button myButton2=(Button) findViewById(R.id.rmb);
        vol_down=(Button)findViewById(R.id.vol_down);
        mute=(Button)findViewById(R.id.mute);
        vol_up=(Button)findViewById(R.id.vol_up);
        up=(Button)findViewById(R.id.up);
        down=(Button)findViewById(R.id.down);
        left=(Button)findViewById(R.id.left);
        right=(Button)findViewById(R.id.right);

        vol_down.setOnTouchListener( new View.OnTouchListener() {
            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        try{
                            vol_down.setBackgroundColor(0x77FFFF33);
                            output.writeBytes("b");output.flush();
                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!vol_down_stop){
                                        try{
                                            Thread.sleep(200);
                                            output.writeBytes("b");output.flush();
                                            }catch(IOException e){}catch(InterruptedException r){}
                                    }
                                    vol_down_stop=false;
                                }
                            });
                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                            vol_down.setBackgroundResource(R.drawable.vol_down1);
                            vol_down_stop=true;
                        break;
                }
                return true;
            }
        });

        vol_up.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        try{
                            vol_up.setBackgroundColor(0x77FFFF33);
                            output.writeBytes("a");output.flush();
                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!vol_up_stop){
                                        try{
                                        output.writeBytes("a");output.flush();
                                        Thread.sleep(200);}catch(IOException e){}catch(InterruptedException r){}
                                    }
                                    vol_up_stop=false;
                                }
                            });
                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        vol_up.setBackgroundResource(R.drawable.vol_up1);
                        vol_up_stop=true;
                        break;
                }
                return true;
            }

        });

        mute.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        try{
                            mute.setBackgroundColor(0x77FFFF33);
                            output.writeBytes("c");output.flush();
                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        mute.setBackgroundResource(R.drawable.mute);
                        break;
                }
                return true;
            }

        });

        up.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        try{
                            up.setBackgroundColor(0x55FFFF33);
                            output.writeBytes("g");output.flush();

                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!up_stop){
                                        try{
                                            output.writeBytes("g");output.flush();
                                            Thread.sleep(200);}catch(IOException e){}catch(InterruptedException r){}
                                    }
                                    up_stop=false;
                                }
                            });
                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        up.setBackgroundResource(R.drawable.up);
                        up_stop=true;
                        break;
                }
                return true;
            }

        });

        down.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        try{
                            down.setBackgroundColor(0x55FFFF33);
                            output.writeBytes("h");output.flush();
                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!down_stop){
                                        try{
                                            output.writeBytes("h");output.flush();
                                            Thread.sleep(200);}catch(IOException e){}catch(InterruptedException r){}
                                    }
                                    down_stop=false;
                                }
                            });
                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        down.setBackgroundResource(R.drawable.down);
                        down_stop=true;
                        break;
                }
                return true;
            }

        });

        left.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        try{
                            left.setBackgroundColor(0x55FFFF33);
                            output.writeBytes("i");output.flush();

                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!left_stop){
                                        try{
                                            output.writeBytes("i");output.flush();
                                            Thread.sleep(200);}catch(IOException e){}catch(InterruptedException r){}
                                    }
                                    left_stop=false;
                                }
                            });


                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        left.setBackgroundResource(R.drawable.left);
                        left_stop=true;
                        break;
                }
                return true;
            }

        });

        right.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        try{
                            right.setBackgroundColor(0x55FFFF33);
                            output.writeBytes("j");output.flush();

                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!right_stop){
                                        try{
                                            output.writeBytes("j");output.flush();
                                            Thread.sleep(200);}catch(IOException e){}catch(InterruptedException r){}
                                    }
                                    right_stop=false;
                                }
                            });
                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        right.setBackgroundResource(R.drawable.right);
                        right_stop=true;
                        break;
                }
                return true;
            }

        });


        myButton1.setOnTouchListener( new View.OnTouchListener() {

                                         public boolean onTouch(View yourButton, MotionEvent theMotion) {
                                             switch (theMotion.getAction()) {
                                                 case MotionEvent.ACTION_DOWN:
                                                     try{
                                                         output.writeBytes("lbd");output.flush();
                                                     }catch(IOException e){}catch(NullPointerException m){}
                                                     break;
                                                 case MotionEvent.ACTION_UP:
                                                     try{
                                                         output.writeBytes("lbu");output.flush();
                                                     }catch(IOException e){}catch(NullPointerException m){}

                                                     break;
                                             }
                                             return true;
                                         }

    });

        myButton2.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        try{
                            output.writeBytes("rbd");output.flush();
                        }catch(IOException e){}catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        try{
                            output.writeBytes("rbu");output.flush();
                        }catch(IOException e){}catch(NullPointerException m){}

                        break;
                }
                return true;
            }

        });

    }//end onCreate

    public void sendClickLmb(View view){
        try{
            output.writeBytes("d");output.flush();
        }catch(IOException e){}catch(NullPointerException n){}
    }

    public void sendClickRmb(View view){
        try{
            output.writeBytes("e");output.flush();
        }catch(IOException e){}catch(NullPointerException n){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//binding menu to this activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){//menu selection listener
        if(item.getItemId()==R.id.action_settings){
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void run_client(){
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try{
                    Thread.sleep(500);}catch (java.lang.InterruptedException i){}
                    try {
                        if (ip_address == "") {}
                        ip_address = sharedPref.getString("ip_addr", "");
                        client = new Socket(ip_address, 9999);
                        output = new DataOutputStream(client.getOutputStream());
                        output.flush();
                        try{output.writeBytes("__0ms__");output.flush();}catch(IOException e){runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "__0ms notsent", Toast.LENGTH_SHORT).show();
                            }
                        });}//confirmation message
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Connected", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    } catch (IOException i) {runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "trying to connect", Toast.LENGTH_SHORT).show();
                        }
                    });}
                }//while loop ends here
            }//run ends here
        });//executor ends here
    }//Run client ends here


    public class ActivitySwipeDetector  implements View.OnTouchListener {

        static final String logTag = "ActivitySwipeDetector";
        private Activity activity;
        static final int MIN_DISTANCE = 5;
        private float downX, downY, upX, upY;
        private float prevX;
        private float prevY;
        private int incr=0;
        byte[] bytesToSend=new byte[10];

        public ActivitySwipeDetector(Activity activity){
            this.activity = activity;
            this.incr=0;
        }

        public void onRightToLeftSwipe(float delta){
            //pass
        }

        public void onLeftToRightSwipe(float delta){
             //pass
        }

        public void onTopToBottomSwipe(Float delta){
            //pass
        }

        public void onBottomToTopSwipe(Float delta){
           //pass
        }

        public boolean onTouch(View v, MotionEvent event) {

            switch(event.getAction()) {

                case MotionEvent.ACTION_DOWN: {
                    downX = event.getX();
                    downY = event.getY();
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    upX = event.getX();
                    upY = event.getY();
                    incr=0;
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
                         try{
                            output.writeBytes("d");output.flush();
                        }catch(IOException e){}catch(NullPointerException n){}
                        break;
                    }
                    return true;
                }


                case MotionEvent.ACTION_MOVE:
                    if (incr == 0) {
                        prevX = event.getX();
                        prevY = event.getY();
                        incr += 1;
                    }
                    else if (incr == 1) {

                        try {
                            output.writeBytes(String.format("%s,%s,", event.getX()-prevX,event.getY()-prevY));output.flush();
                        }
                        catch (IOException e) {} catch (NullPointerException n) {}
                        prevX=event.getX();prevY=event.getY();
                        }
                    return true;



            }
            return false;
        }
    }
}
