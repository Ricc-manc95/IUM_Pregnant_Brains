// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import com.android.calendarcommon2.LogUtils;
import java.util.List;

public final class SuggestionBucket
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/ui/SuggestionBucket);
    public final String displayLabel;
    public final boolean isBestBucket;
    public final List suggestions;

    public SuggestionBucket(String s, List list, boolean flag)
    {
        if (s == null)
        {
            LogUtils.wtf(TAG, "Display label should not be null", new Object[0]);
        }
        displayLabel = s;
        suggestions = list;
        isBestBucket = flag;
    }

}
