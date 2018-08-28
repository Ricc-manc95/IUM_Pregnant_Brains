// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.common.api:
//            Status

public final class zzh
    implements android.os.Parcelable.Creator
{

    public zzh()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        PendingIntent pendingintent = null;
        int l = zzb.zzdE(parcel);
        int j = 0;
        int i = 0;
        String s = null;
        do
        {
            if (parcel.dataPosition() < l)
            {
                int k = parcel.readInt();
                switch (k & 0xffff)
                {
                default:
                    if ((k & 0xffff0000) != 0xffff0000)
                    {
                        k = k >> 16 & 0xffff;
                    } else
                    {
                        k = parcel.readInt();
                    }
                    parcel.setDataPosition(k + parcel.dataPosition());
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, k, 4);
                    j = parcel.readInt();
                    break;

                case 2: // '\002'
                    s = zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    pendingintent = (PendingIntent)zzb.zza(parcel, k, PendingIntent.CREATOR);
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new Status(i, j, s, pendingintent);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Status[i];
    }
}
