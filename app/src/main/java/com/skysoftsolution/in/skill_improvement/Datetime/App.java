package com.skysoftsolution.in.skill_improvement.Datetime;
import android.app.Application;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRxTrueTime();
    }

    private void initRxTrueTime() {
        DisposableSingleObserver<Date> disposable = TrueTimeRx.build().withConnectionTimeout(31_428).withRetryCount(100).withSharedPreferencesCache(this)
                .withLoggingEnabled(true).initializeRx("time.google.com").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        subscribeWith(new DisposableSingleObserver<Date>() {
            @Override
            public void onSuccess(Date date) {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

}