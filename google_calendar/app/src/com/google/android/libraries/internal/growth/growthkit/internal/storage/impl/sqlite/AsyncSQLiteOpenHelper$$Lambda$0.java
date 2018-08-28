// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

final class arg._cls1
    implements AsyncCallable
{

    private final String arg$1;

    public final ListenableFuture call()
    {
        String s = arg$1;
        if (s == null)
        {
            return com.google.common.util.concurrent.fulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.fulFuture(s);
        }
    }

    y(String s)
    {
        arg$1 = s;
    }
}
