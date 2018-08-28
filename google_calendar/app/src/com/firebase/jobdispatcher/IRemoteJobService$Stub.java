// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;

// Referenced classes of package com.firebase.jobdispatcher:
//            IRemoteJobService, IJobCallback

public abstract class  extends BaseStub
    implements IRemoteJobService
{

    protected final boolean dispatchTransaction$514KOOBECHP6UQB45TNN6BQGC5P66PBC7D662RJ4E9NMIP1FDTPIUK31E9HMAR1R94KLK___0(int i, Parcel parcel, Parcel parcel1)
        throws RemoteException
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 26
    //                   2 95;
           goto _L1 _L2 _L3
_L1:
        return false;
_L2:
        parcel1 = (Bundle)Codecs.createParcelable(parcel, Bundle.CREATOR);
        parcel = parcel.readStrongBinder();
        if (parcel == null)
        {
            parcel = null;
        } else
        {
            android.os.IInterface iinterface = parcel.queryLocalInterface("com.firebase.jobdispatcher.IJobCallback");
            if (iinterface instanceof IJobCallback)
            {
                parcel = (IJobCallback)iinterface;
            } else
            {
                parcel = new (parcel);
            }
        }
        start(parcel1, parcel);
_L5:
        return true;
_L3:
        stop((Bundle)Codecs.createParcelable(parcel, Bundle.CREATOR), Codecs.createBoolean(parcel));
        if (true) goto _L5; else goto _L4
_L4:
    }

    public ()
    {
        super("com.firebase.jobdispatcher.IRemoteJobService");
    }
}
