// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.collection;

import java.util.Collection;
import java.util.Iterator;

public final class Iterables2
{

    public static boolean isNullOrEmpty(Iterable iterable)
    {
        if (iterable instanceof Collection)
        {
            return ((Collection)iterable).isEmpty();
        }
        return iterable == null || !iterable.iterator().hasNext();
    }
}
