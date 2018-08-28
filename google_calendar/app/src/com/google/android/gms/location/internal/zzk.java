// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzq

public interface zzk
    extends IInterface
{

    public abstract void zza(zzq zzq)
        throws RemoteException;

    public abstract void zzaK(boolean flag)
        throws RemoteException;

    public abstract Location zzeP(String s)
        throws RemoteException;
}
