// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.widgets.spinner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.widgets.spinner:
//            LabeledSpinner

final class this._cls0 extends AnimatorListenerAdapter
{

    private final LabeledSpinner this$0;

    public final void onAnimationEnd(Animator animator)
    {
        animator = LabeledSpinner.this;
        if (((LabeledSpinner) (animator)).currentLabel < ((LabeledSpinner) (animator)).labels.size() - 1)
        {
            ((LabeledSpinner) (animator)).fadeOutAnimator.start();
        }
    }

    I()
    {
        this$0 = LabeledSpinner.this;
        super();
    }
}
