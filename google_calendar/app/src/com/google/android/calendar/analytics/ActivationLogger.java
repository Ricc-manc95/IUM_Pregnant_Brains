// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.analytics;

import android.content.Context;
import com.google.android.apps.calendar.loggers.ClearcutManager;
import com.google.android.apps.calendar.loggers.rlz.RlzConfig;
import com.google.android.apps.calendar.loggers.rlz.RlzTracker;
import com.google.android.calendar.utils.app.ApplicationUtils;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;

public final class ActivationLogger
{

    public static ActivationLogger instance;
    public ClearcutManager clearcutManager;
    public RlzTracker rlzTracker;

    public ActivationLogger(Context context)
    {
        context = context.getApplicationContext();
        boolean flag = ApplicationUtils.isSystemApp(context);
        rlzTracker = new RlzTracker(context, RlzConfig.getInstance(flag), TimelyUtils.getVersionSharedPreferences(context));
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0)
        {
            clearcutManager = ClearcutManager.getInstance(context, flag);
        }
    }
}
