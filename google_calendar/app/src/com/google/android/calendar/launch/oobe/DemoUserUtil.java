// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.content.Context;
import android.os.UserManager;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;

public final class DemoUserUtil
{

    public final Context context;

    public DemoUserUtil(Context context1)
    {
        context = context1;
    }

    public final boolean isDemoUser()
    {
        Object obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).android_retail_demo_calendar();
        if (android.os.Build.VERSION.SDK_INT < 25)
        {
            return false;
        }
        obj = (UserManager)context.getSystemService(android/os/UserManager);
        return obj != null && ((UserManager) (obj)).isDemoUser();
    }
}
