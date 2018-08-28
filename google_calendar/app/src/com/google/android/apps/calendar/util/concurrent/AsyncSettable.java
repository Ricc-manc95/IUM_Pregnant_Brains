// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.concurrent.Executor;

public interface AsyncSettable
{

    public abstract void set(Object obj);

    public abstract void set(Object obj, Runnable runnable, Executor executor);
}
