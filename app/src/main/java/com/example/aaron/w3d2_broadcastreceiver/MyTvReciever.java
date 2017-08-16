package com.example.aaron.w3d2_broadcastreceiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.widget.TextView;

public class MyTvReciever extends BroadcastReceiver {


    TextView textView;
    TextView tvHeadphones;
    TextView tvPowerCable;
    TextView tvMessegeRecieved;
    public MyTvReciever(TextView textView, TextView tvHeadphones, TextView tvPower, TextView tvMessege) {
        this.textView = textView;
        this.tvHeadphones = tvHeadphones;
        this.tvPowerCable = tvPower;
        this.tvMessegeRecieved = tvMessege;

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        switch(intent.getAction()){
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    textView.setText("Airplane Mode: " + (isAirplaneModeOn(context) ? "On" : "Off"));
                break;
            case Intent.ACTION_HEADSET_PLUG:
                tvHeadphones.setText("Headphone Status: " + (isheadPhonesPlugedIn(context) ? "Connected" : "Disconected"));
                break;
            case Intent.ACTION_POWER_CONNECTED:
                tvPowerCable.setText("Power Cable Status: Connected");
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                tvPowerCable.setText("Power Cable Status: Disconnected");
                break;
            case "recieveMessage":
                tvMessegeRecieved.setText(intent.getExtras().getString("data"));
                break;


        }


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
        AudioManager am1 = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        return am1.isWiredHeadsetOn();
    }
    public static boolean powerCableStatus(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }


}
