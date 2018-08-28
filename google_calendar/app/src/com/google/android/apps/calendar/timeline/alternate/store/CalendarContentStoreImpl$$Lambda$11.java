// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

final class arg._cls1
    implements Runnable
{

    private final SettableFuture arg$1;

    public final void run()
    {
        arg$1.set(new Object());
    }

    (SettableFuture settablefuture)
    {
        arg$1 = settablefuture;
    }
}
