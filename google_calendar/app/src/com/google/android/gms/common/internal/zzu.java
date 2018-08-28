// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzu
    extends IInterface
{

    public abstract void zza(int i, Bundle bundle)
        throws RemoteException;

    public abstract void zza(int i, IBinder ibinder, Bundle bundle)
        throws RemoteException;
}
