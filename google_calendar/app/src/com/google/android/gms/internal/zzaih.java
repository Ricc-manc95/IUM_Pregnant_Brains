// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.internal:
//            zzahi, zzaig

public final class zzaih
    implements android.os.Parcelable.Creator
{

    public zzaih()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        int i = 0;
        zzahi zzahi1 = null;
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
                    zzahi1 = (zzahi)zzb.zza(parcel, k, zzahi.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzaig(i, zzahi1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzaig[i];
    }
}
