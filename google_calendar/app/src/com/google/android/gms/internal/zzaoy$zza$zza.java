// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

// Referenced classes of package com.google.android.gms.internal:
//            zzaoy

public final class zzro
    implements zzaoy
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(DataReadResult datareadresult)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IDataReadCallback");
        if (datareadresult == null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        parcel.writeInt(1);
        datareadresult.writeToParcel(parcel, 0);
_L1:
        zzro.transact(1, parcel, null, 1);
        parcel.recycle();
        return;
        parcel.writeInt(0);
          goto _L1
        datareadresult;
        parcel.recycle();
        throw datareadresult;
    }

    public Result(IBinder ibinder)
    {
        zzro = ibinder;
    }
}
