// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.ValueAnimator;
import android.view.View;

public final class arg._cls1
    implements android.animation.dateListener
{

    private final View arg$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        arg$1.setAlpha(((Float)valueanimator.getAnimatedValue()).floatValue());
    }

    public (View view)
    {
        arg$1 = view;
    }
}
