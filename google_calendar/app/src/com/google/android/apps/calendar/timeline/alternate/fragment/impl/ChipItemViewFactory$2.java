// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.SwipeHandler;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            ChipItemViewFactory

final class val.entry
    implements com.google.android.calendar.timeline.chip.te
{

    public final ChipItemViewFactory this$0;
    private final TimeRangeEntry val$entry;
    private final ViewMode val$viewMode;

    public final boolean isSwipeEnabled()
    {
        return !((Boolean)isTalkBackEnabled.get()).booleanValue() && val$viewMode == ViewMode.SCHEDULE;
    }

    public final boolean isSwipePossible()
    {
        return true;
    }

    public final void onSwipeGestureCancel(Chip chip)
    {
        chip = ChipItemViewFactory.this;
        if (((ChipItemViewFactory) (chip)).clipCallback != null)
        {
            ((ChipItemViewFactory) (chip)).clipCallback.run();
            chip.clipCallback = null;
        }
    }

    public final boolean onSwipeGestureComplete(final Chip chip, int i)
    {
        ListenableFuture listenablefuture = swipeHandler.onSwipe(val$entry);
        class _cls1
            implements FutureCallback
        {

            public final ChipItemViewFactory._cls2 this$1;
            private final Chip val$chip;

            public final void onFailure(Throwable throwable)
            {
                throwable = chip;
                throwable = ChipAnimations.createTranslationXSwipeAnimator(throwable, 0.0F, ChipAnimations.calculateTranslationDuration(-throwable.getTranslationX(), null));
                class _cls1 extends AnimatorListenerAdapter
                {

                    private final _cls1 this$2;

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
                            this$2 = _cls1.this;
                            super();
                        }
                }

                throwable.addListener(new _cls1());
                throwable.start();
            }

            public final void onSuccess(Object obj)
            {
                obj = this$0;
                if (((ChipItemViewFactory) (obj)).clipCallback != null)
                {
                    ((ChipItemViewFactory) (obj)).clipCallback.run();
                    obj.clipCallback = null;
                }
                chip.setTranslationX(0.0F);
            }

            _cls1()
            {
                this$1 = ChipItemViewFactory._cls2.this;
                chip = chip1;
                super();
            }
        }

        chip = new _cls1();
        com.google.common.util.concurrent.ecutor ecutor = com.google.common.util.concurrent.ecutor.INSTANCE;
        if (chip == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.er(listenablefuture, chip), ecutor);
            return true;
        }
    }

    public final void onSwipeGestureStart(Chip chip)
    {
        if (clipCallback == null)
        {
            clipCallback = timelineApi.disableChildClipping();
        }
    }

    _cls1()
    {
        this$0 = final_chipitemviewfactory;
        val$viewMode = viewmode;
        val$entry = TimeRangeEntry.this;
        super();
    }
}
