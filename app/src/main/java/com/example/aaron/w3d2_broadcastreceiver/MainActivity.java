package com.example.aaron.w3d2_broadcastreceiver;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvAirplaneMode;
    TextView tvHeadphoneStatus;
    TextView tvPowerCableStatus;
    TextView tvMessege;
    Button btnSendMessege;
    MyTvReciever myTvReciever;
    IntentFilter intentFilter;

    Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAirplaneMode = (TextView)findViewById(R.id.tvMonitorStreen);
        tvHeadphoneStatus = (TextView)findViewById(R.id.tvMonitorStreen1);
        tvPowerCableStatus = (TextView)findViewById((R.id.tvMonitorStreen2));
        tvMessege = (TextView)findViewById(R.id.tvMonitorStreen4);
        tvAirplaneMode.setText("Airplane Mode: " + (isAirplaneModeOn(this) ? "On" : "Off"));
        tvHeadphoneStatus.setText("Headphone Status: " + (isheadPhonesPlugedIn(this) ? "Connected" : "Disconected"));
        tvPowerCableStatus.setText("Power Cable Status: " + (powerCableStatus(this) ? "Connected" : "Disconected"));
        tvMessege.setText("");
        btnSendMessege = (Button)findViewById(R.id.btnBroadcastMsg);
        intentService = new Intent(this, MyIntentService.class);
        //startService(intentService);
        //ArrayList<RandomObject> randomObjectsArray = intentService.getParcelableExtra("randomObjects");
      //  Log.d("TAG", "onCreate: " + randomObjectsArray.size());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intentService);

    }

    @Override
    protected void onStart() {
        super.onStart();
      //  tvAirplaneMode = (TextView)findViewById(R.id.tvMonitorStreen);
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction("recieveMessage");
        myTvReciever = new MyTvReciever(tvAirplaneMode,tvHeadphoneStatus,tvPowerCableStatus,tvMessege);
        registerReceiver(myTvReciever,intentFilter);

    }

    public void broadcastController(View view) {
        Intent intent = new Intent();
        intent.setAction("recieveMessage");
        intent.putExtra("data", "Messege Recieved");
        sendBroadcast(intent);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public boolean isheadPhonesPlugedIn(Context context) {
        AudioManager am1 = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            return am1.isWiredHeadsetOn();
    }

    public static boolean powerCableStatus(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }
}
