// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.statusbar;

import android.animation.Animator;
import android.view.View;
import android.view.Window;

// Referenced classes of package com.google.android.calendar.utils.statusbar:
//            StatusbarAnimatorImpl21

final class StatusbarAnimatorImpl23 extends StatusbarAnimatorImpl21
{

    private final View decorView;

    public StatusbarAnimatorImpl23(Window window)
    {
        super(window);
        decorView = window.getDecorView();
    }

    public final void addLightStatusbarChangeToAnimationStart(Animator animator, final boolean lightStatusbarEnabled)
    {
        animator.addListener(new _cls1());
    }

    public final boolean isLightStatusbarEffective()
    {
        return (decorView.getSystemUiVisibility() & 0x2000) != 0;
    }

    public final void setLightStatusbar(boolean flag)
    {
        View view = decorView;
        int i = decorView.getSystemUiVisibility();
        if (flag)
        {
            i |= 0x2000;
        } else
        {
            i &= 0xffffdfff;
        }
        view.setSystemUiVisibility(i);
    }

    private class _cls1
        implements android.animation.Animator.AnimatorListener
    {

        private final StatusbarAnimatorImpl23 this$0;
        private final boolean val$lightStatusbarEnabled;
        private boolean wasLSBpreAnimation;

        public final void onAnimationCancel(Animator animator)
        {
            setLightStatusbar(wasLSBpreAnimation);
        }

        public final void onAnimationEnd(Animator animator)
        {
        }

        public final void onAnimationRepeat(Animator animator)
        {
        }

        public final void onAnimationStart(Animator animator)
        {
            wasLSBpreAnimation = isLightStatusbarEffective();
            setLightStatusbar(lightStatusbarEnabled);
        }

        _cls1()
        {
            this$0 = StatusbarAnimatorImpl23.this;
            lightStatusbarEnabled = flag;
            super();
        }
    }

}
