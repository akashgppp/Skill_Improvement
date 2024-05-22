package com.skysoftsolution.in.skill_improvement.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageUtils {

    private static final String PREFS_NAME = "KGBV_Language";

    public static void setSelectedLanguage(Context context, Context baseContext, String lang) {

       /* SharedPreferences pref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("language", lang);
        editor.commit();*/
        setApplicationLocale(context, lang);
    }

    public static String getSelectedLanguage(Context context) {

        SharedPreferences pref = context.getSharedPreferences(PREFS_NAME, 0);
        String lang = pref.getString("language", "hi");
        if (lang.equalsIgnoreCase("hi"))
            return "hi";
        else
            return "en";

    }


    /**
     * setting application locale to app specific
     *
     * @param lang language to convert en for english and hi for hindi
     */
    @SuppressLint("NewApi")
    public static void setApplicationLocale(Context baseContext, String lang) {
        if (Build.VERSION.SDK_INT > 23) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);

            Configuration config = baseContext.getResources().getConfiguration();
            config.setLocale(locale);
            DisplayMetrics dMetrics = baseContext.getResources().getDisplayMetrics();
            baseContext.getResources().updateConfiguration(config, dMetrics);
        } else {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = baseContext.getResources().getConfiguration();
            config.locale = locale;
            DisplayMetrics dMetrics = baseContext.getResources().getDisplayMetrics();
            baseContext.getResources().updateConfiguration(config, dMetrics);

        }

    }


    public static boolean changeLang(Context context, String lang_code) {

        boolean languagesetSuccess = false;
        Locale sysLocale;

        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.getLocales().get(0);
        } else {
            sysLocale = config.locale;
        }
        if (!lang_code.equals("") && !sysLocale.getLanguage().equals(lang_code)) {
            Locale locale = new Locale(lang_code);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
            languagesetSuccess = true;

        }

        return languagesetSuccess;
    }


}
