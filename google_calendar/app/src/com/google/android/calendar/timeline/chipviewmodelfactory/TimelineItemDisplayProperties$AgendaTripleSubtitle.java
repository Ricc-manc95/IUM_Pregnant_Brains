// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.content.res.Resources;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.TimelineTaskBundle;

// Referenced classes of package com.google.android.calendar.timeline.chipviewmodelfactory:
//            ChipFragmentInfo

class untilPlaceholder extends TimelineItemOperation
{

    private final String untilPlaceholder;

    public volatile Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return onAny(timelineitem, (Integer[])aobj);
    }

    public transient String onAny(TimelineItem timelineitem, Integer ainteger[])
    {
        boolean flag;
        if (ainteger.length > 0 && ainteger.length <= 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        int j = ainteger[0].intValue();
        int i;
        if (ainteger.length > 1)
        {
            i = ainteger[1].intValue();
        } else
        {
            i = 0;
        }
        if (timelineitem.isAllDay() || TimelineItemUtil.isTimedMidnightToNextMidnight(timelineitem))
        {
            return null;
        }
        if (TimelineItemUtil.shouldItemBeRenderedAsMultiday(timelineitem))
        {
            if (TimelineItemUtil.isFirstDay(timelineitem, j) || ChipFragmentInfo.shouldForceFirstDay(j))
            {
                ainteger = DateTimeFormatHelper.instance;
                if (ainteger == null)
                {
                    throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                } else
                {
                    return ((DateTimeFormatHelper)ainteger).getTimeRangeText(timelineitem.getStartMillis(), timelineitem.getStartMillis(), i);
                }
            }
            if (TimelineItemUtil.isLastDay(timelineitem, j))
            {
                ainteger = DateTimeFormatHelper.instance;
                if (ainteger == null)
                {
                    throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                } else
                {
                    timelineitem = ((DateTimeFormatHelper)ainteger).getTimeRangeText(timelineitem.getEndMillis(), timelineitem.getEndMillis(), i);
                    return String.format(untilPlaceholder, new Object[] {
                        timelineitem
                    });
                }
            } else
            {
                return null;
            }
        }
        ainteger = DateTimeFormatHelper.instance;
        if (ainteger == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        ainteger = (DateTimeFormatHelper)ainteger;
        long l1 = timelineitem.getStartMillis();
        long l;
        if (timelineitem.showEndTime())
        {
            l = timelineitem.getEndMillis();
        } else
        {
            l = timelineitem.getStartMillis();
        }
        return ainteger.getTimeRangeText(l1, l, i);
    }

    public final Object onBirthdayBundle(TimelineBirthday timelinebirthday, Object aobj[])
    {
        return timelinebirthday.subtitle;
    }

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        return timelinetaskbundle.subtitle;
    }

    public (Resources resources)
    {
        untilPlaceholder = resources.getString(0x7f130483);
    }
}
