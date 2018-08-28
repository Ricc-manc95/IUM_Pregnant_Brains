// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.pseudonymous.PseudonymousIdToken;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbc

public abstract class attachInterface extends Binder
    implements zzbbc
{

    public IBinder asBinder()
    {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.pseudonymous.internal.IPseudonymousIdCallbacks");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.pseudonymous.internal.IPseudonymousIdCallbacks");
            if (parcel.readInt() != 0)
            {
                parcel1 = (Status)Status.CREATOR.createFromParcel(parcel);
            } else
            {
                parcel1 = null;
            }
            if (parcel.readInt() != 0)
            {
                parcel = (PseudonymousIdToken)PseudonymousIdToken.CREATOR.createFromParcel(parcel);
            } else
            {
                parcel = null;
            }
            zza(parcel1, parcel);
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.google.android.gms.pseudonymous.internal.IPseudonymousIdCallbacks");
            break;
        }
        if (parcel.readInt() != 0)
        {
            parcel = (Status)Status.CREATOR.createFromParcel(parcel);
        } else
        {
            parcel = null;
        }
        zzdC(parcel);
        return true;
    }

    public nymousIdToken()
    {
        attachInterface(this, "com.google.android.gms.pseudonymous.internal.IPseudonymousIdCallbacks");
    }
}
