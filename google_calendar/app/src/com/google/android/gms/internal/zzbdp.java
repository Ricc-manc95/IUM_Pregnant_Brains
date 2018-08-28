// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdo

public final class zzbdp
    implements android.os.Parcelable.Creator
{

    public zzbdp()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = 0;
        int k = zzb.zzdE(parcel);
        Intent intent = null;
        int i = 0;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int l = parcel.readInt();
                switch (0xffff & l)
                {
                default:
                    zzb.zzb(parcel, l);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 3: // '\003'
                    intent = (Intent)zzb.zza(parcel, l, Intent.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new zzbdo(i, j, intent);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzbdo[i];
    }
}
