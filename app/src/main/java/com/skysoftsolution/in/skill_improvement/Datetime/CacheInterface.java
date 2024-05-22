package com.skysoftsolution.in.skill_improvement.Datetime;

public interface CacheInterface {

    String KEY_CACHED_BOOT_TIME = "com.skysoftsolution.in.skill_improvement.Datetime.cached_boot_time";
    String KEY_CACHED_DEVICE_UPTIME = "com.skysoftsolution.in.skill_improvement.Datetime.cached_device_uptime";
    String KEY_CACHED_SNTP_TIME = "com.skysoftsolution.in.skill_improvement.Datetime.cached_sntp_time";

    void put(String key, long value);

    long get(String key, long defaultValue);

    void clear();


}
