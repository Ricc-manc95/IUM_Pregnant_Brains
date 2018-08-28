// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.view.View;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineAdapter, TimelyDayHeaderView, OnTimelineModeChangedListener

final class val.context
    implements android.view.er
{

    private final TimelineAdapter this$0;
    private final Context val$context;

    public final void onClick(View view)
    {
        view = val$context.getString(0x7f130449, new Object[] {
            val$context.getString(0x7f1302f6)
        });
        headerView.announceForAccessibility(view);
        view = AnalyticsLoggerHolder.instance;
        if (view == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)view).trackEvent(val$context, "menu_item", "day_toggle");
            timelineModeChangedListener.onModeChanged(headerView.position);
            return;
        }
    }

    gedListener()
    {
        this$0 = final_timelineadapter;
        val$context = Context.this;
        super();
    }
}
