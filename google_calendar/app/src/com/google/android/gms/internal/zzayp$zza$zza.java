// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.people.model.AvatarReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzayp, zzayo, zzayt

final class zzro
    implements zzayp
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final zzs zza(zzayo zzayo1, AvatarReference avatarreference, zzayt zzayt1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
        parcel.writeStrongBinder(zzayo1.asBinder());
        if (avatarreference == null) goto _L2; else goto _L1
_L1:
        parcel.writeInt(1);
        avatarreference.writeToParcel(parcel, 0);
_L3:
        if (zzayt1 == null)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        parcel.writeInt(1);
        zzayt1.writeToParcel(parcel, 0);
_L4:
        zzro.transact(508, parcel, parcel1, 0);
        parcel1.readException();
        zzayo1 = com.google.android.gms.common.internal.riteToParcel(parcel1.readStrongBinder());
        parcel1.recycle();
        parcel.recycle();
        return zzayo1;
_L2:
        parcel.writeInt(0);
          goto _L3
        zzayo1;
        parcel1.recycle();
        parcel.recycle();
        throw zzayo1;
        parcel.writeInt(0);
          goto _L4
    }

    public final zzs zza(zzayo zzayo1, String s, String s1, boolean flag, String s2, String s3, int i, 
            int j, int k, boolean flag1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        boolean flag2;
        flag2 = true;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
        parcel.writeStrongBinder(zzayo1.asBinder());
        parcel.writeString(s);
        parcel.writeString(s1);
        int l;
        if (flag)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        parcel.writeInt(l);
        parcel.writeString(s2);
        parcel.writeString(s3);
        parcel.writeInt(i);
        parcel.writeInt(j);
        parcel.writeInt(k);
        if (flag1)
        {
            i = ((flag2) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzro.transact(507, parcel, parcel1, 0);
        parcel1.readException();
        zzayo1 = com.google.android.gms.common.internal.riteToParcel(parcel1.readStrongBinder());
        parcel1.recycle();
        parcel.recycle();
        return zzayo1;
        zzayo1;
        parcel1.recycle();
        parcel.recycle();
        throw zzayo1;
    }

    public final zzs zzb(zzayo zzayo1, String s, String s1, int i, int j)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
        parcel.writeStrongBinder(zzayo1.asBinder());
        parcel.writeString(s);
        parcel.writeString(s1);
        parcel.writeInt(i);
        parcel.writeInt(j);
        zzro.transact(505, parcel, parcel1, 0);
        parcel1.readException();
        zzayo1 = com.google.android.gms.common.internal.riteToParcel(parcel1.readStrongBinder());
        parcel1.recycle();
        parcel.recycle();
        return zzayo1;
        zzayo1;
        parcel1.recycle();
        parcel.recycle();
        throw zzayo1;
    }

    rence(IBinder ibinder)
    {
        zzro = ibinder;
    }
}
