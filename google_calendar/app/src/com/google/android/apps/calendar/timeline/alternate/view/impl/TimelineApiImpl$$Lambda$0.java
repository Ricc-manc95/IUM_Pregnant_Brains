// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.Layout;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManager;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineApiImpl

final class arg._cls3
    implements Consumer
{

    private final TimelineApiImpl arg$1;
    private final ObservableReference arg$2;
    private final ObservableReference arg$3;

    public final void accept(Object obj)
    {
        obj = arg$1;
        ObservableReference observablereference = arg$2;
        ObservableReference observablereference1 = arg$3;
        if (((Boolean)observablereference.get()).booleanValue() && !((TimelineApiImpl) (obj)).lastCurrentTime.equals(observablereference1.get()))
        {
            obj.lastCurrentTime = (Long)observablereference1.get();
            ((TimelineApiImpl) (obj)).layoutManager.getLayout().invalidateCache();
            ((TimelineApiImpl) (obj)).recyclerView.mAdapter.rvable.notifyChanged();
            ((TimelineApiImpl) (obj)).recyclerView.invalidate();
            ((TimelineApiImpl) (obj)).recyclerView.requestLayout();
        }
    }

    (TimelineApiImpl timelineapiimpl, ObservableReference observablereference, ObservableReference observablereference1)
    {
        arg$1 = timelineapiimpl;
        arg$2 = observablereference;
        arg$3 = observablereference1;
    }
}
