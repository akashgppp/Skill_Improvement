package com.skysoftsolution.in.skill_improvement.utils;
import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.skysoftsolution.in.skill_improvement.R;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public static final String CHANNEL_ONE_ID = "channel_one_id";
    public static final String CHANNEL_TWO_ID = "channel_two_id";

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannels();
        }
    }

    private void createNotificationChannels() {
        NotificationChannel channelOne = new NotificationChannel(
                CHANNEL_ONE_ID,
                "Channel One",
                NotificationManager.IMPORTANCE_HIGH
        );
        channelOne.setDescription("This is channel one for video notifications");

        NotificationChannel channelTwo = new NotificationChannel(
                CHANNEL_TWO_ID,
                "Channel Two",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        channelTwo.setDescription("This is channel two for audio notifications");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (manager != null) {
            List<NotificationChannel> channels = new ArrayList<>();
            channels.add(channelOne);
            channels.add(channelTwo);
            manager.createNotificationChannels(channels);
        } else {
            // Handle the case where the NotificationManager is not available
            // Log or throw an exception as per your application's requirement
        }
    }
    @SuppressLint("MissingPermission")
    public static void sendNotification(Context context, String title, String message, int badgeCount) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ONE_ID)
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setNumber(badgeCount)  // Set the badge count here
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ONE_ID, "System Events", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        // Update the badge count
        BadgeUtils.updateBadgeCount(context, badgeCount);
    }
}