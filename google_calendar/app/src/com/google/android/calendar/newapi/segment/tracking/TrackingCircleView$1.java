// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.tracking;

import android.animation.ValueAnimator;

// Referenced classes of package com.google.android.calendar.newapi.segment.tracking:
//            TrackingCircleView

final class this._cls0
    implements android.animation.rUpdateListener
{

    private final TrackingCircleView this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        angle = ((Float)valueanimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    ()
    {
        this$0 = TrackingCircleView.this;
        super();
    }
}
