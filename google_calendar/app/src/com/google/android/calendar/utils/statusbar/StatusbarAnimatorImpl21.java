// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.statusbar;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import com.google.android.calendar.utils.animation.Properties;

// Referenced classes of package com.google.android.calendar.utils.statusbar:
//            StatusbarAnimatorCompat

class StatusbarAnimatorImpl21 extends StatusbarAnimatorCompat
{

    private final Window window;

    public StatusbarAnimatorImpl21(Window window1)
    {
        window = window1;
    }

    public void addLightStatusbarChangeToAnimationStart(Animator animator, boolean flag)
    {
    }

    public final Animator animateStatusbarColor(int i, int j, int k)
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofArgb(window, Properties.STATUS_BAR_COLOR, new int[] {
            k, i
        });
        objectanimator.setDuration(j);
        objectanimator.setEvaluator(new ArgbEvaluator());
        objectanimator.setInterpolator(new LinearInterpolator());
        return objectanimator;
    }

    public final int getStatusbarColor()
    {
        return window.getStatusBarColor();
    }

    public boolean isLightStatusbarEffective()
    {
        return false;
    }

    public void setLightStatusbar(boolean flag)
    {
    }

    public final void setStatusbarColor(int i)
    {
        window.setStatusBarColor(i);
    }
}
