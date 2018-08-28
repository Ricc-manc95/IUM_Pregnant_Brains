// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Function;

public final class arg._cls1
    implements Function
{

    private final Consumer arg$1;

    public final Object apply(Object obj)
    {
        arg$1.accept(obj);
        return null;
    }

    public (Consumer consumer)
    {
        arg$1 = consumer;
    }
}
