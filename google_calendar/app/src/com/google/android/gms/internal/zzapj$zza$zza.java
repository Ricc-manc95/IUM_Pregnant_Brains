// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SubscribeRequest;
import com.google.android.gms.fitness.request.UnsubscribeRequest;

// Referenced classes of package com.google.android.gms.internal:
//            zzapj

final class zzro
    implements zzapj
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(SubscribeRequest subscriberequest)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
        parcel.writeInt(1);
        subscriberequest.writeToParcel(parcel, 0);
        zzro.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        subscriberequest;
        parcel1.recycle();
        parcel.recycle();
        throw subscriberequest;
    }

    public final void zza(UnsubscribeRequest unsubscriberequest)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
        parcel.writeInt(1);
        unsubscriberequest.writeToParcel(parcel, 0);
        zzro.transact(2, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        unsubscriberequest;
        parcel1.recycle();
        parcel.recycle();
        throw unsubscriberequest;
    }

    ribeRequest(IBinder ibinder)
    {
        zzro = ibinder;
    }
}
