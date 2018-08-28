// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.gridviews.geometry.PositionOnGrid;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class ChipAnimationChoreographer
{

    public static final TimeInterpolator INTERPOLATOR = new AccelerateDecelerateInterpolator();
    public final ArrayList animatedChips;
    private final ViewGroup gridDayView;
    public final int phaseDuration;

    private ChipAnimationChoreographer(ViewGroup viewgroup, ArrayList arraylist, int i)
    {
        gridDayView = viewgroup;
        animatedChips = arraylist;
        phaseDuration = i;
    }

    static void animateChips(ViewGroup viewgroup, Chip chip, Function function, Chip chip1, Function function1, ArrayList arraylist, int i)
    {
        ArrayList arraylist1 = new ArrayList(arraylist.size());
        for (i = 0; i < arraylist.size(); i++)
        {
            Object obj = (Chip)arraylist.get(i);
            PositionOnGrid positionongrid2 = (PositionOnGrid)((GridDayView.LayoutParams)((Chip) (obj)).getLayoutParams()).position;
            obj = new AnimatedChip(((Chip) (obj)), new PositionOnGrid(positionongrid2), new PositionOnGrid(positionongrid2));
            ((AnimatedChip) (obj)).nonOverlappingPosition.z = i;
            arraylist1.add(obj);
        }

        class .Lambda._cls3
            implements PositionPairProcessor
        {

            public static final PositionPairProcessor $instance = new .Lambda._cls3();

            public final void process(PositionOnGrid positionongrid3, PositionOnGrid positionongrid4)
            {
                ChipAnimationChoreographer.bridge$lambda$1$ChipAnimationChoreographer(positionongrid3, positionongrid4);
            }


            private .Lambda._cls3()
            {
            }
        }

        processOverlappingPairs(arraylist1, .Lambda._cls3..instance);
        class .Lambda._cls4
            implements PositionPairProcessor
        {

            public static final PositionPairProcessor $instance = new .Lambda._cls4();

            public final void process(PositionOnGrid positionongrid3, PositionOnGrid positionongrid4)
            {
                ChipAnimationChoreographer.bridge$lambda$2$ChipAnimationChoreographer(positionongrid3, positionongrid4);
            }


            private .Lambda._cls4()
            {
            }
        }

        processOverlappingPairs(arraylist1, .Lambda._cls4..instance);
        i = 0;
        do
        {
            if (i >= arraylist1.size())
            {
                break;
            }
            arraylist = ((AnimatedChip)arraylist1.get(i)).nonOverlappingPosition;
            PositionOnGrid positionongrid = ((AnimatedChip)arraylist1.get(i)).finalPosition;
            float f = positionongrid.startFraction;
            float f1 = positionongrid.endFraction;
            int j = 0;
            while (j < arraylist1.size()) 
            {
                PositionOnGrid positionongrid1 = ((AnimatedChip)arraylist1.get(j)).nonOverlappingPosition;
                float f2 = f1;
                float f3 = f;
                if (i != j)
                {
                    boolean flag;
                    if (((PositionOnGrid) (arraylist)).topMinutes < positionongrid1.bottomMinutes && ((PositionOnGrid) (arraylist)).bottomMinutes > positionongrid1.topMinutes)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    f2 = f1;
                    f3 = f;
                    if (flag)
                    {
                        f2 = f1;
                        f3 = f;
                        if (!arraylist.overlaps(positionongrid1))
                        {
                            if (((PositionOnGrid) (arraylist)).startFraction > positionongrid1.startFraction)
                            {
                                f3 = Math.max(f, positionongrid1.endFraction);
                                f2 = f1;
                            } else
                            {
                                f2 = f1;
                                f3 = f;
                                if (((PositionOnGrid) (arraylist)).endFraction < positionongrid1.endFraction)
                                {
                                    f2 = Math.min(f1, positionongrid1.startFraction);
                                    f3 = f;
                                }
                            }
                        }
                    }
                }
                j++;
                f1 = f2;
                f = f3;
            }
            arraylist.startFraction = f;
            arraylist.endFraction = f1;
            i++;
        } while (true);
        arraylist = new ArrayList();
        if (chip != null)
        {
            function = ((Animator)function.apply(chip)).setDuration(137L);
            chip.addOnAttachStateChangeListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls2(function));
            arraylist.add(function);
        }
        if (chip1 != null)
        {
            chip = (Animator)function1.apply(chip1);
            chip.setDuration(275L);
            chip.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            chip1.addOnAttachStateChangeListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls2(chip));
            arraylist.add(chip);
        }
        viewgroup = new ChipAnimationChoreographer(viewgroup, arraylist1, 275);
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                return ChipAnimationChoreographer.lambda$animateToNonOverlapping$0$ChipAnimationChoreographer((AnimatedChip)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        setPositions(((ChipAnimationChoreographer) (viewgroup)).animatedChips, .Lambda._cls0..instance);
        class .Lambda._cls1
            implements Runnable
        {

            private final ChipAnimationChoreographer arg$1;

            public final void run()
            {
                ChipAnimationChoreographer chipanimationchoreographer = arg$1;
                class .Lambda._cls2
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls2();

                    public final Object apply(Object obj1)
                    {
                        return ChipAnimationChoreographer.lambda$animateToOverlapping$1$ChipAnimationChoreographer((AnimatedChip)obj1);
                    }


                        private .Lambda._cls2()
                        {
                        }
                }

                ChipAnimationChoreographer.setPositions(chipanimationchoreographer.animatedChips, .Lambda._cls2..instance);
                chipanimationchoreographer.animateAllChipsThenRun(Collections.EMPTY_LIST, null);
            }

            .Lambda._cls1()
            {
                arg$1 = ChipAnimationChoreographer.this;
            }
        }

        viewgroup.animateAllChipsThenRun(arraylist, viewgroup. new .Lambda._cls1());
    }

    static final void bridge$lambda$1$ChipAnimationChoreographer(PositionOnGrid positionongrid, PositionOnGrid positionongrid1)
    {
        float f = positionongrid1.endFraction;
        float f1 = positionongrid.startFraction;
        f = Math.max(positionongrid1.startFraction, 0.65F * (f - f1) + positionongrid.startFraction);
        positionongrid.endFraction = Math.min(positionongrid.endFraction, f);
    }

    static final void bridge$lambda$2$ChipAnimationChoreographer(PositionOnGrid positionongrid, PositionOnGrid positionongrid1)
    {
        positionongrid1.startFraction = Math.max(positionongrid1.startFraction, positionongrid.endFraction);
    }

    static final PositionOnGrid lambda$animateToNonOverlapping$0$ChipAnimationChoreographer(AnimatedChip animatedchip)
    {
        return animatedchip.nonOverlappingPosition;
    }

    static final PositionOnGrid lambda$animateToOverlapping$1$ChipAnimationChoreographer(AnimatedChip animatedchip)
    {
        return animatedchip.finalPosition;
    }

    private static void processOverlappingPairs(ArrayList arraylist, PositionPairProcessor positionpairprocessor)
    {
        for (int i = 0; i < arraylist.size(); i++)
        {
            PositionOnGrid positionongrid = ((AnimatedChip)arraylist.get(i)).nonOverlappingPosition;
            PositionOnGrid positionongrid1 = ((AnimatedChip)arraylist.get(i)).finalPosition;
            int j = 0;
            while (j < arraylist.size()) 
            {
                PositionOnGrid positionongrid2 = ((AnimatedChip)arraylist.get(j)).nonOverlappingPosition;
                PositionOnGrid positionongrid3 = ((AnimatedChip)arraylist.get(j)).finalPosition;
                if (i == j || !positionongrid.overlaps(positionongrid2) || positionongrid.startFraction >= positionongrid2.startFraction)
                {
                    continue;
                }
                boolean flag;
                boolean flag1;
                if (positionongrid.z > positionongrid2.z)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (positionongrid1.z > positionongrid3.z)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag != flag1)
                {
                    positionpairprocessor.process(positionongrid, positionongrid2);
                }
                j++;
            }
        }

    }

    static void setPositions(Iterable iterable, Function function)
    {
        for (iterable = iterable.iterator(); iterable.hasNext();)
        {
            Object obj = (AnimatedChip)iterable.next();
            PositionOnGrid positionongrid = (PositionOnGrid)((GridDayView.LayoutParams)((AnimatedChip) (obj)).chip.getLayoutParams()).position;
            obj = (PositionOnGrid)function.apply(obj);
            positionongrid.topMinutes = ((PositionOnGrid) (obj)).topMinutes;
            positionongrid.bottomMinutes = ((PositionOnGrid) (obj)).bottomMinutes;
            positionongrid.startFraction = ((PositionOnGrid) (obj)).startFraction;
            positionongrid.endFraction = ((PositionOnGrid) (obj)).endFraction;
            positionongrid.z = ((PositionOnGrid) (obj)).z;
        }

    }

    final void animateAllChipsThenRun(final List animators, final Runnable runnable)
    {
        gridDayView.requestLayout();
        animators = new ArrayList(animators);
        _cls1 _lcls1 = new _cls1();
        ArrayList arraylist = (ArrayList)animatedChips;
        int j = arraylist.size();
        for (int i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            ((AnimatedChip)obj).chip.addOnLayoutChangeListener(_lcls1);
        }

        gridDayView.addOnLayoutChangeListener(new _cls2());
    }


    private class AnimatedChip
    {

        public final Chip chip;
        public final PositionOnGrid finalPosition;
        public final PositionOnGrid nonOverlappingPosition;

        AnimatedChip(Chip chip1, PositionOnGrid positionongrid, PositionOnGrid positionongrid1)
        {
            chip = chip1;
            nonOverlappingPosition = positionongrid;
            finalPosition = positionongrid1;
        }
    }


    private class PositionPairProcessor
    {

        public abstract void process(PositionOnGrid positionongrid, PositionOnGrid positionongrid1);
    }


    private class _cls1
        implements android.view.View.OnLayoutChangeListener
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
            valueanimator.addUpdateListener(new com.google.android.calendar.utils.animation.AnimationUtils..Lambda._cls0(view, i1, i - i1, j1, j - j1, k1, k - k1, l1, l - l1));
            valueanimator.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            valueanimator.setDuration(phaseDuration);
            valueanimator.setInterpolator(ChipAnimationChoreographer.INTERPOLATOR);
            view.addOnAttachStateChangeListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls2(valueanimator));
            animators.add(valueanimator);
        }

        _cls1()
        {
            this$0 = ChipAnimationChoreographer.this;
            animators = arraylist;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnLayoutChangeListener
    {

        private final ChipAnimationChoreographer this$0;
        private final ArrayList val$animators;
        private final Runnable val$runnable;

        public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            view.removeOnLayoutChangeListener(this);
            view = new AnimatorSet();
            view.playTogether(animators);
            view.addListener(new com.google.android.calendar.timely.interaction.InteractionTracker._cls1(InteractionTracker.getInstance(), animatedChips, ChipAnimationChoreographer.this));
            AnimationUtils.animateThenRun(view, runnable);
        }

        _cls2()
        {
            this$0 = ChipAnimationChoreographer.this;
            animators = arraylist;
            runnable = runnable1;
            super();
        }
    }

}
