// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzr;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdu, zzbdy, zzbdt

final class zzro
    implements zzbdu
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(zzr zzr1, int i, boolean flag)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        boolean flag1;
        flag1 = false;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
        if (zzr1 == null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        zzr1 = zzr1.asBinder();
_L1:
        parcel.writeStrongBinder(zzr1);
        parcel.writeInt(i);
        i = ((flag1) ? 1 : 0);
        if (flag)
        {
            i = 1;
        }
        parcel.writeInt(i);
        zzro.transact(9, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzr1 = null;
          goto _L1
        zzr1;
        parcel1.recycle();
        parcel.recycle();
        throw zzr1;
    }

    public final void zza(zzbdy zzbdy1, zzbdt zzbdt1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
        parcel.writeInt(1);
        zzbdy1.writeToParcel(parcel, 0);
        if (zzbdt1 == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        zzbdy1 = zzbdt1.asBinder();
_L1:
        parcel.writeStrongBinder(zzbdy1);
        zzro.transact(12, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzbdy1 = null;
          goto _L1
        zzbdy1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbdy1;
    }

    public final void zzvb(int i)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
        parcel.writeInt(i);
        zzro.transact(7, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    (IBinder ibinder)
    {
        zzro = ibinder;
    }
}
