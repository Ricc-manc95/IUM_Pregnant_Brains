// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Set;

final class arg._cls2
    implements Runnable
{

    private final arg._cls2 arg$1;
    private final ListenableFuture arg$2;

    public final void run()
    {
        arg._cls2 _lcls2 = arg$1;
        ListenableFuture listenablefuture = arg$2;
        _lcls2._fld2.remove(listenablefuture);
    }

    ( , ListenableFuture listenablefuture)
    {
        arg$1 = ;
        arg$2 = listenablefuture;
    }
}
