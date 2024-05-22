package com.skysoftsolution.in.skill_improvement.utility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import java.util.HashMap;

public class DeviceInfo {

    String loginUserName = "";

    public static final String USER_DEVICE_ID = Build.ID;
    public static final String USER_DEVICE_BRAND = Build.BRAND;
    public static final String USER_DEVICE_NAME = Build.DEVICE;
    public static final String USER_DEVICE_PRODUCT = Build.PRODUCT;
    public static final String USER_DEVICE_MODEL = Build.MODEL;
    public static final String USER_DEVICE_SERIAL = Build.SERIAL;
    public static final String USER_DEVICE_BOARD = Build.BOARD;
    public static final String USER_DEVICE_FINGERPRINT = Build.FINGERPRINT;
    public static final String USER_DEVICE_HARDWARE = Build.HARDWARE;
    public static final String USER_DEVICE_MANUFACTURER = Build.MANUFACTURER;
    public static final String USER_DEVICE_CODENAME = Build.VERSION.CODENAME;

    public HashMap<String, String> getDeviceInfoHashMap() {
        HashMap<String, String> deviceInfo = new HashMap<String, String>();
        deviceInfo.put("USER_DEVICE_ID", USER_DEVICE_ID);
        deviceInfo.put("USER_DEVICE_BRAND", USER_DEVICE_BRAND);
        deviceInfo.put("USER_DEVICE_NAME", USER_DEVICE_NAME);
        deviceInfo.put("USER_DEVICE_PRODUCT", USER_DEVICE_PRODUCT);
        deviceInfo.put("USER_DEVICE_SERIAL", USER_DEVICE_SERIAL);
        deviceInfo.put("USER_DEVICE_BOARD", USER_DEVICE_BOARD);
        deviceInfo.put("USER_DEVICE_FINGERPRINT", USER_DEVICE_FINGERPRINT);
        deviceInfo.put("USER_DEVICE_HARDWARE", USER_DEVICE_HARDWARE);
        deviceInfo.put("USER_DEVICE_MANUFACTURER", USER_DEVICE_MANUFACTURER);
        deviceInfo.put("USER_DEVICE_CODENAME", USER_DEVICE_CODENAME);
        deviceInfo.put("USER_DEVICE_MODEL", USER_DEVICE_MODEL);
        deviceInfo.put("Android_Device_ID", user_Device_Android_Id);
        deviceInfo.put("OS_Version", user_Device_Os_Version);// /like 4.2.2
        deviceInfo.put("OS_SDK_Version", String.valueOf(user_Device_Sdk_Version));// like 18/19
        deviceInfo.put("OS_Name", getOsName(user_Device_Sdk_Version));// like
        deviceInfo.put("UserServerId", getUserServerId());
        return deviceInfo;
    }

    private String getUserServerId() {

        return "";
    }

    public static String getOsName(int user_Device_Sdk_Version) {
        switch (user_Device_Sdk_Version) {
            case 15:
                return "Ice_Cream_SandWich_MR1";
            case 16:
                return "Jelly_Bean";
            case 17:
                return "Jelly_Bean_MR1";
            case 18:
                return "Jelly_Bean_MR2";
            case 19:
                return "Kitkat";
            case 20:
                return "Kitkat_Watch";
            case 21:
                return "Lollipop";
            case 22:
                return "Lollipop_MR1";
            case 23:
                return "MarshaMallow";
            case 24:
                return "Nougat";
            case 25:
                return "Nougat";
            case 26:
                return "Oreo";
            case 27:
                return "Oreo";
            case 28:
                return "Pie";
            case 29:
                return "Android10";
            case 30:
                return "Android11";
            case 31:
                return "Android12";
            case 32:
                return "Android12L";

            default:
                return "";
        }

    }

    // ////Device Info Work Like Upasthiti

    // //Divice os version
    String user_Device_Os_Version = Build.VERSION.RELEASE;
    int user_Device_Sdk_Version = Build.VERSION.SDK_INT;
    public static String user_Device_Android_Id;

    public static String appUuid;
    public static String appVersionCode;
    public static String appVersionName;

    // /////jay sir's Device Identification Work
    public final static String user_Device_Serial = Build.SERIAL;// not
    // available
    // with
    // all
    // devices(Unknown)
    public final static String user_Device_Id = Build.ID;
    Context context;
    public ContentResolver cr;
    WifiManager wifiManager;

    public DeviceInfo(Context context) {
        this.loginUserName = loginUserName;
        this.context = context;

        this.cr = context.getContentResolver();
        this.wifiManager = (WifiManager) context
                .getSystemService(context.WIFI_SERVICE);

        try {
            user_Device_Android_Id = android.provider.Settings.Secure
                    .getString(cr, android.provider.Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            user_Device_Android_Id = "";
        }


        PackageManager pm = context.getPackageManager();
        int uuid = 0;// /10049
        try {
            ApplicationInfo appInfo = pm.getApplicationInfo(
                    context.getPackageName(), 0);
            uuid = appInfo.uid;
            appUuid = String.valueOf(uuid);

        } catch (PackageManager.NameNotFoundException e2) {
            // TODO Auto-generated catch block
            appUuid = "";
            e2.printStackTrace();
        }

        // ////Version Code Work
        PackageInfo pinfo = null;
        try {
            pinfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            appVersionCode = String.valueOf(pinfo.versionCode);
            appVersionName = pinfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            appVersionCode = "";
            appVersionName = "";
            e.printStackTrace();
        }

    }


    protected static String getApplicationInformation(Context context) {
        String appsInfo = "";
        try {

            PackageInfo pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            String appName = "UPSNM";
            String appDescription = "An android client to take inspection any ways.";
            String packageName = pInfo.packageName;
            String versionCode = pInfo.versionCode + "";
            String versionName = pInfo.versionName;
            String updateMessage = "";
            String apkFileName = "";

            appsInfo = "{'appName': '" + appName + "','appDescription': '"
                    + appDescription + "','packageName': '" + packageName
                    + "','versionCode': '" + versionCode + "','versionName': '"
                    + versionName + "','updateMessage': '" + updateMessage
                    + "','apkFileName':'" + apkFileName + "'}";

        } catch (Exception e) {
            Log.e("DEBUG", "Exception in retreving pkg info" + e.getMessage());
            return null;
        }
        return appsInfo;
    }

}