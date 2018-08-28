// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.feedback.ErrorReport;

// Referenced classes of package com.google.android.gms.internal:
//            zzaod

final class zzro
    implements zzaod
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final boolean zza(ErrorReport errorreport)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        boolean flag;
        flag = true;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.feedback.internal.IFeedbackService");
        if (errorreport == null)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        parcel.writeInt(1);
        errorreport.writeToParcel(parcel, 0);
_L1:
        int i;
        zzro.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        if (i == 0)
        {
            flag = false;
        }
        parcel1.recycle();
        parcel.recycle();
        return flag;
        parcel.writeInt(0);
          goto _L1
        errorreport;
        parcel1.recycle();
        parcel.recycle();
        throw errorreport;
    }

    public final boolean zzb(ErrorReport errorreport)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        boolean flag;
        flag = true;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.feedback.internal.IFeedbackService");
        if (errorreport == null)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        parcel.writeInt(1);
        errorreport.writeToParcel(parcel, 0);
_L1:
        int i;
        zzro.transact(3, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        if (i == 0)
        {
            flag = false;
        }
        parcel1.recycle();
        parcel.recycle();
        return flag;
        parcel.writeInt(0);
          goto _L1
        errorreport;
        parcel1.recycle();
        parcel.recycle();
        throw errorreport;
    }

    (IBinder ibinder)
    {
        zzro = ibinder;
    }
}
