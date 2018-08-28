// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timeline.chip.Chip;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

final class 
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        return GridDayView.lambda$static$0$GridDayView((Chip)obj, (Chip)obj1);
    }


    private ()
    {
    }
}
