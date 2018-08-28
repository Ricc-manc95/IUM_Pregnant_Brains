// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.ValueAnimator;
import android.view.View;

public final class arg._cls3
    implements android.animation.dateListener
{

    private final float arg$1;
    private final float arg$2;
    private final View arg$3;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f = arg$1;
        float f1 = arg$2;
        arg$3.setZ(((Float)valueanimator.getAnimatedValue()).floatValue() * (f1 - f) + f);
    }

    public (float f, float f1, View view)
    {
        arg$1 = f;
        arg$2 = f1;
        arg$3 = view;
    }
}
