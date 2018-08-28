// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.net;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineSuggestion;
import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely.findatime.net:
//            FindTimeExchangeClient

static final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (TimelineSuggestion)obj;
        obj1 = (TimelineSuggestion)obj1;
        return ComparisonChain.ACTIVE.compare(((TimelineSuggestion) (obj)).totalPenalty, ((TimelineSuggestion) (obj1)).totalPenalty).compare(((TimelineEvent) (obj)).timeRange.getUtcStartMillis(), ((TimelineEvent) (obj1)).timeRange.getUtcStartMillis()).result();
    }

    ()
    {
    }
}
