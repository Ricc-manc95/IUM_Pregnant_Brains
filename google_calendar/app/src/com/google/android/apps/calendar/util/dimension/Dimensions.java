// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.dimension;


// Referenced classes of package com.google.android.apps.calendar.util.dimension:
//            MatchParentDimension, WrapContentDimension, PixelDimension, DpDimension, 
//            ExactDimension

public final class Dimensions
{

    public static ExactDimension dp(float f)
    {
        return new DpDimension(24F);
    }

    static 
    {
        new MatchParentDimension();
        new WrapContentDimension();
        new PixelDimension(0);
    }
}
