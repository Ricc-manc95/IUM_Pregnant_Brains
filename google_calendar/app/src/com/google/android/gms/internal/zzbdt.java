// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdo, zzbea

public interface zzbdt
    extends IInterface
{

    public abstract void zza(ConnectionResult connectionresult, zzbdo zzbdo)
        throws RemoteException;

    public abstract void zza(Status status, GoogleSignInAccount googlesigninaccount)
        throws RemoteException;

    public abstract void zzb(zzbea zzbea)
        throws RemoteException;

    public abstract void zzej(Status status)
        throws RemoteException;

    public abstract void zzek(Status status)
        throws RemoteException;
}
