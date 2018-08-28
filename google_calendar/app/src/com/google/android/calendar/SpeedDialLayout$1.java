// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import java.util.List;

// Referenced classes of package com.google.android.calendar:
//            SpeedDialLayout

final class t> extends AnimatorListenerAdapter
{

    private final SpeedDialLayout this$0;
    private final boolean val$fadeIn;
    private final List val$viewsWithLayer;

    public final void onAnimationEnd(Animator animator)
    {
        if (!val$fadeIn)
        {
            fadeLayer.setVisibility(8);
        }
        animator = SpeedDialLayout.this;
        SpeedDialLayout.setLayerTypeOnAllViewTargets(val$viewsWithLayer, 0);
    }

    public final void onAnimationStart(Animator animator)
    {
        if (val$fadeIn)
        {
            fadeLayer.setVisibility(0);
        }
        animator = SpeedDialLayout.this;
        SpeedDialLayout.setLayerTypeOnAllViewTargets(val$viewsWithLayer, 2);
    }

    ()
    {
        this$0 = final_speeddiallayout;
        val$fadeIn = flag;
        val$viewsWithLayer = List.this;
        super();
    }
}
