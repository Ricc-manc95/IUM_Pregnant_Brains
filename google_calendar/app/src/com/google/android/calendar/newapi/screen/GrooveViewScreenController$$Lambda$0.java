// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            GrooveViewScreenController

final class arg._cls2
    implements Runnable
{

    private final GrooveViewScreenController arg$1;
    private final Intent arg$2;

    public final void run()
    {
        GrooveViewScreenController grooveviewscreencontroller = arg$1;
        Intent intent = arg$2;
        grooveviewscreencontroller.markAsDoneRunnable = null;
        FragmentActivity fragmentactivity;
        if (((Fragment) (grooveviewscreencontroller)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (grooveviewscreencontroller)).mHost.mActivity;
        }
        if (fragmentactivity != null)
        {
            if (((Fragment) (grooveviewscreencontroller)).mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)((Fragment) (grooveviewscreencontroller)).mHost.mActivity;
            }
            fragmentactivity.startService(intent);
        }
    }

    (GrooveViewScreenController grooveviewscreencontroller, Intent intent)
    {
        arg$1 = grooveviewscreencontroller;
        arg$2 = intent;
    }
}
