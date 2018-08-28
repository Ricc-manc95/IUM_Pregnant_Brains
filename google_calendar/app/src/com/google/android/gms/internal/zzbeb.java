// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.zzaf;

// Referenced classes of package com.google.android.gms.internal:
//            zzbea

public final class zzbeb
    implements android.os.Parcelable.Creator
{

    public zzbeb()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        ConnectionResult connectionresult = null;
        int i = 0;
        zzaf zzaf1 = null;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k = parcel.readInt();
                switch (0xffff & k)
                {
                default:
                    zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    connectionresult = (ConnectionResult)zzb.zza(parcel, k, ConnectionResult.CREATOR);
                    break;

                case 3: // '\003'
                    zzaf1 = (zzaf)zzb.zza(parcel, k, zzaf.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzbea(i, connectionresult, zzaf1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzbea[i];
    }
}
