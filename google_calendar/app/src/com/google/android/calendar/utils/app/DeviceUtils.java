// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

public final class DeviceUtils
{

    public static int getDeviceBucket(Context context, int i, int j)
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences("sync_adapter_prefs.xml", 0);
        String s = sharedpreferences.getString("device_id", null);
        if (s != null)
        {
            context = s;
        } else
        {
            String s1 = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
            context = s1;
            if (TextUtils.isEmpty(s1))
            {
                context = UUID.randomUUID().toString();
            }
            sharedpreferences.edit().putString("device_id", context).apply();
        }
        return Math.abs(context.hashCode() ^ i) % j;
    }
}
