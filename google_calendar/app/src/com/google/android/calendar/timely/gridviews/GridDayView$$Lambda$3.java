// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.ObjectAnimator;
import android.view.View;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.common.base.Function;

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = (View)(Chip)obj;
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(obj, View.ALPHA, new float[] {
            ((View) (obj)).getAlpha(), 0.0F
        });
        objectanimator.addListener(new com.google.android.calendar.utils.animation.>(((View) (obj))));
        return objectanimator;
    }


    private ()
    {
    }
}
