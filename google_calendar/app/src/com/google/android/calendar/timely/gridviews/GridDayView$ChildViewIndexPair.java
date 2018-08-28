// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.view.View;
import com.google.android.calendar.timely.gridviews.geometry.PositionOnGrid;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

static final class index
    implements Comparable
{

    public final int index;
    private final int z;

    public final int compareTo(Object obj)
    {
        obj = (index)obj;
        return GridDayView.compare(z, ((z) (obj)).z);
    }

    Q(View view, int i)
    {
        z = ((z)view.getLayoutParams()).on.z;
        index = i;
    }
}
