// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype:
//            CalendarViewType

public final class EventViewPositionHelper
{

    private static final int MAX_POS;
    public static final int MIN_POS;
    public static final int POS_BUCKET;

    public static int toAlternatePosition(int i)
    {
        int j;
        if (i - MIN_POS < POS_BUCKET)
        {
            j = 1;
        } else
        {
            j = -1;
        }
        return j * POS_BUCKET + i;
    }

    public static int toPrimaryPosition(int i)
    {
        return (i - MIN_POS) % POS_BUCKET + MIN_POS;
    }

    static 
    {
        MIN_POS = CalendarViewType.EVENT.minPosition;
        int i = CalendarViewType.EVENT.maxPosition;
        MAX_POS = i;
        POS_BUCKET = (i - MIN_POS) / 2;
    }
}
