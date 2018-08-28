// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import com.google.common.base.Function;

final class arg._cls1
    implements Function
{

    private final long arg$1;

    public final Object apply(Object obj)
    {
        return Long.valueOf(arg$1 + ((Long)obj).longValue());
    }

    (long l)
    {
        arg$1 = l;
    }
}
