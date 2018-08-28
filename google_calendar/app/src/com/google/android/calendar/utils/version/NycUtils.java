// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.version;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public final class NycUtils
{

    public static boolean isDeviceTablet(Context context)
    {
        context = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getRealMetrics(displaymetrics);
        return (float)Math.min(displaymetrics.heightPixels, displaymetrics.widthPixels) / displaymetrics.density >= 600F;
    }
}
