// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.IntentSender;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            zzahu, zzajj, zzagn, zzaho, 
//            zzahv

final class zzro
    implements zzahu
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final IntentSender zza(zzajj zzajj1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
        parcel.writeInt(1);
        zzajj1.writeToParcel(parcel, 0);
        zzro.transact(10, parcel, parcel1, 0);
        parcel1.readException();
        if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
        zzajj1 = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel1);
_L4:
        parcel1.recycle();
        parcel.recycle();
        return zzajj1;
_L2:
        zzajj1 = null;
        if (true) goto _L4; else goto _L3
_L3:
        zzajj1;
        parcel1.recycle();
        parcel.recycle();
        throw zzajj1;
    }

    public final void zza(zzagn zzagn1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
        parcel.writeInt(1);
        zzagn1.writeToParcel(parcel, 0);
        zzro.transact(16, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzagn1;
        parcel1.recycle();
        parcel.recycle();
        throw zzagn1;
    }

    public final void zza(zzaho zzaho1, zzahv zzahv1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
        parcel.writeInt(1);
        zzaho1.writeToParcel(parcel, 0);
        parcel.writeStrongBinder(zzahv1.asBinder());
        zzro.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzaho1;
        parcel1.recycle();
        parcel.recycle();
        throw zzaho1;
    }

    (IBinder ibinder)
    {
        zzro = ibinder;
    }
}
