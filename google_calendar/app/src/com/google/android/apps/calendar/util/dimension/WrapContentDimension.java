// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.dimension;

import android.util.DisplayMetrics;

// Referenced classes of package com.google.android.apps.calendar.util.dimension:
//            Dimension

final class WrapContentDimension
    implements Dimension
{

    WrapContentDimension()
    {
    }

    public final int asSize(DisplayMetrics displaymetrics)
    {
        return -2;
    }
}
