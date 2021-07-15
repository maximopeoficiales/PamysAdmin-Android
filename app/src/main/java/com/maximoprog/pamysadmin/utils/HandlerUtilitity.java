package com.maximoprog.pamysadmin.utils;

import android.os.Handler;

public class HandlerUtilitity {
    public static void setTimeOut(Runnable runnable, long timer) {
        new Handler().postDelayed(runnable, timer);
    }
}
