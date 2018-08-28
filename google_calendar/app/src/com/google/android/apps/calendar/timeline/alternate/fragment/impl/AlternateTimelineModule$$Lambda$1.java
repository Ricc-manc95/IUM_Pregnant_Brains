// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ViewModeChangeListener;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            AlternateTimelineModule

final class arg._cls4
    implements com.google.android.apps.calendar.timeline.alternate.view.api.
{

    private final com.google.android.apps.calendar.timeline.alternate.view.api.ier arg$1;
    private final TimelineApi arg$2;
    private final Context arg$3;
    private final ViewModeChangeListener arg$4;

    public final void onClick(int i)
    {
        AlternateTimelineModule.lambda$providesDayHeaderClickListener$2$AlternateTimelineModule(arg$1, arg$2, arg$3, arg$4, i);
    }

    i(com.google.android.apps.calendar.timeline.alternate.view.api.ier ier, TimelineApi timelineapi, Context context, ViewModeChangeListener viewmodechangelistener)
    {
        arg$1 = ier;
        arg$2 = timelineapi;
        arg$3 = context;
        arg$4 = viewmodechangelistener;
    }
}
