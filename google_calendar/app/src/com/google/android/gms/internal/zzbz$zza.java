// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            zzbz

public abstract class zza extends Binder
    implements zzbz
{

    public static zzbz zza(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        class zza
            implements zzbz
        {

            private IBinder zzro;

            public final IBinder asBinder()
            {
                return zzro;
            }

            public final Bundle zza(Account account, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                if (account == null) goto _L2; else goto _L1
_L1:
                parcel.writeInt(1);
                account.writeToParcel(parcel, 0);
_L5:
                parcel.writeString(s);
                if (bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                zzro.transact(5, parcel, parcel1, 0);
                parcel1.readException();
                if (parcel1.readInt() == 0)
                {
                    break MISSING_BLOCK_LABEL_143;
                }
                account = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L7:
                parcel1.recycle();
                parcel.recycle();
                return account;
_L2:
                parcel.writeInt(0);
                  goto _L5
                account;
                parcel1.recycle();
                parcel.recycle();
                throw account;
_L4:
                parcel.writeInt(0);
                  goto _L6
                account = null;
                  goto _L7
            }

            public final Bundle zza(String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                parcel.writeString(s);
                if (bundle == null) goto _L2; else goto _L1
_L1:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L3:
                zzro.transact(2, parcel, parcel1, 0);
                parcel1.readException();
                if (parcel1.readInt() == 0)
                {
                    break MISSING_BLOCK_LABEL_108;
                }
                s = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
                parcel1.recycle();
                parcel.recycle();
                return s;
_L2:
                parcel.writeInt(0);
                  goto _L3
                s;
                parcel1.recycle();
                parcel.recycle();
                throw s;
                s = null;
                  goto _L4
            }

            zza(IBinder ibinder)
            {
                zzro = ibinder;
            }
        }

        if (iinterface != null && (iinterface instanceof zzbz))
        {
            return (zzbz)iinterface;
        } else
        {
            return new zza(ibinder);
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        throw new NoSuchMethodError();
    }
}
