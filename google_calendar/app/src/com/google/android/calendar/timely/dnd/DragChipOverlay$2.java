// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import android.util.Property;
import android.view.View;

final class agChipLayoutParams extends Property
{

    public final Object get(Object obj)
    {
        obj = (agChipLayoutParams)((View)obj).getLayoutParams();
        return Integer.valueOf(((agChipLayoutParams) (obj)).width - ((agChipLayoutParams) (obj)).rightInset);
    }

    public final void set(Object obj, Object obj1)
    {
        obj = (View)obj;
        obj1 = (Integer)obj1;
        agChipLayoutParams agchiplayoutparams = (agChipLayoutParams)((View) (obj)).getLayoutParams();
        agchiplayoutparams.rightInset = agchiplayoutparams.width - ((Integer) (obj1)).intValue();
        ((View) (obj)).setLayoutParams(agchiplayoutparams);
    }

    agChipLayoutParams(Class class1, String s)
    {
        super(class1, s);
    }
}
