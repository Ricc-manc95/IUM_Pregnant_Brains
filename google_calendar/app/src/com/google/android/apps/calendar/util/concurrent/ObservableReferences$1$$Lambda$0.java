// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;

final class arg._cls2
    implements Runnable
{

    private final Consumer arg$1;
    private final Object arg$2;

    public final void run()
    {
        arg$1.accept(arg$2);
    }

    (Consumer consumer, Object obj)
    {
        arg$1 = consumer;
        arg$2 = obj;
    }
}
