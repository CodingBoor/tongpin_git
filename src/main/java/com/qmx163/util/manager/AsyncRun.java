package com.qmx163.util.manager;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by likai on 2017/9/4.
 */
public class AsyncRun {
    public static void run(Runnable r) {
        Handler h = new Handler(Looper.getMainLooper());
        h.post(r);
    }
}
