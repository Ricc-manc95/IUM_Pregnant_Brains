// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.ValueAnimator;
import com.google.common.util.concurrent.AbstractFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation:
//            ValueAnimatorFuture

final class arg._cls2
    implements android.animation.stener
{

    private final ValueAnimatorFuture arg$1;
    private final onListener arg$2;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        ValueAnimatorFuture valueanimatorfuture = arg$1;
        onListener onlistener = arg$2;
        if (!valueanimatorfuture.isCancelled())
        {
            onlistener.onStep(((Float)valueanimator.getAnimatedValue()).floatValue());
        }
    }

    onListener(ValueAnimatorFuture valueanimatorfuture, onListener onlistener)
    {
        arg$1 = valueanimatorfuture;
        arg$2 = onlistener;
    }
}
