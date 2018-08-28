// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            TypeBucketer

public final class IgnoringBucketer extends TypeBucketer
{

    public IgnoringBucketer()
    {
    }

    final void addToBucket(Object obj, TimeRangeEntry timerangeentry)
    {
    }

    final TimeRangeEntry finalizeBucket(Object obj)
    {
        return null;
    }

    final int hashEntry(TimeRangeEntry timerangeentry)
    {
        return 0;
    }

    final Object newBucket(TimeRangeEntry timerangeentry)
    {
        return null;
    }
}
