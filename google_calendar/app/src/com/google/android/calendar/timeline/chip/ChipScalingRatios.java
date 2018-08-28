// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.content.res.Resources;
import android.util.TypedValue;
import com.google.common.collect.ImmutableList;

public final class ChipScalingRatios
{

    private final ImmutableList scalingRatios;
    private final ImmutableList scalingThresholds;

    public ChipScalingRatios(Resources resources)
    {
        scalingThresholds = ImmutableList.of(Integer.valueOf(resources.getDimensionPixelSize(0x7f0e01d8)), Integer.valueOf(resources.getDimensionPixelSize(0x7f0e01d9)), Integer.valueOf(resources.getDimensionPixelSize(0x7f0e01da)));
        TypedValue typedvalue = new TypedValue();
        com.google.common.collect.ImmutableList.Builder builder = new com.google.common.collect.ImmutableList.Builder();
        com.google.common.collect.ImmutableList.Builder builder1 = (com.google.common.collect.ImmutableList.Builder)builder.add(Float.valueOf(1.0F));
        resources.getValue(0x7f0e039d, typedvalue, true);
        builder1 = (com.google.common.collect.ImmutableList.Builder)builder.add(Float.valueOf(typedvalue.getFloat()));
        resources.getValue(0x7f0e039e, typedvalue, true);
        resources = (com.google.common.collect.ImmutableList.Builder)builder.add(Float.valueOf(typedvalue.getFloat()));
        builder.forceCopy = true;
        scalingRatios = ImmutableList.asImmutableList(builder.contents, builder.size);
    }

    public final float getScalingRatio(int i, boolean flag)
    {
        int j;
        if (flag)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        for (; j < scalingThresholds.size(); j++)
        {
            if (i >= ((Integer)scalingThresholds.get(j)).intValue())
            {
                return ((Float)scalingRatios.get(j)).floatValue();
            }
        }

        return ((Float)scalingRatios.get(scalingRatios.size() - 1)).floatValue();
    }
}
