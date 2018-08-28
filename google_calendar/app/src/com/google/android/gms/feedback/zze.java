// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.feedback:
//            ThemeSettings

public final class zze
    implements android.os.Parcelable.Creator
{

    public zze()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = 0;
        int l = zzb.zzdE(parcel);
        int j = 0;
        int i = 0;
        do
        {
            if (parcel.dataPosition() < l)
            {
                int i1 = parcel.readInt();
                switch (0xffff & i1)
                {
                default:
                    zzb.zzb(parcel, i1);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 4);
                    k = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != l)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
                }
                return new ThemeSettings(i, j, k);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new ThemeSettings[i];
    }
}
