// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.common.base.Function;

final class arg._cls1
    implements Function
{

    private final Class arg$1;

    public final Object apply(Object obj)
    {
        return arg$1.cast(obj);
    }

    A(Class class1)
    {
        arg$1 = class1;
    }
}
