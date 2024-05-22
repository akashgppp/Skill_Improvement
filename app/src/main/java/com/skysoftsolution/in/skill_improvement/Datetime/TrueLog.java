package com.skysoftsolution.in.skill_improvement.Datetime;

import android.util.Log;

class TrueLog {

    private static boolean LOGGING_ENABLED = false;

    static void d(String tag, String msg) {
        if (LOGGING_ENABLED) {
            Log.d(tag, msg);
        }
    }

    static void i(String tag, String msg) {
        if (LOGGING_ENABLED) {
            Log.i(tag, msg);
        }
    }

    static void w(String tag, String msg) {
        if (LOGGING_ENABLED) {
            Log.w(tag, msg);
        }
    }

    static void e(String tag, String msg, Throwable t) {
        if (LOGGING_ENABLED) {
            Log.e(tag, msg, t);
        }
    }

    static void setLoggingEnabled(boolean isLoggingEnabled) {
        LOGGING_ENABLED = isLoggingEnabled;
    }
}
