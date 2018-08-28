// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.utils.animation.AnimationUtils;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.android.calendar.utils.recycler.Recycler;

public final class DragChipFactory
{

    public final int chipElevation;
    public final Recycler chipRecycler;
    public final float chipTextIconScale;
    public final ChipViewModel chipViewModel;

    public DragChipFactory(Recycler recycler, ChipViewModel chipviewmodel, int i, float f)
    {
        chipRecycler = recycler;
        chipViewModel = chipviewmodel;
        chipElevation = i;
        chipTextIconScale = f;
    }

    public static Animator createPickUpDropAnimator(Chip chip, Property property, Property property1, int i, int j, int k, int l, float f, 
            float f1)
    {
        ObjectAnimator objectanimator = null;
        ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(chip, View.TRANSLATION_Z, new float[] {
            f, 0.0F
        });
        if (i != j)
        {
            property = ObjectAnimator.ofInt(chip, property, new int[] {
                i, j
            });
        } else
        {
            property = null;
        }
        if (k != l)
        {
            objectanimator = ObjectAnimator.ofInt(chip, property1, new int[] {
                k, l
            });
        }
        chip = AnimationUtils.playTogether(objectanimator1, new Animator[] {
            property, objectanimator
        });
        chip.setDuration(200L);
        chip.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
        return chip;
    }
}
