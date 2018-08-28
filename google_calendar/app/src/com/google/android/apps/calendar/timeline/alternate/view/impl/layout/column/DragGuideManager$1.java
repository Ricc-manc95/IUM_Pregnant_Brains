// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.LongSparseArray;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            DragGuideManager

final class val.time extends AnimatorListenerAdapter
{

    private final DragGuideManager this$0;
    private final long val$time;

    public final void onAnimationEnd(Animator animator)
    {
        animator = Long.valueOf(val$time);
        Long long1 = lastTime;
        boolean flag;
        if (animator == long1 || animator != null && animator.equals(long1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            animators.remove(val$time);
        }
    }

    ()
    {
        this$0 = final_dragguidemanager;
        val$time = J.this;
        super();
    }
}
