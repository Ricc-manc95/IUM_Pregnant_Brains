// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.ValueAnimator;

final class arg._cls1
    implements android.animation..Lambda._cls1
{

    private final kerRight arg$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        arg$1.kerRight(((Float)valueanimator.getAnimatedValue()).floatValue());
    }

    ( )
    {
        arg$1 = ;
    }
}
