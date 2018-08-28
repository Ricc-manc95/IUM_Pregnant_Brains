// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            AllDayHeaderView

final class gistry
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return AllDayHeaderView.lambda$makePartitionItemsAndResolveConflicts$2$AllDayHeaderView((gistry)obj);
    }


    private gistry()
    {
    }
}
