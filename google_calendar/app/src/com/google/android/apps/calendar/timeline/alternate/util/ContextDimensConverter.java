// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.util:
//            DimensConverter

public final class ContextDimensConverter
    implements DimensConverter
{

    private final DisplayMetrics displayMetrics;

    public ContextDimensConverter(Context context)
    {
        displayMetrics = context.getResources().getDisplayMetrics();
    }

    public final float dpToPx(float f)
    {
        return TypedValue.applyDimension(1, f, displayMetrics);
    }

    public final int getPixelOffset(float f)
    {
        return (int)dpToPx(f);
    }

    public final int getPixelSize(float f)
    {
        int i = (int)(dpToPx(f) + 0.5F);
        if (i != 0)
        {
            return i;
        }
        if (f == 0.0F)
        {
            return 0;
        }
        return f <= 0.0F ? -1 : 1;
    }

    public final float spToPx(float f)
    {
        return TypedValue.applyDimension(2, f, displayMetrics);
    }
}
