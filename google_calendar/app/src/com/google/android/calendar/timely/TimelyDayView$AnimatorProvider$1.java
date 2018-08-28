// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.ValueAnimator;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView

final class lastValue
    implements android.animation.ner
{

    private int lastValue;
    private final lastValue this$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
        int j = lastValue;
        lastValue = i;
        nowLineYAgenda = (i - j) + nowLineYAgenda;
    }

    I()
    {
        this$1 = this._cls1.this;
        super();
        lastValue = 0;
    }
}
