// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.statusbar;

import android.animation.Animator;
import android.view.Window;

// Referenced classes of package com.google.android.calendar.utils.statusbar:
//            StatusbarAnimatorImpl23, StatusbarAnimatorImpl21

public abstract class StatusbarAnimatorCompat
{

    public StatusbarAnimatorCompat()
    {
    }

    public static StatusbarAnimatorCompat createInstance(Window window)
    {
        boolean flag;
        if (window != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return new StatusbarAnimatorImpl23(window);
        } else
        {
            return new StatusbarAnimatorImpl21(window);
        }
    }

    public abstract void addLightStatusbarChangeToAnimationStart(Animator animator, boolean flag);

    public abstract Animator animateStatusbarColor(int i, int j, int k);

    public abstract int getStatusbarColor();

    public abstract boolean isLightStatusbarEffective();

    public abstract void setLightStatusbar(boolean flag);

    public abstract void setStatusbarColor(int i);

    public final void tryApplyLightStatusbar(boolean flag, int i, int j, int k)
    {
        setLightStatusbar(flag);
        if (!isLightStatusbarEffective())
        {
            i = j;
        }
        if (k == 0)
        {
            setStatusbarColor(i);
            return;
        } else
        {
            animateStatusbarColor(i, k, getStatusbarColor()).start();
            return;
        }
    }
}
