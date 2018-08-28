// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.LaunchInfoActivityUtils;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.rtl.RtlUtils;

final class le extends le
{

    private final int namesResource = 0x7f10039a;
    private final String subtitle;

    public final int getLayoutId(text text)
    {
        if (text.widgetStyle == 1)
        {
            return 0x7f050187;
        }
        if (TextUtils.isEmpty(subtitle))
        {
            return isFirst ? 0x7f050189 : 0x7f050188;
        } else
        {
            return 0x7f05018b;
        }
    }

    protected final String getTitleDescription(text text, boolean flag)
    {
        if (TextUtils.isEmpty(subtitle))
        {
            return item.getTitle();
        } else
        {
            text = item.getTitle();
            String s = subtitle;
            return (new StringBuilder(String.valueOf(text).length() + 2 + String.valueOf(s).length())).append(text).append(", ").append(s).toString();
        }
    }

    public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
    {
        context = LaunchInfoActivityUtils.getLaunchFillInIntent(context, Long.valueOf(0L));
        TimelineItemUtil.setLaunchTimelineItem(context, super.nchTimelineItem);
        context.putExtra("beginTime", super.nchTimelineItem.getStartMillis());
        context.putExtra("allDay", true);
        remoteviews.setOnClickFillInIntent(0x7f100398, context);
    }

    public final void updateStatus(text text, RemoteViews remoteviews)
    {
        ((TimelineBirthday)item).validate(text.context);
        super.atus(text, remoteviews);
    }

    public final void updateTextViews(text text, RemoteViews remoteviews, int i)
    {
        text = item.getTitle();
        remoteviews.setViewVisibility(0x7f100047, 0);
        remoteviews.setTextViewText(0x7f100047, RtlUtils.forceTextAlignment(text, super.ignment));
        remoteviews.setTextColor(0x7f100047, i);
        updateIcon(remoteviews, i, 0, TimelineItemUtil.useBadgedIcon(item), item.getColor());
        if (!TextUtils.isEmpty(subtitle))
        {
            int j = namesResource;
            text = subtitle;
            remoteviews.setViewVisibility(j, 0);
            remoteviews.setTextViewText(j, RtlUtils.forceTextAlignment(text, super.ignment));
            remoteviews.setTextColor(j, i);
        }
    }

    text(TimelineBirthday timelinebirthday, DateTimeFormatHelper datetimeformathelper)
    {
        super(timelinebirthday);
        subtitle = timelinebirthday.subtitle;
    }
}
