package com.skysoftsolution.in.skill_improvement.Datetime;

import static com.skysoftsolution.in.skill_improvement.Datetime.CacheInterface.KEY_CACHED_BOOT_TIME;
import static com.skysoftsolution.in.skill_improvement.Datetime.CacheInterface.KEY_CACHED_DEVICE_UPTIME;
import static com.skysoftsolution.in.skill_improvement.Datetime.CacheInterface.KEY_CACHED_SNTP_TIME;

import android.os.SystemClock;
class DiskCacheClient {

    private static final String TAG = DiskCacheClient.class.getSimpleName();

    private CacheInterface _cacheInterface = null;

    void enableCacheInterface(CacheInterface cacheInterface) {
        this._cacheInterface = cacheInterface;
    }

    void clearCachedInfo() {
        clearCachedInfo(this._cacheInterface);
    }

    void clearCachedInfo(CacheInterface cacheInterface) {
        if (cacheInterface != null) {
            cacheInterface.clear();
        }
    }

    void cacheTrueTimeInfo(SntpClient sntpClient) {
        if (cacheUnavailable()) {
            return;
        }

        long cachedSntpTime = sntpClient.getCachedSntpTime();
        long cachedDeviceUptime = sntpClient.getCachedDeviceUptime();
        long bootTime = cachedSntpTime - cachedDeviceUptime;

        TrueLog.d(TAG, String.format("Caching true time info to disk sntp [%s] device [%s] boot [%s]", cachedSntpTime, cachedDeviceUptime, bootTime));

        _cacheInterface.put(KEY_CACHED_BOOT_TIME, bootTime);
        _cacheInterface.put(KEY_CACHED_DEVICE_UPTIME, cachedDeviceUptime);
        _cacheInterface.put(KEY_CACHED_SNTP_TIME, cachedSntpTime);

    }

    boolean isTrueTimeCachedFromAPreviousBoot() {
        if (cacheUnavailable()) {
            return false;
        }

        long cachedBootTime = _cacheInterface.get(KEY_CACHED_BOOT_TIME, 0L);
        if (cachedBootTime == 0) {
            return false;
        }

        boolean bootTimeChanged = SystemClock.elapsedRealtime() < getCachedDeviceUptime();
        TrueLog.i(TAG, "---- boot time changed " + bootTimeChanged);
        return !bootTimeChanged;
    }

    long getCachedDeviceUptime() {
        if (cacheUnavailable()) {
            return 0L;
        }

        return _cacheInterface.get(KEY_CACHED_DEVICE_UPTIME, 0L);
    }

    long getCachedSntpTime() {
        if (cacheUnavailable()) {
            return 0L;
        }
        return _cacheInterface.get(KEY_CACHED_SNTP_TIME, 0L);
    }

    private boolean cacheUnavailable() {
        if (_cacheInterface == null) {
            TrueLog.w(TAG, "Cannot use disk caching strategy for TrueTime. CacheInterface unavailable");
            return true;
        }
        return false;
    }

}