// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.common.api:
//            Scope

public final class zzg
    implements android.os.Parcelable.Creator
{

    public zzg()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = zzb.zzdE(parcel);
        int i = 0;
        String s = null;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int j = parcel.readInt();
                switch (j & 0xffff)
                {
                default:
                    if ((j & 0xffff0000) != 0xffff0000)
                    {
                        j = j >> 16 & 0xffff;
                    } else
                    {
                        j = parcel.readInt();
                    }
                    parcel.setDataPosition(j + parcel.dataPosition());
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, j, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    s = zzb.zzq(parcel, j);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new Scope(i, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Scope[i];
    }
}
