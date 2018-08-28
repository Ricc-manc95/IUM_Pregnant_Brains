// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.widget.RemoteViews;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.rtl.RtlUtils;

final class Two extends Two
{

    protected final void updateTextViews(ontext ontext, RemoteViews remoteviews, int i, int j)
    {
        ontext = item.getTitle();
        String s = timeString;
        if (((TimelineGroove)item).completed)
        {
            SpannableString spannablestring = new SpannableString(ontext);
            spannablestring.setSpan(new StrikethroughSpan(), 0, ontext.length(), 0);
            ontext = spannablestring;
        }
        remoteviews.setViewVisibility(0x7f100047, 0);
        remoteviews.setTextViewText(0x7f100047, RtlUtils.forceTextAlignment(ontext, super.Alignment));
        remoteviews.setTextColor(0x7f100047, i);
        remoteviews.setViewVisibility(0x7f10039a, 0);
        remoteviews.setTextViewText(0x7f10039a, RtlUtils.forceTextAlignment(s, super.Alignment));
        remoteviews.setTextColor(0x7f10039a, i);
        updateIcon(remoteviews, i, j, TimelineItemUtil.useBadgedIcon(item), item.getColor());
    }

    ontext(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
    {
        super(timelineitem, datetimeformathelper);
    }
}
