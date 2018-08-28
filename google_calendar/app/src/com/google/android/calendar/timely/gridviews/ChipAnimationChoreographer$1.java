// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            ChipAnimationChoreographer

final class val.animators
    implements android.view.ws.ChipAnimationChoreographer._cls1
{

    private final ChipAnimationChoreographer this$0;
    private final ArrayList val$animators;

    public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        view.removeOnLayoutChangeListener(this);
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        valueanimator.addUpdateListener(new com.google.android.calendar.utils.animation.t>(view, i1, i - i1, j1, j - j1, k1, k - k1, l1, l - l1));
        valueanimator.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
        valueanimator.setDuration(phaseDuration);
        valueanimator.setInterpolator(ChipAnimationChoreographer.INTERPOLATOR);
        view.addOnAttachStateChangeListener(new com.google.android.calendar.utils.animation.NTERPOLATOR(valueanimator));
        val$animators.add(valueanimator);
    }

    ()
    {
        this$0 = final_chipanimationchoreographer;
        val$animators = ArrayList.this;
        super();
    }
}
