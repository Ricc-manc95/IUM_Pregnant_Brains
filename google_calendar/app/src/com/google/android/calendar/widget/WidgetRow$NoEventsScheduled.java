// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.time.Time;

final class dayInfo extends dayInfo
{

    private final us dayInfo;

    public final int getLayoutId(dayInfo dayinfo)
    {
        return dayinfo.widgetStyle != 1 ? 0x7f050196 : 0x7f050195;
    }

    public final void updateStatus(widgetStyle widgetstyle, RemoteViews remoteviews)
    {
        super.widgetStyle(widgetstyle, remoteviews);
        if (remoteviews.getLayoutId() == 0x7f050196)
        {
            dayInfo.us(widgetstyle, remoteviews);
        }
    }

    (int i, Time time, DateTimeFormatHelper datetimeformathelper)
    {
        dayInfo = new dayInfo(i, i, time);
    }
}
