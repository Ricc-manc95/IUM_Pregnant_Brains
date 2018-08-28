// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


// Referenced classes of package com.google.android.apps.calendar.timebox:
//            TimeRange

public abstract class TimeRangeEntry
{

    public TimeRangeEntry()
    {
    }

    public abstract Object getKey();

    public abstract TimeRange getRange();

    public abstract Object getValue();
}
