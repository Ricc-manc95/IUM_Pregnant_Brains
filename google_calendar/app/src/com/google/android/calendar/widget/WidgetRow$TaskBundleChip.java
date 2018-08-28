// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTaskBundle;

final class le extends le
{

    private final String subTitle;

    public final int getLayoutId(xt xt)
    {
        return xt.widgetStyle != 1 ? 0x7f05018b : 0x7f05018a;
    }

    protected final boolean isChipDurationMultidayEnabled()
    {
        return false;
    }

    public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
    {
        remoteviews.setOnClickFillInIntent(0x7f100398, lineItemFillIntent(context, Long.valueOf(0L)));
    }

    public final void updateTextViews(xt xt, RemoteViews remoteviews, int i)
    {
        super.updateTextViews(xt, remoteviews, i);
        iew(remoteviews, 0x7f10039a, 0, subTitle, i);
    }

    xt(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
    {
        super(timelineitem, datetimeformathelper);
        subTitle = ((TimelineTaskBundle)timelineitem).subtitle;
    }
}
