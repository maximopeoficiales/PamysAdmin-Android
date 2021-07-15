package com.maximoprog.pamysadmin.utils;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Alert {
    public static void showMessageSuccess(Context context, String msg) {
        Toasty.success(context, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void showMessageInfo(Context context, String msg) {
        Toasty.info(context, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void showMessageWarning(Context context, String msg) {
        Toasty.warning(context, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void showMessage(Context context, String msg) {
        Toasty.normal(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showMessageError(Context context, String msg) {
        Toasty.error(context, msg, Toast.LENGTH_SHORT, true).show();

    }
}
