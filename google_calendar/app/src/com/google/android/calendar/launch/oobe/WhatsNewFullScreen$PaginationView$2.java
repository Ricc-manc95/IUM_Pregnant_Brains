// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.animation.ValueAnimator;

final class this._cls1
    implements android.animation.
{

    private final validate this$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        deValue = ((Float)valueanimator.getAnimatedValue()).floatValue();
        validate();
    }

    Q()
    {
        this$1 = this._cls1.this;
        super();
    }
}
