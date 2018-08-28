// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.collection;

import java.util.Iterator;

public final class arg._cls1
    implements Iterable
{

    private final Iterable arg$1;

    public final Iterator iterator()
    {
        return new arg._cls1(arg$1);
    }

    public (Iterable iterable)
    {
        arg$1 = iterable;
    }
}
