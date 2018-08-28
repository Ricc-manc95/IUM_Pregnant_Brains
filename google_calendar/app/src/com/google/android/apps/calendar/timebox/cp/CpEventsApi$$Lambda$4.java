// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;

import com.google.common.base.Function;
import java.util.Iterator;
import java.util.List;

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = ((Iterable)(List)obj).iterator();
        Object obj1 = ((Iterator) (obj)).next();
        if (!((Iterator) (obj)).hasNext())
        {
            return obj1;
        }
        obj1 = (new StringBuilder("expected one element but was: <")).append(obj1);
        for (int i = 0; i < 4 && ((Iterator) (obj)).hasNext(); i++)
        {
            ((StringBuilder) (obj1)).append(", ").append(((Iterator) (obj)).next());
        }

        if (((Iterator) (obj)).hasNext())
        {
            ((StringBuilder) (obj1)).append(", ...");
        }
        ((StringBuilder) (obj1)).append('>');
        throw new IllegalArgumentException(((StringBuilder) (obj1)).toString());
    }


    private ()
    {
    }
}
