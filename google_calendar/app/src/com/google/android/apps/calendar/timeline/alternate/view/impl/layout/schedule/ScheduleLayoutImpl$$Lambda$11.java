// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.Cut;
import com.google.common.collect.Range;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleLayoutImpl

final class arg._cls1
    implements Consumer
{

    private final ScheduleLayoutImpl arg$1;

    public final void accept(Object obj)
    {
        ScheduleLayoutImpl schedulelayoutimpl = arg$1;
        int i = ((Integer)obj).intValue();
        if (i != ((Integer)((Range)schedulelayoutimpl.selectedRangeObservable.get()).lowerBound.endpoint()).intValue())
        {
            schedulelayoutimpl.selectedRangeObservable.set(Range.closed(Integer.valueOf(i), Integer.valueOf(Math.max(i, schedulelayoutimpl.bottomJulianDay))));
        }
    }

    q(ScheduleLayoutImpl schedulelayoutimpl)
    {
        arg$1 = schedulelayoutimpl;
    }
}
