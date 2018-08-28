// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.google.android.calendar.timely.TaskBundleFragment;

// Referenced classes of package com.google.android.calendar:
//            LaunchScreenManager, EventFragmentHostActivity

final class arg._cls1
    implements Runnable
{

    private final LaunchScreenManager arg$1;

    public final void run()
    {
        LaunchScreenManager launchscreenmanager = arg$1;
        if (!((FragmentActivity) (launchscreenmanager.activity)).mFragments.mHost.mFragmentManager.isDestroyed())
        {
            launchscreenmanager.activity.showOverlayFragment(TaskBundleFragment.TAG, launchscreenmanager.taskBundleFragment);
            launchscreenmanager.taskBundleFragment = null;
        }
    }

    (LaunchScreenManager launchscreenmanager)
    {
        arg$1 = launchscreenmanager;
    }
}
