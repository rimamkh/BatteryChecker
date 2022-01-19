package com.example.batterychecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TextView statuesLabel=((MainActivity)context).findViewById(R.id.charging);
        TextView percantageLabel=((MainActivity)context).findViewById(R.id.textView);
        ImageView image=((MainActivity)context).findViewById(R.id.imageView);
    String action=intent.getAction();
    if(action !=null && action.equals(Intent.ACTION_BATTERY_CHANGED)){
        int stautes=intent.getIntExtra(BatteryManager.EXTRA_STATUS,
                -1);
        String message="";
        switch (stautes){
            case BatteryManager.BATTERY_STATUS_FULL:
                message="FULL";
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                message="CHARGING";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                message="DIS CHARGING";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                message="NOT CHARGING";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                message="UNKNOWN";
                break;
        }
        ///print the message in the label
        statuesLabel.setText(message);

        ///get the percentage of charging
        int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,
                -1);
        int scale=intent.getIntExtra(BatteryManager.EXTRA_SCALE,
                -1);
        int percentage=level*100/scale;
        percantageLabel.setText(String.valueOf(percentage));

        ///change the image according to the level
        Resources res=context.getResources();
        if(percentage>=90){
            image.setImageDrawable(res.getDrawable(R.drawable.full));
        }else if(percentage>90 && percentage>=40){
            image.setImageDrawable(res.getDrawable(R.drawable.half));
        }else
        {
            image.setImageDrawable(res.getDrawable(R.drawable.low));
        }

    }
    }
}
