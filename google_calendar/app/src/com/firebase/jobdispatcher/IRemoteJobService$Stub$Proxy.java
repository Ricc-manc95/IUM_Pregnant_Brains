// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;

// Referenced classes of package com.firebase.jobdispatcher:
//            IRemoteJobService, IJobCallback

public final class  extends BaseProxy
    implements IRemoteJobService
{

    public final void start(Bundle bundle, IJobCallback ijobcallback)
        throws RemoteException
    {
        Parcel parcel;
        parcel = Parcel.obtain();
        parcel.writeInterfaceToken(super.mDescriptor);
        Codecs.writeParcelable(parcel, bundle);
        Codecs.writeStrongBinder(parcel, ijobcallback);
        super.mRemote.transact(1, parcel, null, 1);
        parcel.recycle();
        return;
        bundle;
        parcel.recycle();
        throw bundle;
    }

    public final void stop(Bundle bundle, boolean flag)
        throws RemoteException
    {
        Parcel parcel;
        parcel = Parcel.obtain();
        parcel.writeInterfaceToken(super.mDescriptor);
        Codecs.writeParcelable(parcel, bundle);
        Codecs.writeBoolean(parcel, flag);
        super.mRemote.transact(2, parcel, null, 1);
        parcel.recycle();
        return;
        bundle;
        parcel.recycle();
        throw bundle;
    }

    (IBinder ibinder)
    {
        super(ibinder, "com.firebase.jobdispatcher.IRemoteJobService");
    }
}
