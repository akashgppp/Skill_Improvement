package com.skysoftsolution.in.skill_improvement.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.skysoftsolution.in.skill_improvement.utils.BadgeUtils;

public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Retrieve the badge count from input data
        int badgeCount = getInputData().getInt("badgeCount", 0);

        // Send the notification
        App.sendNotification(getApplicationContext(), "New Message", "You have a new message!", badgeCount);

        // Update the badge count
        BadgeUtils.updateBadgeCount(getApplicationContext(), badgeCount);

        return Result.success();
    }
}
