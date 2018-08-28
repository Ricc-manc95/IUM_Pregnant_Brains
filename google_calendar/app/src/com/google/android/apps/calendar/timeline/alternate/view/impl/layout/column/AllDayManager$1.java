// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            AllDayManager

final class val.idleEvent extends AnimatorListenerAdapter
{

    private final AllDayManager this$0;
    private final com.google.android.apps.calendar.timeline.alternate.view.impl.util.ingEvent val$idleEvent;

    public final void onAnimationEnd(Animator animator)
    {
        val$idleEvent.onComplete();
        hostView.invalidate();
        hostView.requestLayout();
    }

    a()
    {
        this$0 = final_alldaymanager;
        val$idleEvent = com.google.android.apps.calendar.timeline.alternate.view.impl.util.ingEvent.this;
        super();
    }
}
