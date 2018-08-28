// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.util.Property;

// Referenced classes of package com.android.datetimepicker.time:
//            RadialSelectorView

final class  extends Property
{

    public final Object get(Object obj)
    {
        return Float.valueOf(((RadialSelectorView)obj).animationRadiusMultiplier);
    }

    public final void set(Object obj, Object obj1)
    {
        ((RadialSelectorView)obj).animationRadiusMultiplier = ((Float)obj1).floatValue();
    }

    (Class class1, String s)
    {
        super(class1, s);
    }
}
