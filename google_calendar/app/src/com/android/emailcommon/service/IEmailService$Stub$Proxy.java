// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.emailcommon.provider.RecipientAvailability;
import com.google.android.aidl.BaseProxy;
import java.util.List;

// Referenced classes of package com.android.emailcommon.service:
//            IEmailService

public final class  extends BaseProxy
    implements IEmailService
{

    public final int getApiVersion()
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        parcel.writeInterfaceToken(super.mDescriptor);
        parcel = transactAndReadException(13, parcel);
        int i = parcel.readInt();
        parcel.recycle();
        return i;
    }

    public final String getProtocolVersion(String s)
        throws RemoteException
    {
        Object obj = Parcel.obtain();
        ((Parcel) (obj)).writeInterfaceToken(super.mDescriptor);
        ((Parcel) (obj)).writeString(s);
        s = transactAndReadException(18, ((Parcel) (obj)));
        obj = s.readString();
        s.recycle();
        return ((String) (obj));
    }

    public final List retrieveRecipientAvailabilities(String s, List list, long l, long l1)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        parcel.writeInterfaceToken(super.mDescriptor);
        parcel.writeString(s);
        parcel.writeStringList(list);
        parcel.writeLong(l);
        parcel.writeLong(l1);
        s = transactAndReadException(17, parcel);
        list = s.createTypedArrayList(RecipientAvailability.CREATOR);
        s.recycle();
        return list;
    }

    public (IBinder ibinder)
    {
        super(ibinder, "com.android.emailcommon.service.IEmailService");
    }
}
