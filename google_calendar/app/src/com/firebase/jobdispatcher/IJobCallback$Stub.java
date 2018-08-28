// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;

// Referenced classes of package com.firebase.jobdispatcher:
//            IJobCallback

public abstract class  extends BaseStub
    implements IJobCallback
{

    protected final boolean dispatchTransaction$514KOOBECHP6UQB45TNN6BQGC5P66PBC7D662RJ4E9NMIP1FDTPIUK31E9HMAR1R94KLK___0(int i, Parcel parcel, Parcel parcel1)
        throws RemoteException
    {
        if (i == 1)
        {
            jobFinished((Bundle)Codecs.createParcelable(parcel, Bundle.CREATOR), parcel.readInt());
            return true;
        } else
        {
            return false;
        }
    }

    public ()
    {
        super("com.firebase.jobdispatcher.IJobCallback");
    }
}
