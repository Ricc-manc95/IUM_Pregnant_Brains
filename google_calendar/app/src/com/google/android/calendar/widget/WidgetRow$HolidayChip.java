// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.TimelineHoliday;

final class ne extends ne
{

    public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
    {
        remoteviews.setOnClickFillInIntent(0x7f100398, imelineItemFillIntent(context, Long.valueOf(0L)));
    }

    ne(TimelineHoliday timelineholiday, DateTimeFormatHelper datetimeformathelper)
    {
        super(timelineholiday, datetimeformathelper);
    }
}
