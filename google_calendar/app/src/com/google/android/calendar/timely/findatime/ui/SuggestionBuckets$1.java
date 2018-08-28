// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineSuggestion;
import java.util.Comparator;

final class I
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (TimelineSuggestion)obj;
        obj1 = (TimelineSuggestion)obj1;
        return Long.valueOf(((TimelineEvent) (obj)).timeRange.getUtcStartMillis()).compareTo(Long.valueOf(((TimelineEvent) (obj1)).timeRange.getUtcStartMillis()));
    }

    I()
    {
    }
}
