// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent, MonthDayViewHolder

final class arg._cls1
    implements Predicate
{

    private final MonthDayViewHolder arg$1;

    public final boolean apply(Object obj)
    {
        MonthDayViewHolder monthdayviewholder = arg$1;
        return ((AdapterEvent)obj).getMonthSlot() == ((Integer)monthdayviewholder.maxEventsPerDay.get()).intValue() - 1;
    }

    (MonthDayViewHolder monthdayviewholder)
    {
        arg$1 = monthdayviewholder;
    }
}
