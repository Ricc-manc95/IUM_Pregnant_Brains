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
//            IJobCallback

public final class  extends BaseProxy
    implements IJobCallback
{

    public final void jobFinished(Bundle bundle, int i)
        throws RemoteException
    {
        Parcel parcel;
        parcel = Parcel.obtain();
        parcel.writeInterfaceToken(super.mDescriptor);
        Codecs.writeParcelable(parcel, bundle);
        parcel.writeInt(i);
        super.mRemote.transact(1, parcel, null, 1);
        parcel.recycle();
        return;
        bundle;
        parcel.recycle();
        throw bundle;
    }

    (IBinder ibinder)
    {
        super(ibinder, "com.firebase.jobdispatcher.IJobCallback");
    }
}
