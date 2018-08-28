// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.ValueAnimator;
import android.util.LongSparseArray;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

public final class DragGuideManager
{

    public final LongSparseArray animators = new LongSparseArray();
    private final ObservableReference changeObservable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(this);
    public Long lastTime;

    DragGuideManager()
    {
    }

    public final void update(Long long1)
    {
        Long long2 = lastTime;
        boolean flag;
        if (long1 == long2 || long1 != null && long1.equals(long2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            Long long3 = lastTime;
            lastTime = long1;
            if (long1 != null)
            {
                ValueAnimator valueanimator = (ValueAnimator)animators.get(long1.longValue());
                if (valueanimator != null)
                {
                    valueanimator.reverse();
                } else
                {
                    LongSparseArray longsparsearray = animators;
                    long l = long1.longValue();
                    final long time = long1.longValue();
                    long1 = ValueAnimator.ofFloat(new float[] {
                        0.0F, 1.0F
                    });
                    long1.setDuration(100L);
                    ObservableReference observablereference = changeObservable;
                    observablereference.getClass();
                    class .Lambda._cls0
                        implements android.animation.ValueAnimator.AnimatorUpdateListener
                    {

                        private final ObservableReference arg$1;

                        public final void onAnimationUpdate(ValueAnimator valueanimator1)
                        {
                            arg$1.set(valueanimator1);
                        }

            .Lambda._cls0(ObservableReference observablereference)
            {
                arg$1 = observablereference;
            }
                    }

                    long1.addUpdateListener(new .Lambda._cls0(observablereference));
                    long1.addListener(new _cls1());
                    long1.start();
                    longsparsearray.put(l, long1);
                }
            }
            if (long3 != null)
            {
                ((ValueAnimator)animators.get(long3.longValue())).reverse();
                return;
            }
        }
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final DragGuideManager this$0;
        private final long val$time;

        public final void onAnimationEnd(Animator animator)
        {
            animator = Long.valueOf(time);
            Long long1 = lastTime;
            boolean flag;
            if (animator == long1 || animator != null && animator.equals(long1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                animators.remove(time);
            }
        }

        _cls1()
        {
            this$0 = DragGuideManager.this;
            time = l;
            super();
        }
    }

}
