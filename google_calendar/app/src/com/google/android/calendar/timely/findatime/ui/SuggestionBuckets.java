// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuggestionBuckets
{

    public static final Comparator BUCKET_STARTTIME_COMPARATOR = new _cls2();
    public static final List EMPTY_SUGGESTION_BUCKET = Collections.unmodifiableList(new ArrayList());
    public static final Comparator SUGGESTION_STARTTIME_COMPARATOR = new _cls1();
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/ui/SuggestionBuckets);
    public final String bestTimeLabel;
    public final Context context;

    public SuggestionBuckets(Context context1, String s)
    {
        context = context1;
        bestTimeLabel = s;
    }

    public static int getBestTimesCount(List list, int i)
    {
        int j = i;
        if (i > 0)
        {
            j = Math.min(3, list.size());
        }
        return j;
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (TimelineSuggestion)obj;
            obj1 = (TimelineSuggestion)obj1;
            return Long.valueOf(((TimelineEvent) (obj)).timeRange.getUtcStartMillis()).compareTo(Long.valueOf(((TimelineEvent) (obj1)).timeRange.getUtcStartMillis()));
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (SuggestionBucket)obj;
            obj1 = (SuggestionBucket)obj1;
            return Long.valueOf(((TimelineEvent) ((TimelineSuggestion)((SuggestionBucket) (obj)).suggestions.get(0))).timeRange.getUtcStartMillis()).compareTo(Long.valueOf(((TimelineEvent) ((TimelineSuggestion)((SuggestionBucket) (obj1)).suggestions.get(0))).timeRange.getUtcStartMillis()));
        }

        _cls2()
        {
        }
    }

}
