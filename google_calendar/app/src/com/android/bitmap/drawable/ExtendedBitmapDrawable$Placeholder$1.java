// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.animation.ValueAnimator;

final class this._cls0
    implements android.animation.eholder._cls1
{

    private final validateSelf this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        lseAlpha = ((Integer)valueanimator.getAnimatedValue()).intValue();
        validateSelf();
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
