// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.animation.ValueAnimator;

// Referenced classes of package com.android.bitmap.drawable:
//            TileDrawable

final class this._cls0
    implements android.animation.nimatorUpdateListener
{

    private final TileDrawable this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        fadeAlpha = ((Integer)valueanimator.getAnimatedValue()).intValue();
        invalidateSelf();
    }

    ateListener()
    {
        this$0 = TileDrawable.this;
        super();
    }
}
