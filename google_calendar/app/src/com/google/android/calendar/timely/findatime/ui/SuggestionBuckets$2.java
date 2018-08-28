// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineSuggestion;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            SuggestionBucket

final class I
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (SuggestionBucket)obj;
        obj1 = (SuggestionBucket)obj1;
        return Long.valueOf(((TimelineEvent) ((TimelineSuggestion)((SuggestionBucket) (obj)).suggestions.get(0))).timeRange.getUtcStartMillis()).compareTo(Long.valueOf(((TimelineEvent) ((TimelineSuggestion)((SuggestionBucket) (obj1)).suggestions.get(0))).timeRange.getUtcStartMillis()));
    }

    I()
    {
    }
}
