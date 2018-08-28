// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

public final class AnimationUtil extends Enum
{

    private static final AnimationUtil $VALUES[] = new AnimationUtil[0];

    public static Animator createMoveAnimation(final View view, final int left, final int top, final int right, final int bottom)
    {
        int i = view.getLeft();
        int j = view.getTop();
        int k = view.getRight();
        int l = view.getBottom();
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        class .Lambda._cls0
            implements android.animation.ValueAnimator.AnimatorUpdateListener
        {

            private final int arg$1;
            private final int arg$2;
            private final int arg$3;
            private final int arg$4;
            private final int arg$5;
            private final int arg$6;
            private final int arg$7;
            private final int arg$8;
            private final View arg$9;

            public final void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                int i2 = arg$1;
                int l2 = arg$2;
                int j2 = arg$3;
                int k2 = arg$4;
                int k1 = arg$5;
                int l1 = arg$6;
                int i1 = arg$7;
                int j1 = arg$8;
                View view1 = arg$9;
                float f = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                float f1 = i2;
                i2 = (int)((float)(l2 - i2) * f + f1);
                j2 = (int)((float)j2 + (float)(k2 - j2) * f);
                k1 = (int)((float)k1 + (float)(l1 - k1) * f);
                f1 = i1;
                i1 = (int)(f * (float)(j1 - i1) + f1);
                view1.setLeft(i2);
                view1.setTop(j2);
                view1.setRight(k1);
                view1.setBottom(i1);
            }

            .Lambda._cls0(int i, int j, int k, int l, int i1, int j1, int k1, 
                    int l1, View view)
            {
                arg$1 = i;
                arg$2 = j;
                arg$3 = k;
                arg$4 = l;
                arg$5 = i1;
                arg$6 = j1;
                arg$7 = k1;
                arg$8 = l1;
                arg$9 = view;
            }
        }

        valueanimator.addUpdateListener(new .Lambda._cls0(i, left, j, top, k, right, l, bottom, view));
        valueanimator.addListener(new _cls1());
        return valueanimator;
    }

    public static AnimationUtil[] values()
    {
        return (AnimationUtil[])$VALUES.clone();
    }


    private class _cls1 extends AnimatorListenerAdapter
    {

        private boolean canceled;
        private final int val$bottom;
        private final int val$left;
        private final int val$right;
        private final int val$top;
        private final View val$view;

        public final void onAnimationCancel(Animator animator)
        {
            canceled = true;
        }

        public final void onAnimationEnd(Animator animator)
        {
            if (!canceled)
            {
                view.setLeft(left);
                view.setTop(top);
                view.setRight(right);
                view.setBottom(bottom);
            }
        }

        _cls1()
        {
            view = view1;
            left = i;
            top = j;
            right = k;
            bottom = l;
            super();
        }
    }

}
