// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import java.util.List;

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = (List)obj;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return ImmutableList.copyOf(new com.google.common.collect.Loader..Lambda._cls2(((Iterable) (obj))));
        }
    }


    private ()
    {
    }
}
