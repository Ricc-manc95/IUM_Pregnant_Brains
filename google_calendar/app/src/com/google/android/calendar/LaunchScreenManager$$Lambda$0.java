// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;


// Referenced classes of package com.google.android.calendar:
//            LaunchScreenManager

final class arg._cls1
    implements Runnable
{

    private final LaunchScreenManager arg$1;

    public final void run()
    {
        LaunchScreenManager launchscreenmanager = arg$1;
        launchscreenmanager.minTimeoutElapsed = true;
        launchscreenmanager.hideLaunchScreen(false);
    }

    (LaunchScreenManager launchscreenmanager)
    {
        arg$1 = launchscreenmanager;
    }
}
