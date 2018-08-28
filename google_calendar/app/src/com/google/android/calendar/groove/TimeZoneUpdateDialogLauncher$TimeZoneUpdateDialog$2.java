// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;

final class val.currentTimeZoneId
    implements android.content.ZoneUpdateDialog._cls2
{

    private final val.currentTimeZoneId this$0;
    private final Context val$context;
    private final String val$currentTimeZoneId;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = val$context;
        if (dialoginterface != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(dialoginterface, "groove", "timezone_dialog_decline", "", null);
        }
        dialoginterface = this._cls0.this;
        veCurrentTimeZoneAsLastDisplayed(val$context, val$currentTimeZoneId);
    }

    ()
    {
        this$0 = final_;
        val$context = context1;
        val$currentTimeZoneId = String.this;
        super();
    }
}
