// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import com.google.android.calendar.utils.SystemWindowInsetApplier;

public final class FullScreenManager
{

    public static FullScreenManager fullScreenManager;
    public final android.view.WindowManager.LayoutParams layoutParams = new android.view.WindowManager.LayoutParams(-1, -1, 1000, 0x1000100, 1);
    public final SystemWindowInsetApplier systemWindowInsetApplier = new SystemWindowInsetApplier(true);
    public final WindowManager windowManager;

    FullScreenManager(Activity activity)
    {
        windowManager = (WindowManager)activity.getBaseContext().getSystemService("window");
    }
}
