package com.yugal.android.mobilemouse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Formatter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.yugal.android.mobilemouse.TextAnalyzer;

public class MouseSimulator extends AppCompatActivity {

    public TextView mousepad;
    public Socket client;
    public Formatter output;
    public Button up,down,left,right;
    public EditText box1;
    public boolean up_stop,down_stop,right_stop,left_stop;
    ExecutorService threadExecutor = Executors.newCachedThreadPool();
    public String ip_address;
    SharedPreferences sharedPref;
    TextAnalyzer TAnal;
    public void onDestroy(){
        super.onDestroy();
        try{
        output.format("__sbk__400");}catch(NullPointerException k){}
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
        up_stop=false;
        down_stop=false;
        left_stop=false;
        right_stop=false;
        mousepad = (TextView)this.findViewById(R.id.mousepad);
        mousepad.setOnTouchListener(activitySwipeDetector);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        ip_address=sharedPref.getString("ip_addr","");
        TAnal=new TextAnalyzer();
        run_client();

        Button myButton1=(Button)findViewById(R.id.lmb);
        Button myButton2=(Button) findViewById(R.id.rmb);

        up=(Button)findViewById(R.id.up);
        down=(Button)findViewById(R.id.down);
        left=(Button)findViewById(R.id.left);
        right=(Button)findViewById(R.id.right);

        TextView father_layout=(TextView) findViewById(R.id.mousepad);

        father_layout.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN &&event.getAction()==KeyEvent.ACTION_DOWN){
                    try{output.format("b");output.flush();}catch(NullPointerException n){}
                }
                if(keyCode==KeyEvent.KEYCODE_VOLUME_UP &&event.getAction()==KeyEvent.ACTION_DOWN){
                    try{output.format("a");output.flush();}catch(NullPointerException d){}
                }
                return false;
            }
        });

        box1=(EditText) findViewById(R.id.message_text);

        box1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER &&event.getAction()==KeyEvent.ACTION_DOWN){
                    try{output.format("Enter");output.flush();}catch(NullPointerException e){}
                }
                if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN &&event.getAction()==KeyEvent.ACTION_DOWN){
                    try{output.format("b");output.flush();}catch(NullPointerException e){}
                }
                if(keyCode==KeyEvent.KEYCODE_VOLUME_UP &&event.getAction()==KeyEvent.ACTION_DOWN){
                    try{output.format("a");output.flush();}catch(NullPointerException s){}
                }
                return false;
            }
        });

        box1.addTextChangedListener(new TextWatcher() {
            String prev;
            String finl;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                 prev=s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                finl=s.toString();
                int action=TAnal.detectAction(prev,finl);
                if (action==TAnal.BACKSPACE){
                    try{output.format("Backspace");output.flush();}catch(NullPointerException d){}
                }
                if (action==TAnal.ADDED){
                    String lastChar = s.subSequence(s.length() - 1, s.length()).toString();
                    try{output.format("added "+lastChar);output.flush();}catch(NullPointerException d){}
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        up.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View yourButton, MotionEvent theMotion) {
                switch (theMotion.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        try{
                            up.setBackgroundColor(0x55FFFF33);
                            try{output.format("g");output.flush();}catch(NullPointerException d){}

                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!up_stop){
                                        try{
                                            output.format("g");output.flush();
                                            Thread.sleep(200);}catch(InterruptedException r){}catch(NullPointerException d){}
                                    }
                                    up_stop=false;
                                }
                            });
                        }catch(NullPointerException m){}
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
                            try{output.format("h");output.flush();}catch(NullPointerException d){}
                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!down_stop){
                                        try{
                                            output.format("h");output.flush();
                                            Thread.sleep(200);}catch(InterruptedException r){}catch(NullPointerException d){}
                                    }
                                    down_stop=false;
                                }
                            });
                        }catch(NullPointerException m){}
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
                            try{output.format("i");output.flush();}catch(NullPointerException d){}

                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!left_stop){
                                        try{
                                            output.format("i");output.flush();
                                            Thread.sleep(200);}catch(InterruptedException r){}catch(NullPointerException d){}
                                    }
                                    left_stop=false;
                                }
                            });


                        }catch(NullPointerException m){}
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
                           try{ output.format("j");output.flush();}catch(NullPointerException d){}

                            threadExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    while(!right_stop){
                                        try{
                                            output.format("j");output.flush();
                                            Thread.sleep(200);}catch(InterruptedException r){}catch(NullPointerException d){}
                                    }
                                    right_stop=false;
                                }
                            });
                        }catch(NullPointerException m){}
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
                                                         output.format("lbd");output.flush();
                                                     }catch(NullPointerException m){}
                                                     break;
                                                 case MotionEvent.ACTION_UP:
                                                     try{
                                                         output.format("lbu");output.flush();
                                                     }catch(NullPointerException m){}

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
                            output.format("rbd");output.flush();
                        }catch(NullPointerException m){}
                        break;
                    case MotionEvent.ACTION_UP:
                        try{
                            output.format("rbu");output.flush();
                        }catch(NullPointerException m){}

                        break;
                }
                return true;
            }

        });

    }//end onCreate

    public void sendClickLmb(View view){
        try{
            output.format("d");output.flush();
        }catch(NullPointerException n){}
    }

    public void sendClickRmb(View view){
        try{
            output.format("e");output.flush();
        }catch(NullPointerException n){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//binding menu to this activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.action_settings){
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();

        //do not give the editbox focus automatically when activity starts
        box1.clearFocus();
        up.requestFocus();
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
                        output = new Formatter(client.getOutputStream());
                        output.flush();
                        try{output.format("__0ms__");output.flush();}catch(NullPointerException n){}
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Connected", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    } catch (IOException i) {}
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
                            output.format("d");output.flush();
                        }catch(NullPointerException n){}
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
                            output.format(String.format("%s,%s,", event.getX()-prevX,event.getY()-prevY));output.flush();
                        }
                         catch (NullPointerException n) {}
                        prevX=event.getX();prevY=event.getY();
                        }
                    return true;



            }
            return false;
        }
    }
}
