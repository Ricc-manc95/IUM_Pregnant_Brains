// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData, TimelineTaskType, TimelineItem

public static class startDay
    implements s
{

    public final int endDay;
    public final List mList = new ArrayList();
    public final int startDay;

    public void addTimelineItem(TimelineItem timelineitem)
    {
        if (timelineitem instanceof TimelineTaskType)
        {
            timelineitem = (TimelineTaskType)timelineitem;
            mList.add(timelineitem);
        }
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof mList) && mList.equals(((mList)obj).mList);
    }

    public int hashCode()
    {
        int l = mList.size();
        int i = 0;
        int j = 1;
        while (i < l) 
        {
            TimelineTaskType timelinetasktype = (TimelineTaskType)mList.get(i);
            int k;
            if (timelinetasktype == null || timelinetasktype.getId() == null)
            {
                k = 0;
            } else
            {
                k = timelinetasktype.getId().hashCode();
            }
            j = j * 31 + k;
            i++;
        }
        return j;
    }

    public s(int i, int j)
    {
        endDay = j;
        startDay = i;
    }
}
