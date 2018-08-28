// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzw

public final class zzro
    implements zzw
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final boolean zzd(String s, zzd zzd1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        boolean flag;
        flag = false;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        parcel.writeString(s);
        if (zzd1 == null)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        s = zzd1.asBinder();
_L1:
        int i;
        parcel.writeStrongBinder(s);
        zzro.transact(3, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        if (i != 0)
        {
            flag = true;
        }
        parcel1.recycle();
        parcel.recycle();
        return flag;
        s = null;
          goto _L1
        s;
        parcel1.recycle();
        parcel.recycle();
        throw s;
    }

    public final boolean zze(String s, zzd zzd1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        boolean flag;
        flag = false;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        parcel.writeString(s);
        if (zzd1 == null)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        s = zzd1.asBinder();
_L1:
        int i;
        parcel.writeStrongBinder(s);
        zzro.transact(4, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        if (i != 0)
        {
            flag = true;
        }
        parcel1.recycle();
        parcel.recycle();
        return flag;
        s = null;
          goto _L1
        s;
        parcel1.recycle();
        parcel.recycle();
        throw s;
    }

    public (IBinder ibinder)
    {
        zzro = ibinder;
    }
}
