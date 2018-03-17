package mapp.com.sg.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int NOTIFY_ME_ID = 1337;

    private BroadcastReceiver the_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "SMS Message Received!", duration);
            toast.show();
        }
    };

    private IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        @Override
        protected void onResume(){

            // Register receiver if activity is in front
            this.registerReceiver(the_receiver, filter);
            super.onResume();
        }

        @Override
        protected void onPause(){

            // Unregister receiver if activity is not in front
            this.unregisterReceiver(the_receiver);
            super.onPause();


        }
    }
