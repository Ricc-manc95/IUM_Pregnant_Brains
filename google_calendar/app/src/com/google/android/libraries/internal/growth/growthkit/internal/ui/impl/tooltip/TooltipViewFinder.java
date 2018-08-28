// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.TargetElementFinder;

public final class TooltipViewFinder
{

    private final TargetElementFinder targetElementFinder;

    public TooltipViewFinder(TargetElementFinder targetelementfinder)
    {
        targetElementFinder = targetelementfinder;
    }

    public final View findView(FragmentActivity fragmentactivity, com.google.identity.growth.proto.Promotion.TooltipUi tooltipui)
    {
        TargetElementFinder targetelementfinder;
        switch (com.google.identity.growth.proto.Promotion.TooltipUi.TargetCase.forNumber(tooltipui.targetCase_).ordinal())
        {
        default:
            return null;

        case 0: // '\0'
            String s = "";
            if (tooltipui.targetCase_ == 1)
            {
                s = (String)tooltipui.target_;
            }
            return TargetElementFinder.findElementById(fragmentactivity, null, s);

        case 1: // '\001'
            String s1 = "";
            if (tooltipui.targetCase_ == 8)
            {
                s1 = (String)tooltipui.target_;
            }
            return TargetElementFinder.findElementByTag(fragmentactivity, null, s1);

        case 2: // '\002'
            targetelementfinder = targetElementFinder;
            break;
        }
        int i;
        if (tooltipui.targetCase_ == 9)
        {
            i = ((Integer)tooltipui.target_).intValue();
        } else
        {
            i = 0;
        }
        return targetelementfinder.findElementByVeId(fragmentactivity, null, i);
    }
}
