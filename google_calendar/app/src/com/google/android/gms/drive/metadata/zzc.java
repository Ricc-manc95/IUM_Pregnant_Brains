// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.drive.metadata:
//            CustomPropertyKey

public final class zzc
    implements android.os.Parcelable.Creator
{

    public zzc()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = 0;
        int k = zzb.zzdE(parcel);
        String s = null;
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
                    s = zzb.zzq(parcel, l);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != k)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
                }
                return new CustomPropertyKey(i, s, j);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new CustomPropertyKey[i];
    }
}
