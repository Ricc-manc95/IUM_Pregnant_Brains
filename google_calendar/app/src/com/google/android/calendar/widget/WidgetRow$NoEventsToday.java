// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.time.Time;

final class hadEvents extends hadEvents
{

    private final ext.noMoreEventsToday dayInfo;
    private final boolean hadEvents;

    public final int getLayoutId(ext ext)
    {
        return ext.widgetStyle != 1 ? 0x7f050198 : 0x7f050197;
    }

    public final void updateStatus(ext ext, RemoteViews remoteviews)
    {
        super.us(ext, remoteviews);
        if (remoteviews.getLayoutId() == 0x7f050198)
        {
            dayInfo.Status(ext, remoteviews);
        }
        if (hadEvents)
        {
            remoteviews.setTextViewText(0x7f1003aa, ext.noMoreEventsToday);
        }
    }

    ext(int i, Time time, boolean flag, DateTimeFormatHelper datetimeformathelper)
    {
        dayInfo = new (i, i, time);
        hadEvents = flag;
    }
}
