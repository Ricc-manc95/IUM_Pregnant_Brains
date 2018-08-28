// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import java.util.regex.Pattern;

public final class ChipConstants
{

    public static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    public static final Interpolator SPRING_INTERPOLATOR = new OvershootInterpolator(3F);

    public static int backgroundImageFadeInDurationMillis(int i)
    {
        return Math.min(i, 500);
    }

    static 
    {
        Pattern.compile("[\t\n],");
    }
}
