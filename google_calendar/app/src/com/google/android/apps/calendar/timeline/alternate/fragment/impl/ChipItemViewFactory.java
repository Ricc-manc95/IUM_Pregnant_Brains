// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.view.View;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.AlternateTimelineChipViewModelFactory;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ChipClickListener;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.SwipeHandler;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipFactory;
import com.google.android.calendar.timeline.chip.ChipSwipeHelper;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timeline.chip.GhostChipUtils;

public final class ChipItemViewFactory
    implements ItemViewFactory
{

    private final ChipFactory chipFactory;
    public final ChipClickListener clickListener;
    public Runnable clipCallback;
    public final ObservableReference idle;
    public final ObservableReference isTalkBackEnabled;
    private final ScreenType screenType;
    public final SwipeHandler swipeHandler;
    public final TimelineApi timelineApi;
    private final AlternateTimelineChipViewModelFactory viewModelFactory;

    public ChipItemViewFactory(TimelineApi timelineapi, ChipFactory chipfactory, ChipClickListener chipclicklistener, AlternateTimelineChipViewModelFactory alternatetimelinechipviewmodelfactory, ObservableReference observablereference, ObservableReference observablereference1, SwipeHandler swipehandler, 
            ObservableReference observablereference2)
    {
        timelineApi = timelineapi;
        chipFactory = chipfactory;
        clickListener = chipclicklistener;
        viewModelFactory = alternatetimelinechipviewmodelfactory;
        screenType = (ScreenType)observablereference.get();
        isTalkBackEnabled = observablereference1;
        swipeHandler = swipehandler;
        idle = observablereference2;
    }

    public final void bindView$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HL62TJ15TM62RJ75T7M4QJ5CDQ3MIACCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNM2S395TB6IPBN9LNM8P9RB9666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR1E1KIUIBKCLMLCQB5ET362ORKDTP7I923D1KN0L3PE1IJMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NNAT39DGNMCTBECDQ6IRRE5T1MURJJELMMASHR8OKLC___0(View view, Object obj, final int julianDay, final ViewMode viewMode, boolean flag, final int chipType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0, Consumer consumer, 
            float f)
    {
        final TimeRangeEntry entry;
        Chip chip;
        boolean flag1;
        entry = (TimeRangeEntry)obj;
        chip = (Chip)view;
        obj = viewModelFactory.apply(viewMode, entry, julianDay);
        if (flag && (ViewMode.ONE_DAY_GRID.equals(viewMode) || ViewMode.MULTI_DAY_GRID.equals(viewMode)))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && ((ChipViewModel) (obj)).getBorderColor() == 0)
        {
            view = ((ChipViewModel) (obj)).toBuilder().setBorderColor(-1).build();
        } else
        {
            view = ((View) (obj));
            if (!flag1)
            {
                view = ((View) (obj));
                if (((ChipViewModel) (obj)).getBorderColor() == -1)
                {
                    view = ((ChipViewModel) (obj)).toBuilder().setBorderColor(0).build();
                }
            }
        }
        if (chipType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.DRAG_GHOST$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0)
        {
            obj = GhostChipUtils.toGhostChip(view, true);
        } else
        {
            obj = view;
            if (chipType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.CREATE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0)
            {
                obj = GhostChipUtils.toGhostChip(view, false);
            }
        }
        chip.setViewModel(((ChipViewModel) (obj)));
        chip.setTextIconScale(f);
        if (viewMode != ViewMode.MONTH) goto _L2; else goto _L1
_L1:
        if (screenType == ScreenType.PHONE)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1 && !((Boolean)isTalkBackEnabled.get()).booleanValue()) goto _L2; else goto _L3
_L3:
        chip.setActionListener(null);
        view = null;
_L5:
        chip.longPressListener = view;
        chip.updateInteractionListeners();
        view = new _cls2();
        chip.swipeHelper._flddelegate = view;
        chip.configureSwipeState(null);
        return;
_L2:
        chip.setActionListener(new _cls1());
        class .Lambda._cls0
            implements com.google.android.calendar.timeline.chip.Chip.ChipLongPressListener
        {

            private final Consumer arg$1;

            public final boolean onChipLongPress(Chip chip1, Point point)
            {
                arg$1.accept(com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode.MOVE);
                return true;
            }

            .Lambda._cls0(Consumer consumer)
            {
                arg$1 = consumer;
            }
        }

        if (chipType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.REGULAR$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0)
        {
            view = new .Lambda._cls0(consumer);
        } else
        {
            view = null;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final View createView()
    {
        return chipFactory.create();
    }

    public final void onRecycle(View view)
    {
        if (clipCallback != null)
        {
            clipCallback.run();
            clipCallback = null;
        }
        ((Chip)view).clean();
    }

    private class _cls2
        implements com.google.android.calendar.timeline.chip.ChipSwipeHelper.Delegate
    {

        public final ChipItemViewFactory this$0;
        private final TimeRangeEntry val$entry;
        private final ViewMode val$viewMode;

        public final boolean isSwipeEnabled()
        {
            return !((Boolean)isTalkBackEnabled.get()).booleanValue() && viewMode == ViewMode.SCHEDULE;
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
            ListenableFuture listenablefuture = swipeHandler.onSwipe(entry);
            class _cls1
                implements FutureCallback
            {

                public final _cls2 this$1;
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
                            animator = _fld0;
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
                    obj = _fld0;
                    if (((ChipItemViewFactory) (obj)).clipCallback != null)
                    {
                        ((ChipItemViewFactory) (obj)).clipCallback.run();
                        obj.clipCallback = null;
                    }
                    chip.setTranslationX(0.0F);
                }

                _cls1()
                {
                    this$1 = _cls2.this;
                    chip = chip1;
                    super();
                }
            }

            chip = new _cls1();
            com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
            if (chip == null)
            {
                throw new NullPointerException();
            } else
            {
                listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, chip), directexecutor);
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

        _cls2()
        {
            this$0 = ChipItemViewFactory.this;
            viewMode = viewmode;
            entry = timerangeentry;
            super();
        }
    }


    private class _cls1
        implements com.google.android.calendar.timeline.chip.Chip.ChipActionListener
    {

        private final ChipItemViewFactory this$0;
        private final int val$chipType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0;
        private final TimeRangeEntry val$entry;
        private final int val$julianDay;
        private final ViewMode val$viewMode;

        public final void onChipPrimaryAction(Chip chip)
        {
            if (((Boolean)idle.get()).booleanValue())
            {
                clickListener.onClick$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCD5N6ABR3D1KN0BQ3D1KN0EQCD9GNCO9FDHGMSPPF9TH6KPB3EGTKIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TGN0Q9FAPKMATQDDTI6AEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNM2S395T4N8PBDAPKMATQ6C5HN8RRIF4I46Q39E1A7IS357CKLC___0(chip, entry, julianDay, viewMode, chipType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0);
            }
        }

        public final void onChipSecondaryAction(Chip chip)
        {
        }

        _cls1()
        {
            this$0 = ChipItemViewFactory.this;
            entry = timerangeentry;
            julianDay = i;
            viewMode = viewmode;
            chipType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0 = j;
            super();
        }
    }

}
