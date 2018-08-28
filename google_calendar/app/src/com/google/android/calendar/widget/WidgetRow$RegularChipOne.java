// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.rtl.RtlUtils;

class xt extends xt
{

    public int getLayoutId(xt xt)
    {
        if (xt.widgetStyle == 1)
        {
            return 0x7f050187;
        }
        return isFirst ? 0x7f050189 : 0x7f050188;
    }

    protected final String getTitleDescription(xt xt, boolean flag)
    {
        String s = item.getTitle();
        if (isChipDurationMultidayEnabled())
        {
            xt = xt.context.getResources();
            int i;
            if (flag)
            {
                i = 0x7f130079;
            } else
            {
                i = 0x7f130481;
            }
            return xt.getString(i, new Object[] {
                s, Integer.valueOf((dayInfo.ay - item.getStartDay()) + 1), Integer.valueOf((item.getEndDay() - item.getStartDay()) + 1)
            });
        } else
        {
            return s;
        }
    }

    protected boolean isChipDurationMultidayEnabled()
    {
        return TimelineItemUtil.shouldItemBeRenderedAsMultiday(item);
    }

    public void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
    {
        remoteviews.setOnClickFillInIntent(0x7f100398, lineItemFillIntent(context, (EventKey)((TimelineEvent)item).eventKey));
    }

    public void updateTextViews(xt xt, RemoteViews remoteviews, int i)
    {
        Context context = xt.context;
        int j;
        if (TimelineItemUtil.isAnyReminder(item))
        {
            j = 0x7f020139;
        } else
        if (TimelineItemUtil.isGroove(item))
        {
            j = 0x7f0201fb;
        } else
        if (TimelineItemUtil.isOutOfOfficeEvent(item))
        {
            j = 0x7f0201ef;
        } else
        if (TimelineItemUtil.isProposeNewTimeEvent(item, context))
        {
            j = 0x7f02022f;
        } else
        if (TimelineItemUtil.isEveryoneDeclinedEvent(item))
        {
            j = 0x7f020127;
        } else
        {
            j = 0;
        }
        updateTextViews(xt, remoteviews, i, j);
    }

    protected void updateTextViews(xt xt, RemoteViews remoteviews, int i, int j)
    {
        xt = scription(xt, false);
        if (item.hasDeclinedStatus())
        {
            SpannableString spannablestring = new SpannableString(xt);
            spannablestring.setSpan(new StrikethroughSpan(), 0, xt.length(), 0);
            xt = spannablestring;
        }
        remoteviews.setViewVisibility(0x7f100047, 0);
        remoteviews.setTextViewText(0x7f100047, RtlUtils.forceTextAlignment(xt, super.nment));
        remoteviews.setTextColor(0x7f100047, i);
        updateIcon(remoteviews, i, j, TimelineItemUtil.useBadgedIcon(item), item.getColor());
    }

    xt(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
    {
        super(timelineitem);
    }
}
