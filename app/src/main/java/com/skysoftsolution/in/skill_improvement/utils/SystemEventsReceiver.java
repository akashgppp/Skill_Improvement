package com.skysoftsolution.in.skill_improvement.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
public class SystemEventsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_BATTERY_LOW.equals(action)) {
            scheduleNotification(context, "Battery Low", "Your battery is low. Please charge your device.");
        } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                scheduleNotification(context, "Network Connected", "Your device is connected to the internet.");
            }
        } else if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            scheduleNotification(context, "Power Connected", "Your device is charging.");
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            scheduleNotification(context, "Power Disconnected", "Your device is not charging.");
        } else if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
            scheduleNotification(context, "ACTION_AIRPLANE_MODE_CHANGED",
                    "Your device ACTION_AIRPLANE_MODE_CHANGED");
        }
    }

    private void scheduleNotification(Context context, String title, String message) {
        Data inputData = new Data.Builder()
                .putString("title", title)
                .putString("message", message)
                .build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setInputData(inputData)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);
    }
}
