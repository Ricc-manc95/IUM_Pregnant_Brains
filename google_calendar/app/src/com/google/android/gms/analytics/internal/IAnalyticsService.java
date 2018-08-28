// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics.internal;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public interface IAnalyticsService
    extends IInterface
{

    public abstract void clearHits()
        throws RemoteException;

    public abstract void sendHit(Map map, long l, String s, List list)
        throws RemoteException;
}
