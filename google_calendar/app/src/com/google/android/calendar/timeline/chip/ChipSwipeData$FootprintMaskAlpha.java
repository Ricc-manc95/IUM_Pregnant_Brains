// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.util.Property;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip, ChipSwipeData

public final class  extends Property
{

    public static final  PROPERTY = new <init>();

    public final Object get(Object obj)
    {
        return Integer.valueOf(((Chip)obj).swipeData.footprintMaskAlpha);
    }

    public final void set(Object obj, Object obj1)
    {
        obj = (Chip)obj;
        obj1 = (Integer)obj1;
        ((Chip) (obj)).swipeData.footprintMaskAlpha = ((Integer) (obj1)).intValue();
    }


    private ()
    {
        super(java/lang/Integer, "swipeData.footprintMaskAlpha");
    }
}
