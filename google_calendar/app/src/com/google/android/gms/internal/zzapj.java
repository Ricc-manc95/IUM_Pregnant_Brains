// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SubscribeRequest;
import com.google.android.gms.fitness.request.UnsubscribeRequest;

public interface zzapj
    extends IInterface
{

    public abstract void zza(SubscribeRequest subscriberequest)
        throws RemoteException;

    public abstract void zza(UnsubscribeRequest unsubscriberequest)
        throws RemoteException;
}
