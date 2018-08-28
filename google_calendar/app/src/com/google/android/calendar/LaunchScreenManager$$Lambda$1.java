// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar:
//            LaunchScreenManager

final class arg._cls1
    implements Runnable
{

    private final LaunchScreenManager arg$1;

    public final void run()
    {
        LaunchScreenManager launchscreenmanager = arg$1;
        LogUtils.d(LaunchScreenManager.TAG, "Max timeout expired!", new Object[0]);
        launchscreenmanager.forceDismiss = true;
        launchscreenmanager.hideLaunchScreen(true);
    }

    (LaunchScreenManager launchscreenmanager)
    {
        arg$1 = launchscreenmanager;
    }
}
