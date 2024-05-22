package com.skysoftsolution.in.skill_improvement.faceWork.Entity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AndroidUtils {

    public static boolean checkYourMobileDataConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean iswifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean ismobileConn = false;
        if (networkInfo != null) {
            ismobileConn = networkInfo.isConnected();
        }
        boolean returnString = false;
        if (iswifiConn || ismobileConn) {
            returnString = true;
        }
        return returnString;
    }

}