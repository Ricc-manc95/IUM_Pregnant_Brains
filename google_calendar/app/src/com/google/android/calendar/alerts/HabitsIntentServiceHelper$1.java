// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventKey;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentServiceHelper

final class val.label
    implements FutureCallback
{

    private final Context val$context;
    private final boolean val$done;
    private final EventKey val$eventKey;
    private final String val$label;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e(HabitsIntentServiceHelper.TAG, "Could not load habit instance: %s.", new Object[] {
            val$eventKey
        });
    }

    public final void onSuccess(Object obj)
    {
        if ((Event)obj == null)
        {
            LogUtils.e(HabitsIntentServiceHelper.TAG, "Could not mark habit instance as done: %s.", new Object[] {
                val$eventKey
            });
        } else
        {
            if (val$eventKey.uri().isPresent())
            {
                HabitsIntentServiceHelper.forceNotifyChange(val$context, (Uri)val$eventKey.uri().get());
            }
            Context context1;
            String s;
            if (val$done)
            {
                obj = "marked_as_done";
            } else
            {
                obj = "marked_as_not_done";
            }
            context1 = val$context;
            s = val$label;
            if (context1 != null)
            {
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                } else
                {
                    ((AnalyticsLogger)analyticslogger).trackEvent(context1, "groove", ((String) (obj)), s, null);
                    return;
                }
            }
        }
    }

    ()
    {
        val$eventKey = eventkey;
        val$context = context1;
        val$done = flag;
        val$label = s;
        super();
    }
}
