// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.app.Activity;
import android.view.View;
import com.google.android.calendar.Utils;
import com.google.android.calendar.utils.recycler.Recycler;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewPagerAdapter, WeekFragment, DndEventHandler

public final class  extends GridViewPagerAdapter
{

    public final int getCount()
    {
        return 3497;
    }

    public final int getFirstJulianDayOfItem(int i)
    {
        return Utils.getJulianFirstDayFromWeeksSinceEpoch(i, Utils.getFirstDayOfWeekAsTime(mActivity));
    }

    public (WeekFragment weekfragment, Activity activity, View view, Recycler recycler)
    {
        super(activity, 7, 0x7f050184, recycler, DndEventHandler.create(weekfragment.dndController, view));
    }
}
