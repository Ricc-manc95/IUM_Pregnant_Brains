// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.design.shadow.ShadowDrawableWrapper;

// Referenced classes of package android.support.design.floatingactionbutton:
//            FloatingActionButtonImpl

abstract class <init> extends AnimatorListenerAdapter
    implements android.animation.onImpl.ShadowAnimatorImpl
{

    private float shadowSizeEnd;
    private float shadowSizeStart;
    private final FloatingActionButtonImpl this$0;
    private boolean validValues;

    protected abstract float getTargetShadowSize();

    public void onAnimationEnd(Animator animator)
    {
        animator = shadowDrawable;
        animator.setShadowSize(shadowSizeEnd, ((ShadowDrawableWrapper) (animator)).rawMaxShadowSize);
        validValues = false;
    }

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        if (!validValues)
        {
            shadowSizeStart = shadowDrawable.rawShadowSize;
            shadowSizeEnd = getTargetShadowSize();
            validValues = true;
        }
        ShadowDrawableWrapper shadowdrawablewrapper = shadowDrawable;
        shadowdrawablewrapper.setShadowSize(shadowSizeStart + (shadowSizeEnd - shadowSizeStart) * valueanimator.getAnimatedFraction(), shadowdrawablewrapper.rawMaxShadowSize);
    }

    private ()
    {
        this$0 = FloatingActionButtonImpl.this;
        super();
    }

    this._cls0(byte byte0)
    {
        this();
    }
}
