package com.skysoftsolution.in.skill_improvement.utils;
import android.content.Context;
import me.leolin.shortcutbadger.ShortcutBadger;

public class BadgeUtils {

    public static void updateBadgeCount(Context context, int count) {
        ShortcutBadger.applyCount(context, count); //for 1.1.4+
    }

    public static void removeBadge(Context context) {
        ShortcutBadger.removeCount(context); //for 1.1.4+
    }
}
