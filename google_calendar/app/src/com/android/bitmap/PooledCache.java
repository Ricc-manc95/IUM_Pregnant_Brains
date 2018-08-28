// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;


public interface PooledCache
{

    public abstract Object get(Object obj, boolean flag);

    public abstract void offer(Object obj);

    public abstract Object poll();

    public abstract Object put(Object obj, Object obj1);
}
