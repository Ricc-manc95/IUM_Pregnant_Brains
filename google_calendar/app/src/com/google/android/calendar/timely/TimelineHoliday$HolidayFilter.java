// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.apps.calendar.timebox.TimeRange;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineEvent

public final class 
{

    public final SparseArray holidays = new SparseArray();

    public static int getShortHashCode(TimelineEvent timelineevent)
    {
        int j = timelineevent.timeRange.getEndDay();
        int k = timelineevent.timeRange.getEndDay();
        int i;
        if (TextUtils.isEmpty(timelineevent.title))
        {
            i = 0;
        } else
        {
            i = timelineevent.title.hashCode();
        }
        return i + (j ^ k >>> 16) * 31;
    }

    public ()
    {
    }
}
