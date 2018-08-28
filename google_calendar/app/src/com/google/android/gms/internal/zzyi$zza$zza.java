// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            zzyi, zzyh

final class zzro
    implements zzyi
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(zzyh zzyh1, LogEventParcelable logeventparcelable)
        throws RemoteException
    {
        IBinder ibinder;
        Parcel parcel;
        ibinder = null;
        parcel = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
        if (zzyh1 == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        ibinder = zzyh1.asBinder();
        parcel.writeStrongBinder(ibinder);
        if (logeventparcelable == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        parcel.writeInt(1);
        logeventparcelable.writeToParcel(parcel, 0);
_L1:
        zzro.transact(1, parcel, null, 1);
        parcel.recycle();
        return;
        parcel.writeInt(0);
          goto _L1
        zzyh1;
        parcel.recycle();
        throw zzyh1;
    }

    lable(IBinder ibinder)
    {
        zzro = ibinder;
    }
}
