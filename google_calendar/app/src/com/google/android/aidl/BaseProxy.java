// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class BaseProxy
    implements IInterface
{

    public final String mDescriptor;
    public final IBinder mRemote;

    public BaseProxy(IBinder ibinder, String s)
    {
        mRemote = ibinder;
        mDescriptor = s;
    }

    public IBinder asBinder()
    {
        return mRemote;
    }

    public final Parcel transactAndReadException(int i, Parcel parcel)
        throws RemoteException
    {
        Parcel parcel1 = Parcel.obtain();
        mRemote.transact(i, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        return parcel1;
        RuntimeException runtimeexception;
        runtimeexception;
        parcel1.recycle();
        throw runtimeexception;
        Exception exception;
        exception;
        parcel.recycle();
        throw exception;
    }
}
