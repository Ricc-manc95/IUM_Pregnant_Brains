// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.TimelineItem;

final class  extends 
{

    public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
    {
        remoteviews.setOnClickFillInIntent(0x7f100398, elineItemFillIntent(context, Long.valueOf(0L)));
    }

    (TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
    {
        super(timelineitem, datetimeformathelper);
    }
}
