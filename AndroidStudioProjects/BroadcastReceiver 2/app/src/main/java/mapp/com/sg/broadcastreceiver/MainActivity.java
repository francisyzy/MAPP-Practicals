package mapp.com.sg.broadcastreceiver;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    private static final int NOTIFY_ME_ID = 1337;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private String phoneNo ="96853256";
    private String message ="Return Message";

    private BroadcastReceiver the_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "SMS Message Received", duration);
            //Log.d (TAG, "Received");
            toast.show();
            String retValuePhone= readSMSDetail(context, intent);
            //sendSMSMessage();
            // Change to your own mobile number
            String num = "+6593059393";

            sendSMS(num,"https://www.google.com.sg/maps/@1.3111343,103.7721155,16z");

            //Alert Dialog Box
            new AlertDialog.Builder(context)
                    .setTitle("Show Message Received")
                    .setMessage("Incoming Message" + retValuePhone)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    };

    private IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

    public String readSMSDetail (Context context, Intent intent){
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String str = "";
        String senderPhoneNo ="";
        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];


            // For every SMS message received
            for (int i=0; i < msgs.length; i++) {
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                // Sender's phone number
                str += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
                senderPhoneNo = msgs[i].getOriginatingAddress();
                // Fetch the text message
                str += msgs[i].getMessageBody().toString();
                // Newline <img draggable="false" class="emoji" alt="🙂" src="https://s.w.org/images/core/emoji/72x72/1f642.png">
                str += "\n";
            }

            // Display the entire SMS Message
            Log.d(TAG, str);
        }
        return senderPhoneNo;
    }
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button notify = (Button) findViewById(R.id.notify);

        notify.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                /**** Create Notification ****/
                final NotificationManager mgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                // Create intent object first

                Intent i = new Intent(getBaseContext(), NotifyMessage.class);

                PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, i,0);

                        Notification note =
                                new Notification.Builder(getApplicationContext())
                                        .setSmallIcon(R.drawable.stat_notify_chat)
                                        .setContentTitle("My notification")
                                        .setContentText ("Hello World").setAutoCancel(true)
                                        .setContentIntent(pi).build();

                mgr.notify(NOTIFY_ME_ID, note);
            }


        });
    }

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
