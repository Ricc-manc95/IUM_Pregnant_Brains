// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public final class WiggleInterpolator
    implements Interpolator
{

    private static final Interpolator WRAPPED_INTERPOLATOR = new DecelerateInterpolator(0.7F);

    public WiggleInterpolator()
    {
    }

    public final float getInterpolation(float f)
    {
        if (f < 0.7F)
        {
            return 0.0F;
        } else
        {
            f = (f - 0.7F) / 0.3F;
            return (Math.abs(0.5F - Math.abs((WRAPPED_INTERPOLATOR.getInterpolation(f) % 0.5F) * 2.0F - 0.25F)) - 0.25F) * 4F;
        }
    }

}
