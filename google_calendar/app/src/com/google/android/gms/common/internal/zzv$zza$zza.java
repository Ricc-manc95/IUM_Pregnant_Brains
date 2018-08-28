// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzv, zzu, zzj

final class zzro
    implements zzv
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(zzu zzu1, zzj zzj1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
        parcel.writeStrongBinder(zzu1.asBinder());
        if (zzj1 == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        parcel.writeInt(1);
        zzj1.writeToParcel(parcel, 0);
_L1:
        zzro.transact(46, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        parcel.writeInt(0);
          goto _L1
        zzu1;
        parcel1.recycle();
        parcel.recycle();
        throw zzu1;
    }

    (IBinder ibinder)
    {
        zzro = ibinder;
    }
}
