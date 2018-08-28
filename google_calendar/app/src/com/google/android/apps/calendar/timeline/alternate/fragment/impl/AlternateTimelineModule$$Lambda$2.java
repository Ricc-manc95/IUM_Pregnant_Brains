// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ViewModeChangeListener;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            AlternateTimelineModule

final class arg._cls4
    implements Consumer
{

    private final TimelineApi arg$1;
    private final int arg$2;
    private final Context arg$3;
    private final ViewModeChangeListener arg$4;

    public final void accept(Object obj)
    {
        AlternateTimelineModule.lambda$providesDayHeaderClickListener$1$AlternateTimelineModule(arg$1, arg$2, arg$3, arg$4, (ViewMode)obj);
    }

    i(TimelineApi timelineapi, int i, Context context, ViewModeChangeListener viewmodechangelistener)
    {
        arg$1 = timelineapi;
        arg$2 = i;
        arg$3 = context;
        arg$4 = viewmodechangelistener;
    }
}
