// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import com.google.common.util.concurrent.AsyncFunction;
import java.util.concurrent.Callable;

public interface Trace
{

    public abstract void begin();

    public abstract void end();

    public abstract AsyncFunction propagateAsyncFunction(AsyncFunction asyncfunction);

    public abstract Callable propagateCallable(Callable callable);

    public abstract Runnable propagateRunnable(Runnable runnable);
}
