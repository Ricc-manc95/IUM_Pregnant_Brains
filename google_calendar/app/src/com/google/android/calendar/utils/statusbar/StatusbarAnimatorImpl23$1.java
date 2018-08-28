// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.statusbar;

import android.animation.Animator;

// Referenced classes of package com.google.android.calendar.utils.statusbar:
//            StatusbarAnimatorCompat, StatusbarAnimatorImpl23

final class val.lightStatusbarEnabled
    implements android.animation.StatusbarAnimatorImpl23._cls1
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
        setLightStatusbar(val$lightStatusbarEnabled);
    }

    ()
    {
        this$0 = final_statusbaranimatorimpl23;
        val$lightStatusbarEnabled = Z.this;
        super();
    }
}
