package com.skysoftsolution.in.skill_improvement.Datetime;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferenceCacheImpl implements CacheInterface {

    private static final String KEY_CACHED_SHARED_PREFS = "com.skysoftsolution.in.skill_improvement.Datetime.shared_preferences";

    private SharedPreferences _sharedPreferences;

    public SharedPreferenceCacheImpl(Context context) {
        _sharedPreferences = context.getSharedPreferences(KEY_CACHED_SHARED_PREFS, MODE_PRIVATE);
    }

    @Override
    public void put(String key, long value) {
        _sharedPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public long get(String key, long defaultValue) {
        return _sharedPreferences.getLong(key, defaultValue);
    }

    @Override
    public void clear() {
        remove(KEY_CACHED_BOOT_TIME);
        remove(KEY_CACHED_DEVICE_UPTIME);
        remove(KEY_CACHED_SNTP_TIME);
    }

    private void remove(String keyCachedBootTime) {
        _sharedPreferences.edit().remove(keyCachedBootTime).apply();
    }

}