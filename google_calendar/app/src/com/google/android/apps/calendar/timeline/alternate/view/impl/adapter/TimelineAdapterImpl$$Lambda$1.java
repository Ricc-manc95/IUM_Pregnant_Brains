// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterImpl

final class arg._cls2
    implements Consumer
{

    private final TimelineAdapterImpl arg$1;
    private final ObservableReference arg$2;

    public final void accept(Object obj)
    {
        obj = arg$1;
        if (((Boolean)arg$2.get()).booleanValue() && ((TimelineAdapterImpl) (obj)).dataSetChangedObservable.get() != ((TimelineAdapterImpl) (obj)).lastDataSetChanged)
        {
            obj.lastDataSetChanged = ((TimelineAdapterImpl) (obj)).dataSetChangedObservable.get();
            ((android.support.v7.widget.bleReference.get) (obj)).le.notifyChanged();
        }
    }

    i(TimelineAdapterImpl timelineadapterimpl, ObservableReference observablereference)
    {
        arg$1 = timelineadapterimpl;
        arg$2 = observablereference;
    }
}
