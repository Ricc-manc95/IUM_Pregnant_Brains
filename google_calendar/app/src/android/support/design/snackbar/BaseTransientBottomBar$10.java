// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar

final class previousAnimatedIntValue
    implements android.animation.teListener
{

    private int previousAnimatedIntValue;
    private final BaseTransientBottomBar this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
        view.setTranslationY(i);
        previousAnimatedIntValue = i;
    }

    ckbarBaseLayout()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
        previousAnimatedIntValue = 0;
    }
}
