// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataReadRequest;

// Referenced classes of package com.google.android.gms.internal:
//            zzaph

final class zzro
    implements zzaph
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(DataReadRequest datareadrequest)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
        parcel.writeInt(1);
        datareadrequest.writeToParcel(parcel, 0);
        zzro.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        datareadrequest;
        parcel1.recycle();
        parcel.recycle();
        throw datareadrequest;
    }

    dRequest(IBinder ibinder)
    {
        zzro = ibinder;
    }
}
