// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleItem, ScheduleLayoutImpl

final class val.offset
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.iew.ActionHandler
{

    private final ScheduleLayoutImpl this$0;
    private final ScheduleItem val$item;
    private final int val$offset;

    public final boolean focus()
    {
        return false;
    }

    public final Optional scroll(boolean flag, Integer integer)
    {
        return Absent.INSTANCE;
    }

    public final boolean showOnScreen()
    {
        int i = val$item.getTop() + val$offset;
        if (i < 0)
        {
            ScheduleLayoutImpl schedulelayoutimpl = ScheduleLayoutImpl.this;
            i = -i;
            schedulelayoutimpl.viewportAnimator.animateViewportChange(new <init>(schedulelayoutimpl, i));
            return true;
        }
        i = val$item.getBottom() + val$offset;
        if (i > hostView.getMeasuredHeight())
        {
            ScheduleLayoutImpl schedulelayoutimpl1 = ScheduleLayoutImpl.this;
            int j = hostView.getMeasuredHeight();
            schedulelayoutimpl1.viewportAnimator.animateViewportChange(new <init>(schedulelayoutimpl1, j - i));
            return true;
        } else
        {
            return false;
        }
    }

    nHandler()
    {
        this$0 = final_schedulelayoutimpl;
        val$item = scheduleitem;
        val$offset = I.this;
        super();
    }
}
