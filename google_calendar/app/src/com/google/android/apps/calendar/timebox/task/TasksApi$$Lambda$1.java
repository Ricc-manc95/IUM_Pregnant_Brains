// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.common.base.Function;

final class arg._cls2
    implements Function
{

    private final int arg$1;
    private final int arg$2;

    public final Object apply(Object obj)
    {
        int i = arg$1;
        int j = arg$2;
        obj = (Iterable)obj;
        arg._cls2 _lcls2 = new <init>(i, j);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (_lcls2 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.collect..TasksApi..Lambda._cls5(((Iterable) (obj)), _lcls2);
        }
    }

    (int i, int j)
    {
        arg$1 = i;
        arg$2 = j;
    }
}
