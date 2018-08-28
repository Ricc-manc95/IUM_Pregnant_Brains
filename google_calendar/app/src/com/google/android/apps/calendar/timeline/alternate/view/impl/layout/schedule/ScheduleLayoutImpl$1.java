// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleLayoutImpl

final class val.scrollBackwardPx
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.iew.ActionHandler
{

    private final ScheduleLayoutImpl this$0;
    private final int val$scrollBackwardPx;
    private final int val$scrollForwardPx;

    public final boolean focus()
    {
        return false;
    }

    public final Optional scroll(boolean flag, Integer integer)
    {
        int i;
        if (flag)
        {
            i = val$scrollForwardPx;
        } else
        {
            i = val$scrollBackwardPx;
        }
        if (i != 0)
        {
            integer = ScheduleLayoutImpl.this;
            class .Lambda._cls0
                implements Function
            {

                public static final Function $instance = new .Lambda._cls0();

                public final Object apply(Object obj)
                {
                    return null;
                }


            private .Lambda._cls0()
            {
            }
            }

            integer = (FluentFuture)AbstractTransformFuture.create(((ScheduleLayoutImpl) (integer)).viewportAnimator.animateViewportChange(new <init>(integer, i)), .Lambda._cls0..instance, com.google.common.util.concurrent.xecutor.INSTANCE);
            if (integer == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(integer);
            }
        } else
        {
            return Absent.INSTANCE;
        }
    }

    public final boolean showOnScreen()
    {
        return false;
    }

    .Lambda._cls0()
    {
        this$0 = final_schedulelayoutimpl;
        val$scrollForwardPx = i;
        val$scrollBackwardPx = I.this;
        super();
    }
}
