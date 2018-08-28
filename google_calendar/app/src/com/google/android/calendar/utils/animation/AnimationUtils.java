// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public final class AnimationUtils
{

    public static void animateThenRun(Animator animator, final Runnable runnable)
    {
        if (animator == null)
        {
            if (runnable != null)
            {
                runnable.run();
            }
            return;
        }
        if (runnable != null)
        {
            animator.addListener(new _cls1());
        }
        animator.start();
    }

    public static transient Animator playTogether(Animator animator, Animator aanimator[])
    {
        Animator animator1;
        if (animator == null)
        {
            animator1 = null;
        } else
        {
            animator1 = animator;
            if (aanimator.length != 0)
            {
                int i;
                for (i = 0; i < aanimator.length && aanimator[i] == null; i++) { }
                animator1 = animator;
                if (i != aanimator.length)
                {
                    AnimatorSet animatorset = new AnimatorSet();
                    animator = animatorset.play(animator);
                    for (; i < aanimator.length; i++)
                    {
                        Animator animator2 = aanimator[i];
                        if (animator2 != null)
                        {
                            animator.with(animator2);
                        }
                    }

                    return animatorset;
                }
            }
        }
        return animator1;
    }

    public static transient List setFadeAnimations(ViewGroup viewgroup, boolean flag, int ai[])
    {
        if (viewgroup != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        return ((List) (obj));
_L2:
        ArrayList arraylist = new ArrayList();
        int j = ai.length;
        int i = 0;
        do
        {
            obj = arraylist;
            if (i >= j)
            {
                continue;
            }
            obj = viewgroup.findViewById(ai[i]);
            if (obj != null)
            {
                arraylist.addAll(setFadeAnimations(flag, new View[] {
                    obj
                }));
            }
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static transient List setFadeAnimations(final boolean fadeIn, View aview[])
    {
        ArrayList arraylist = new ArrayList();
        int j = aview.length;
        int i = 0;
        while (i < j) 
        {
            final View view = aview[i];
            float f;
            float f1;
            ObjectAnimator objectanimator;
            if (fadeIn)
            {
                f = 0.0F;
            } else
            {
                f = 1.0F;
            }
            if (fadeIn)
            {
                f1 = 1.0F;
            } else
            {
                f1 = 0.0F;
            }
            objectanimator = ObjectAnimator.ofFloat(view, "alpha", new float[] {
                f, f1
            }).setDuration(150L);
            objectanimator.addListener(new _cls4());
            objectanimator.addListener(new _cls5());
            arraylist.add(objectanimator);
            i++;
        }
        return arraylist;
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final Runnable val$runnable;

        public final void onAnimationEnd(Animator animator)
        {
            runnable.run();
        }

        _cls1()
        {
            runnable = runnable1;
            super();
        }
    }


    private class _cls4 extends AnimatorListenerAdapter
    {

        private final int val$currentLayerType;
        private final View val$view;

        public final void onAnimationEnd(Animator animator)
        {
            view.setLayerType(currentLayerType, null);
            super.onAnimationEnd(animator);
        }

        public final void onAnimationStart(Animator animator)
        {
            view.setLayerType(2, null);
            super.onAnimationStart(animator);
        }

        public _cls4()
        {
            view = view1;
            currentLayerType = i;
            super();
        }
    }


    private class _cls5 extends AnimatorListenerAdapter
    {

        private final boolean val$fadeIn;
        private final View val$view;

        public final void onAnimationEnd(Animator animator)
        {
            animator = view;
            boolean flag = fadeIn;
            if (animator != null)
            {
                int i;
                if (flag)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                animator.setVisibility(i);
            }
        }

        public final void onAnimationStart(Animator animator)
        {
            animator = view;
            if (animator != null)
            {
                animator.setVisibility(0);
            }
        }

        _cls5()
        {
            view = view1;
            fadeIn = flag;
            super();
        }
    }

}
