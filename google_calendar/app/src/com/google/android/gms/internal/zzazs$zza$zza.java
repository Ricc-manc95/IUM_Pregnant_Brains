// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            zzazs, zzazr

final class zzro
    implements zzazs
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zza(zzazr zzazr1, String s, int i, String as[], byte abyte0[])
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.phenotype.internal.IPhenotypeService");
        parcel.writeStrongBinder(zzazr1.asBinder());
        parcel.writeString(s);
        parcel.writeInt(i);
        parcel.writeStringArray(as);
        parcel.writeByteArray(abyte0);
        zzro.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzazr1;
        parcel1.recycle();
        parcel.recycle();
        throw zzazr1;
    }

    public final void zza(zzazr zzazr1, String s, String s1, String s2)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.phenotype.internal.IPhenotypeService");
        parcel.writeStrongBinder(zzazr1.asBinder());
        parcel.writeString(s);
        parcel.writeString(s1);
        parcel.writeString(s2);
        zzro.transact(11, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzazr1;
        parcel1.recycle();
        parcel.recycle();
        throw zzazr1;
    }

    public final void zzb(zzazr zzazr1, String s)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.phenotype.internal.IPhenotypeService");
        parcel.writeStrongBinder(zzazr1.asBinder());
        parcel.writeString(s);
        zzro.transact(5, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzazr1;
        parcel1.recycle();
        parcel.recycle();
        throw zzazr1;
    }

    (IBinder ibinder)
    {
        zzro = ibinder;
    }
}
