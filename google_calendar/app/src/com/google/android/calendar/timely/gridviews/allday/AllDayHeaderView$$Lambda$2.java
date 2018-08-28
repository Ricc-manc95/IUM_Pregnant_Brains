// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            AllDayHeaderView

final class gistry
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        return AllDayHeaderView.lambda$static$0$AllDayHeaderView((gistry)obj, (gistry)obj1);
    }


    private gistry()
    {
    }
}
