// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.timeline.chipviewmodelfactory:
//            ChipFragmentInfo, ChipViewModelFactory

final class arg._cls4
    implements Supplier
{

    private final ChipViewModelFactory arg$1;
    private final TimelineItem arg$2;
    private final ChipFragmentInfo arg$3;
    private final int arg$4;

    public final Object get()
    {
        boolean flag1 = true;
        ChipViewModelFactory chipviewmodelfactory = arg$1;
        TimelineItem timelineitem = arg$2;
        ChipFragmentInfo chipfragmentinfo = arg$3;
        int i = arg$4;
        Object obj = timelineitem.getTitle();
        String s = ((String) (obj));
        if (obj != null)
        {
            boolean flag;
            if (chipfragmentinfo.showMultiDayAnnotations && TimelineItemUtil.shouldItemBeRenderedAsMultiday(timelineitem))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s = ((String) (obj));
            if (flag)
            {
                s = String.format(chipviewmodelfactory.multidayCountAccessibilityPlaceholder, new Object[] {
                    obj, Integer.valueOf(TimelineItemUtil.getDayIndex(timelineitem, i)), Integer.valueOf(TimelineItemUtil.getNumIntersectingDays(timelineitem))
                });
            }
        }
        obj = chipviewmodelfactory.context;
        if (chipfragmentinfo.viewType != 0)
        {
            flag1 = false;
        }
        return TimelineItemUtil.createContentDescription(((android.content.Context) (obj)), timelineitem, flag1, chipfragmentinfo.contentDescriptionPrefix, chipfragmentinfo.contentDescriptionSuffix, s);
    }

    (ChipViewModelFactory chipviewmodelfactory, TimelineItem timelineitem, ChipFragmentInfo chipfragmentinfo, int i)
    {
        arg$1 = chipviewmodelfactory;
        arg$2 = timelineitem;
        arg$3 = chipfragmentinfo;
        arg$4 = i;
    }
}
