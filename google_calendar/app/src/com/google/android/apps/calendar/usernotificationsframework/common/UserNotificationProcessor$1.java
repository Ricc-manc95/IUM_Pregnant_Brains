// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import android.content.Context;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.common.util.concurrent.FutureCallback;

final class val.context
    implements FutureCallback
{

    private final Context val$context;

    public final void onFailure(Throwable throwable)
    {
        Context context1 = val$context;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context1, "user_notifications", "notifications_check_failed", throwable.toString(), null);
            return;
        }
    }

    public final volatile void onSuccess(Object obj)
    {
    }

    ()
    {
        val$context = context1;
        super();
    }
}
