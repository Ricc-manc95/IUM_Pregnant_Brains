// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import java.util.List;

public final class AnimatorSetCompat
{

    public static void playTogether(AnimatorSet animatorset, List list)
    {
        int j = list.size();
        long l = 0L;
        for (int i = 0; i < j; i++)
        {
            Animator animator = (Animator)list.get(i);
            l = Math.max(l, animator.getStartDelay() + animator.getDuration());
        }

        ValueAnimator valueanimator = ValueAnimator.ofInt(new int[] {
            0, 0
        });
        valueanimator.setDuration(l);
        list.add(0, valueanimator);
        animatorset.playTogether(list);
    }
}
