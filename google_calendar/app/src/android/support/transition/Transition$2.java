// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            Transition

final class r extends AnimatorListenerAdapter
{

    private final Transition this$0;
    private final ArrayMap val$runningAnimators;

    public final void onAnimationEnd(Animator animator)
    {
        val$runningAnimators.remove(animator);
        mCurrentAnimators.remove(animator);
    }

    public final void onAnimationStart(Animator animator)
    {
        mCurrentAnimators.add(animator);
    }

    r()
    {
        this$0 = final_transition;
        val$runningAnimators = ArrayMap.this;
        super();
    }
}
