// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.utils.recycler.Recycler;
import java.lang.ref.WeakReference;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, DayViewConfig, DayPopUpFragment, MonthData

class reference extends TimelyDayView
{

    private final WeakReference reference;

    public final void onUpdate(MonthData monthdata, int i, boolean flag)
    {
        if (i == super.julianDay)
        {
            super.onUpdate(monthdata, i, true);
            if (reference.get() != null)
            {
                DayPopUpFragment.addLayoutChangeListener((OverlayFragment)reference.get(), this);
                return;
            }
        }
    }

    public final void onUpdate(List list, int i, boolean flag)
    {
        if (i == super.julianDay)
        {
            onUpdate(list, Utils.getDateInfo(i), flag);
            mDataLoaded = true;
            if (flag && reference.get() != null)
            {
                DayPopUpFragment.addLayoutChangeListener((OverlayFragment)reference.get(), this);
                return;
            }
        }
    }

    public ener(Context context, Recycler recycler, DayViewConfig dayviewconfig, OverlayFragment overlayfragment)
    {
        float f;
        if (dayviewconfig.inGridMode())
        {
            f = 1.0F;
        } else
        {
            f = 0.0F;
        }
        super(context, recycler, dayviewconfig, new com.google.android.calendar.timely.animations.onStatus.ImmutableTimelineAgendaGridAnimationStatus(false, f), null);
        reference = new WeakReference(overlayfragment);
    }
}
