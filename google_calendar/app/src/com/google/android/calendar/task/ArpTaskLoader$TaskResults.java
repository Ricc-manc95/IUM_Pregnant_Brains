// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.TimelineTaskType;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task:
//            BundleTaskResults, ArpTaskLoader

final class finalStorage extends BundleTaskResults
{

    private final com.google.android.calendar.timely.itles finalStorage;
    private final ArpTaskLoader this$0;

    public final void finalizeStorage()
    {
        super.finalizeStorage();
        TimelineTaskType timelinetasktype;
        for (Iterator iterator = mList.iterator(); iterator.hasNext(); finalStorage.dTimelineItem(timelinetasktype))
        {
            timelinetasktype = (TimelineTaskType)iterator.next();
            if (timelinetasktype instanceof TimelineTaskBundle)
            {
                ((TimelineTaskBundle)timelinetasktype).updateTitles(context);
            }
        }

    }

    (com.google.android.calendar.timely. , int i, int j)
    {
        this$0 = ArpTaskLoader.this;
        super(i, j);
        finalStorage = ;
    }
}
