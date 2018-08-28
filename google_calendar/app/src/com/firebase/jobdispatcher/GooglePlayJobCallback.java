// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobCallback

final class GooglePlayJobCallback
    implements JobCallback
{

    private final IBinder remote;

    public GooglePlayJobCallback(IBinder ibinder)
    {
        remote = ibinder;
    }

    public final void jobFinished(int i)
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.gcm.INetworkTaskCallback");
        parcel.writeInt(i);
        remote.transact(2, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
        return;
        Object obj;
        obj;
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        parcel.recycle();
        parcel1.recycle();
        throw obj;
    }
}
