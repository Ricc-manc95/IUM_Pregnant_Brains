// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.calendarcommon2.LogUtils;

public final class NetworkUtil
{

    public static boolean isConnectedToInternet(Context context)
    {
        boolean flag;
        try
        {
            context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e("NetworkUtil", context, "android.permission.ACCESS_NETWORK_STATE not granted", new Object[0]);
            return true;
        }
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        flag = context.isConnected();
        if (flag)
        {
            return true;
        }
        return false;
    }
}
