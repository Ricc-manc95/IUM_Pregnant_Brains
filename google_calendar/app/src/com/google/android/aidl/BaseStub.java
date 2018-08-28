// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.aidl:
//            TransactionInterceptor

public class BaseStub extends Binder
    implements IInterface
{

    private static TransactionInterceptor globalInterceptor = null;

    public BaseStub(String s)
    {
        attachInterface(this, s);
    }

    public IBinder asBinder()
    {
        return this;
    }

    public boolean dispatchTransaction$514KOOBECHP6UQB45TNN6BQGC5P66PBC7D662RJ4E9NMIP1FDTPIUK31E9HMAR1R94KLK___0(int i, Parcel parcel, Parcel parcel1)
        throws RemoteException
    {
        return false;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        boolean flag;
        if (i > 0xffffff)
        {
            flag = super.onTransact(i, parcel, parcel1, j);
        } else
        {
            parcel.enforceInterface(getInterfaceDescriptor());
            flag = false;
        }
        if (flag)
        {
            return true;
        } else
        {
            return dispatchTransaction$514KOOBECHP6UQB45TNN6BQGC5P66PBC7D662RJ4E9NMIP1FDTPIUK31E9HMAR1R94KLK___0(i, parcel, parcel1);
        }
    }

}
