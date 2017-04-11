package com.yugal.android.mobilemouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public static Socket client;
    public String message;
    public BufferedReader in;
    public DataOutputStream output;
    EditText box;
    EditText ip_box;
    boolean my_message=false;
    ExecutorService threadExecutor = Executors.newCachedThreadPool();
    public String ip_addr;
    public String ip_address;
    Intent i;
    public Button cbutton;
    SharedPreferences.Editor editor;
    View view;
    SharedPreferences sharedPref;
    ViewGroup.LayoutParams param1;
    ImageButton butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this,R.xml.pref_general,false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        ip_address=sharedPref.getString("ip_addr","");
        param1=cbutton.getLayoutParams();
        run_client(view);
        editor=sharedPref.edit();
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

        if(item.getItemId()==R.id.mouse_simulator){
            Intent i =new Intent(this,MouseSimulator.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //i=new Intent(this, MyService.class).putExtra("ip_addr",ip_address);
        //startService(i);

        try {
            if (client !=null && in!=null && output!=null){
            client.close();
            in.close();
            output.close();}
        }catch(IOException e){}
    }




    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("IP", ip_address);
        // etc.
    }



    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        ip_addr = savedInstanceState.getString("IP");

    }


    public static Socket getSocket(){
        return client;
    }


    public void run_client(View view){
       threadExecutor.execute(new Runnable() {
           @Override
           public void run() {
               try {
                   if (ip_address==""){
                       Toast.makeText(getBaseContext(), "Ip address getting error", Toast.LENGTH_SHORT).show();}
                   ip_address=sharedPref.getString("ip_addr","");
                   client = new Socket(ip_address, 9999);
                   in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                   output = new DataOutputStream(client.getOutputStream());
                   output.flush();
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(getBaseContext(), "Connected", Toast.LENGTH_SHORT).show();
                       }
                   });

               } catch (    IOException i) {runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getBaseContext(), String.format("Unable to connect to %s",ip_address), Toast.LENGTH_SHORT).show();
                   }
               });
                   return;}
           }//run ends here
       });//executor ends here
    }//Run client ends here


}//end class mainActivity