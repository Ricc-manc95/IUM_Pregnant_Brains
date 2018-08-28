// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.content.Context;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.common.util.concurrent.FutureCallback;
import java.util.concurrent.CancellationException;

final class val.context
    implements FutureCallback
{

    private final Context val$context;

    public final void onFailure(Throwable throwable)
    {
        if (!(throwable instanceof CancellationException))
        {
            throwable = AnalyticsLoggerHolder.instance;
            if (throwable == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)throwable).trackEvent(val$context, "settings_view_model_failure", "null");
        }
    }

    public final void onSuccess(Object obj)
    {
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)obj).trackEvent(val$context, "settings_view_model", "null");
            return;
        }
    }

    lder()
    {
        val$context = context1;
        super();
    }
}
