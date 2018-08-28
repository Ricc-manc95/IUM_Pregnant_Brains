// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.dimension;

import android.util.DisplayMetrics;
import android.util.TypedValue;

// Referenced classes of package com.google.android.apps.calendar.util.dimension:
//            ExactDimension

final class DpDimension
    implements ExactDimension
{

    private final float value;

    public DpDimension(float f)
    {
        value = f;
    }

    public final int asSize(DisplayMetrics displaymetrics)
    {
        float f = TypedValue.applyDimension(1, value, displaymetrics);
        if (f > 0.0F)
        {
            return Math.max(1, Math.round(f));
        } else
        {
            return 0;
        }
    }
}
