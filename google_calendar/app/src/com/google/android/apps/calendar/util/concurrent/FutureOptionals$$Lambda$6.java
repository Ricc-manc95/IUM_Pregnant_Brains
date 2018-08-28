// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

final class 
    implements AsyncFunction
{

    public static final AsyncFunction $instance = new <init>();

    public final ListenableFuture apply(Object obj)
    {
        return (ListenableFuture)((Optional)obj).transform(.instance).or(.instance);
    }


    private ()
    {
    }
}
