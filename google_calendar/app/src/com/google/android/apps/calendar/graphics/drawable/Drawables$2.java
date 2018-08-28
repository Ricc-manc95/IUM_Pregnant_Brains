// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;

// Referenced classes of package com.google.android.apps.calendar.graphics.drawable:
//            DrawableWrapper

public final class per extends DrawableWrapper
{

    private boolean animatorWasStarted;
    private final ValueAnimator val$animator;

    public final boolean setVisible(boolean flag, boolean flag1)
    {
        if (flag) goto _L2; else goto _L1
_L1:
        val$animator.cancel();
_L4:
        return super.setVisible(flag, flag1);
_L2:
        if (!animatorWasStarted || flag1)
        {
            val$animator.start();
            animatorWasStarted = true;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public per(ValueAnimator valueanimator)
    {
        val$animator = valueanimator;
        super(final_drawable);
    }
}
