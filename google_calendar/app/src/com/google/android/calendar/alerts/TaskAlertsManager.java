// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.NotificationManager;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;

public class TaskAlertsManager
{

    public static final boolean DEBUG;
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/TaskAlertsManager);
    public final Context context;
    public final NotificationManager notificationManager;

    public TaskAlertsManager(Context context1)
    {
        this(context1, (NotificationManager)context1.getSystemService("notification"));
    }

    private TaskAlertsManager(Context context1, NotificationManager notificationmanager)
    {
        context = context1;
        notificationManager = notificationmanager;
    }

    static 
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            DEBUG = ((FeatureConfig)featureconfig).dogfood_features();
        }
    }
}
