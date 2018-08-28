// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.newapi.screen.GrooveEditScreenController;
import com.google.android.calendar.newapi.screen.HostDialog;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveSummaryView

final class val.activity
    implements android.view.
{

    private final GrooveSummaryView this$0;
    private final FragmentActivity val$activity;

    public final void onClick(View view)
    {
        view = getContext();
        if (view != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(view, "groove", "more_options_clicked", "", null);
        }
        view = val$activity.mFragments.mHost.mFragmentManager;
        HostDialog.showWithChildFragment(val$activity, view, GrooveEditScreenController.createGroove(new GrooveEditScreenController(), habitModifications, true));
    }

    eenController()
    {
        this$0 = final_groovesummaryview;
        val$activity = FragmentActivity.this;
        super();
    }
}
