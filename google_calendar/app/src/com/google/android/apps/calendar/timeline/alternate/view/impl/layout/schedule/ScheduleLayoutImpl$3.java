// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleLayoutImpl

final class consumed
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.geAnimationListener
{

    private int consumed;
    private final ScheduleLayoutImpl this$0;
    private final int val$deltaPx;

    public final void onStep(float f)
    {
        int i = (int)((float)val$deltaPx * f - (float)consumed);
        consumed = consumed + i;
        ScheduleLayoutImpl schedulelayoutimpl = ScheduleLayoutImpl.this;
        schedulelayoutimpl.offsetPx = i + schedulelayoutimpl.offsetPx;
        hostView.requestLayout();
    }

    ngeAnimationListener()
    {
        this$0 = final_schedulelayoutimpl;
        val$deltaPx = I.this;
        super();
        consumed = 0;
    }
}
