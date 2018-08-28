// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            AlternateTimelineModule

final class arg._cls4
    implements com.google.android.apps.calendar.timeline.alternate.view.api.ier
{

    private final ObservableReference arg$1;
    private final ObservableReference arg$2;
    private final TimelineApi arg$3;
    private final Context arg$4;

    public final Optional nextViewMode()
    {
        return AlternateTimelineModule.lambda$providesDayHeaderNextModeSupplier$0$AlternateTimelineModule(arg$1, arg$2, arg$3, arg$4);
    }

    i(ObservableReference observablereference, ObservableReference observablereference1, TimelineApi timelineapi, Context context)
    {
        arg$1 = observablereference;
        arg$2 = observablereference1;
        arg$3 = timelineapi;
        arg$4 = context;
    }
}
