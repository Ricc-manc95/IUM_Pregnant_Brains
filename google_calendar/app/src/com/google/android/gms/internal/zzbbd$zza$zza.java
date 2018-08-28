// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbd, zzbbc

final class zzro
    implements zzbbd
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(zzbbc zzbbc1)
        throws RemoteException
    {
        IBinder ibinder;
        Parcel parcel;
        ibinder = null;
        parcel = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.pseudonymous.internal.IPseudonymousIdService");
        if (zzbbc1 == null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        ibinder = zzbbc1.asBinder();
        parcel.writeStrongBinder(ibinder);
        zzro.transact(1, parcel, null, 1);
        parcel.recycle();
        return;
        zzbbc1;
        parcel.recycle();
        throw zzbbc1;
    }

    (IBinder ibinder)
    {
        zzro = ibinder;
    }
}
