// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.utils.AccessibilityUtils;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            WeekHeaderLabelsView

final class val.daysHeaders
    implements Runnable
{

    private final WeekHeaderLabelsView val$daysHeaders;

    public final void run()
    {
        val$daysHeaders.requestFocus();
        AccessibilityUtils.requestAccessibilityFocus(val$daysHeaders);
    }

    I()
    {
        val$daysHeaders = weekheaderlabelsview;
        super();
    }
}
