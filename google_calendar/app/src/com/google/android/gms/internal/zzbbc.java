// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.pseudonymous.PseudonymousIdToken;

public interface zzbbc
    extends IInterface
{

    public abstract void zza(Status status, PseudonymousIdToken pseudonymousidtoken)
        throws RemoteException;

    public abstract void zzdC(Status status)
        throws RemoteException;
}
