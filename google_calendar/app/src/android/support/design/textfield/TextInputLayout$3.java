// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.animation.ValueAnimator;
import android.support.design.internal.CollapsingTextHelper;

// Referenced classes of package android.support.design.textfield:
//            TextInputLayout

final class this._cls0
    implements android.animation.atorUpdateListener
{

    private final TextInputLayout this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        collapsingTextHelper.setExpansionFraction(((Float)valueanimator.getAnimatedValue()).floatValue());
    }

    r()
    {
        this$0 = TextInputLayout.this;
        super();
    }
}
