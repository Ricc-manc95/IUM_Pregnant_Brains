// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar

final class val.translationYBottom
    implements android.animation.ateListener
{

    private int previousAnimatedIntValue;
    private final BaseTransientBottomBar this$0;
    private final int val$translationYBottom;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
        view.setTranslationY(i);
        previousAnimatedIntValue = i;
    }

    ackbarBaseLayout()
    {
        this$0 = final_basetransientbottombar;
        val$translationYBottom = I.this;
        super();
        previousAnimatedIntValue = val$translationYBottom;
    }
}
