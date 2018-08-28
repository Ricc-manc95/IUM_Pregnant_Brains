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
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.utils.rtl.RtlUtils;

// Referenced classes of package com.google.android.calendar.widget:
//            WidgetDateUtils

class ItemTimeText extends ItemTimeText
{

    public final String timeString;

    public final int getLayoutId(xt xt)
    {
        return xt.widgetStyle != 1 ? 0x7f05018b : 0x7f05018a;
    }

    protected void updateTextViews(xt xt, RemoteViews remoteviews, int i, int j)
    {
        super.updateTextViews(xt, remoteviews, i, j);
        Object obj1 = timeString;
        Object obj = obj1;
        if (isChipDurationMultidayEnabled())
        {
            if (dayInfo.ay == item.getStartDay())
            {
                obj = DateTimeFormatHelper.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                }
                obj = ((DateTimeFormatHelper)obj).getTimeRangeText(item.getStartMillis(), item.getStartMillis(), 0);
            } else
            {
                obj = obj1;
                if (dayInfo.ay == item.getEndDay())
                {
                    obj = DateTimeFormatHelper.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                    }
                    obj = ((DateTimeFormatHelper)obj).getTimeRangeText(item.getEndMillis(), item.getEndMillis(), 0);
                    obj = xt.context.getResources().getString(0x7f130483, new Object[] {
                        obj
                    });
                }
            }
        }
        if (super.context.getLocation() != null && !super.context.getLocation().isEmpty())
        {
            j = 1;
        } else
        {
            j = 0;
        }
        obj1 = obj;
        if (j != 0)
        {
            obj1 = String.format(xt.timeLocation, new Object[] {
                obj, item.getLocation()
            });
        }
        xt = ((xt) (obj1));
        if (item.hasDeclinedStatus())
        {
            xt = new SpannableString(((CharSequence) (obj1)));
            xt.setSpan(new StrikethroughSpan(), 0, ((CharSequence) (obj1)).length(), 0);
        }
        remoteviews.setViewVisibility(0x7f10039a, 0);
        remoteviews.setTextViewText(0x7f10039a, RtlUtils.forceTextAlignment(xt, super.nment));
        remoteviews.setTextColor(0x7f10039a, i);
    }

    xt(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
    {
        super(timelineitem, datetimeformathelper);
        datetimeformathelper = WidgetDateUtils.instance;
        if (datetimeformathelper == null)
        {
            throw new NullPointerException(String.valueOf("WidgetDateUtils#initialize(...) must be called first"));
        } else
        {
            timeString = ((WidgetDateUtils)datetimeformathelper).getWidgetItemTimeText(timelineitem, 0x80000);
            return;
        }
    }
}
