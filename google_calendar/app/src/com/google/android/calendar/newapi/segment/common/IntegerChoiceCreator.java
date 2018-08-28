// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common;


// Referenced classes of package com.google.android.calendar.newapi.segment.common:
//            ChoiceCreator

public abstract class IntegerChoiceCreator extends ChoiceCreator
{

    public IntegerChoiceCreator()
    {
    }

    public int compare(Object obj, Object obj1)
    {
        obj = (Integer)obj;
        obj1 = (Integer)obj1;
        int i = ((Integer) (obj)).intValue();
        int j = ((Integer) (obj1)).intValue();
        if (i < j)
        {
            return -1;
        }
        return i <= j ? 0 : 1;
    }
}
