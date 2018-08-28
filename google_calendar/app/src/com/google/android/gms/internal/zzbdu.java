// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzr;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdy, zzbdt

public interface zzbdu
    extends IInterface
{

    public abstract void zza(zzr zzr, int i, boolean flag)
        throws RemoteException;

    public abstract void zza(zzbdy zzbdy, zzbdt zzbdt)
        throws RemoteException;

    public abstract void zzvb(int i)
        throws RemoteException;
}
