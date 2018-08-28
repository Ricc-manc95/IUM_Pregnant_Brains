// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;

import com.google.android.calendar.timely.TimelineItem;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.widgetmonth.model:
//            MonthViewWidgetModel

static final class results
    implements com.google.android.calendar.timely.entResults
{

    public final ArrayList results;

    public final void addTimelineItem(TimelineItem timelineitem)
    {
        results.add(timelineitem);
    }

    ()
    {
        results = new ArrayList();
    }

    results(int i)
    {
        results = new ArrayList(i);
    }
}
