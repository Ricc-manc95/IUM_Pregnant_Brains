// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.TimelyDayView;
import com.google.android.calendar.utils.animation.AnimationUtils;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            SwipeInteractionController

final class val.endInteraction
    implements FutureCallback
{

    private final SwipeInteractionController this$0;
    private final Runnable val$endInteraction;
    private final boolean val$isInteractive;
    private final TimelineTaskBundle val$item;
    private final TimelyDayView val$view;

    public final void onFailure(Throwable throwable)
    {
        AnimationUtils.animateThenRun(val$view.createRestoreAnimator(val$item), val$endInteraction);
    }

    public final void onSuccess(Object obj)
    {
        onMarkReminderAsDone(val$view, val$item, val$isInteractive);
        if (val$isInteractive)
        {
            SwipeInteractionController.logUserAction(val$view, "done_all_swipe");
        }
    }

    Q()
    {
        this$0 = final_swipeinteractioncontroller;
        val$view = timelydayview;
        val$item = timelinetaskbundle;
        val$isInteractive = flag;
        val$endInteraction = Runnable.this;
        super();
    }
}
