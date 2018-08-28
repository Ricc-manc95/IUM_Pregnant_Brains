// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp.internal.common;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.googlehelp.GoogleHelp;

// Referenced classes of package com.google.android.gms.googlehelp.internal.common:
//            zzd, IGoogleHelpCallbacks

final class zzro
    implements zzd
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(GoogleHelp googlehelp, Bitmap bitmap, IGoogleHelpCallbacks igooglehelpcallbacks)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.googlehelp.internal.common.IGoogleHelpService");
        if (googlehelp == null) goto _L2; else goto _L1
_L1:
        parcel.writeInt(1);
        googlehelp.writeToParcel(parcel, 0);
_L3:
        if (bitmap == null)
        {
            break MISSING_BLOCK_LABEL_116;
        }
        parcel.writeInt(1);
        bitmap.writeToParcel(parcel, 0);
_L4:
        parcel.writeStrongBinder(igooglehelpcallbacks.asBinder());
        zzro.transact(2, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
_L2:
        parcel.writeInt(0);
          goto _L3
        googlehelp;
        parcel1.recycle();
        parcel.recycle();
        throw googlehelp;
        parcel.writeInt(0);
          goto _L4
    }

    allbacks(IBinder ibinder)
    {
        zzro = ibinder;
    }
}
