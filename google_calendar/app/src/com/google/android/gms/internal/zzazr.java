// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.phenotype.Configurations;
import com.google.android.gms.phenotype.DogfoodsToken;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.gms.phenotype.Flag;
import com.google.android.gms.phenotype.FlagOverrides;

public interface zzazr
    extends IInterface
{

    public abstract void zza(Status status, Configurations configurations)
        throws RemoteException;

    public abstract void zza(Status status, DogfoodsToken dogfoodstoken)
        throws RemoteException;

    public abstract void zza(Status status, ExperimentTokens experimenttokens)
        throws RemoteException;

    public abstract void zza(Status status, Flag flag)
        throws RemoteException;

    public abstract void zza(Status status, FlagOverrides flagoverrides)
        throws RemoteException;

    public abstract void zzb(Status status, Configurations configurations)
        throws RemoteException;

    public abstract void zzdd(Status status)
        throws RemoteException;

    public abstract void zzde(Status status)
        throws RemoteException;

    public abstract void zzdf(Status status)
        throws RemoteException;

    public abstract void zzdg(Status status)
        throws RemoteException;

    public abstract void zzdh(Status status)
        throws RemoteException;

    public abstract void zzdi(Status status)
        throws RemoteException;

    public abstract void zzdj(Status status)
        throws RemoteException;
}
