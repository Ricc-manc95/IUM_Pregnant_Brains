// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;

public interface zzayo
    extends IInterface
{

    public abstract void zza(int i, Bundle bundle, Bundle bundle1)
        throws RemoteException;

    public abstract void zza(int i, Bundle bundle, ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void zza(int i, Bundle bundle, ParcelFileDescriptor parcelfiledescriptor, Bundle bundle1)
        throws RemoteException;

    public abstract void zza(int i, Bundle bundle, DataHolder dataholder)
        throws RemoteException;

    public abstract void zza(int i, Bundle bundle, DataHolder adataholder[])
        throws RemoteException;
}
