// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.AnimatorSet;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation:
//            AnimatorFuture

public final class AnimatorSetFuture extends AnimatorFuture
{

    public AnimatorSetFuture(IdleTracker idletracker)
    {
        super(idletracker, new AnimatorSet());
    }

    final volatile AnimatorFuture cast()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }
}
