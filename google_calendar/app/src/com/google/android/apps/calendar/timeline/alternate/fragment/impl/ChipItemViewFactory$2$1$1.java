// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            ChipItemViewFactory

final class this._cls2 extends AnimatorListenerAdapter
{

    private final llback this$2;

    public final void onAnimationEnd(Animator animator)
    {
        animator = _fld0;
        if (((ChipItemViewFactory) (animator)).clipCallback != null)
        {
            ((ChipItemViewFactory) (animator)).clipCallback.run();
            animator.clipCallback = null;
        }
    }

    a()
    {
        this$2 = this._cls2.this;
        super();
    }
}
