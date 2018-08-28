// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.AccountState;

public interface zzbbh
    extends IInterface
{

    public abstract void onReminderCreated(String s, String s1)
        throws RemoteException;

    public abstract void zzTu()
        throws RemoteException;

    public abstract void zza(DataHolder dataholder, Status status)
        throws RemoteException;

    public abstract void zza(AccountState accountstate, Status status)
        throws RemoteException;

    public abstract void zzax(DataHolder dataholder)
        throws RemoteException;

    public abstract void zzay(DataHolder dataholder)
        throws RemoteException;

    public abstract void zzb(boolean flag, Status status)
        throws RemoteException;

    public abstract void zzc(Status status)
        throws RemoteException;
}
