// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net;

import com.google.common.util.concurrent.ListenableFuture;

public interface BaseClient
{

    public abstract void injectResponse(Object obj, Object obj1);

    public abstract ListenableFuture sendRequest(Object obj);

    public abstract void wipeCache();
}
