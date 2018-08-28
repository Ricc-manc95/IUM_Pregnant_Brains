// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;

// Referenced classes of package com.google.android.calendar:
//            ICalendarOobeService

public abstract class  extends BaseStub
    implements ICalendarOobeService
{

    protected final boolean dispatchTransaction$514KOOBECHP6UQB45TNN6BQGC5P66PBC7D662RJ4E9NMIP1FDTPIUK31E9HMAR1R94KLK___0(int i, Parcel parcel, Parcel parcel1)
        throws RemoteException
    {
        if (i == 1)
        {
            boolean flag = isOobeCompleted();
            parcel1.writeNoException();
            Codecs.writeBoolean(parcel1, flag);
            return true;
        } else
        {
            return false;
        }
    }

    public ()
    {
        super("com.google.android.calendar.ICalendarOobeService");
    }
}
