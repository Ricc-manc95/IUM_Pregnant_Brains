// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.util.Property;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip, ChipSwipeData

final class  extends Property
{

    public static final  PROPERTY = new <init>();

    public final Object get(Object obj)
    {
        return Float.valueOf(((Chip)obj).swipeData.iconScaleAddend);
    }

    public final void set(Object obj, Object obj1)
    {
        obj = (Chip)obj;
        obj1 = (Float)obj1;
        ((Chip) (obj)).swipeData.iconScaleAddend = ((Float) (obj1)).floatValue();
    }


    private ()
    {
        super(java/lang/Float, "swipeData.iconScaleAddend");
    }
}
