// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.animation.Animator;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipAnimations;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            ChipItemViewFactory

final class val.chip
    implements FutureCallback
{

    public final val.chip this$1;
    private final Chip val$chip;

    public final void onFailure(Throwable throwable)
    {
        throwable = val$chip;
        throwable = ChipAnimations.createTranslationXSwipeAnimator(throwable, 0.0F, ChipAnimations.calculateTranslationDuration(-throwable.getTranslationX(), null));
        class _cls1 extends AnimatorListenerAdapter
        {

            private final ChipItemViewFactory._cls2._cls1 this$2;

            public final void onAnimationEnd(Animator animator)
            {
                animator = this$0;
                if (((ChipItemViewFactory) (animator)).clipCallback != null)
                {
                    ((ChipItemViewFactory) (animator)).clipCallback.run();
                    animator.clipCallback = null;
                }
            }

            _cls1()
            {
                this$2 = ChipItemViewFactory._cls2._cls1.this;
                super();
            }
        }

        throwable.addListener(new _cls1());
        throwable.start();
    }

    public final void onSuccess(Object obj)
    {
        obj = _fld0;
        if (((ChipItemViewFactory) (obj)).clipCallback != null)
        {
            ((ChipItemViewFactory) (obj)).clipCallback.run();
            obj.clipCallback = null;
        }
        val$chip.setTranslationX(0.0F);
    }

    _cls1()
    {
        this$1 = final__pcls1;
        val$chip = Chip.this;
        super();
    }
}
