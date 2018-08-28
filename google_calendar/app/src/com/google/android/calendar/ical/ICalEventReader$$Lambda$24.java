// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.common.base.Predicate;

final class arg._cls1
    implements Predicate
{

    private final Class arg$1;

    public final boolean apply(Object obj)
    {
        return arg$1.isInstance(obj);
    }

    A(Class class1)
    {
        arg$1 = class1;
    }
}
