// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.recurrence;

import java.util.Comparator;

final class a
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        int i = ((Integer)obj).intValue();
        int j = ((Integer)obj1).intValue();
        if (i < j)
        {
            return -1;
        }
        return i <= j ? 0 : 1;
    }


    private a()
    {
    }
}
