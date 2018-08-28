// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.phenotype:
//            Flag, Configuration

public final class zza
    implements android.os.Parcelable.Creator
{

    public zza()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = zzb.zzdE(parcel);
        Flag aflag[] = null;
        int j = 0;
        int i = 0;
        String as[] = null;
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
                    aflag = (Flag[])zzb.zzb(parcel, l, Flag.CREATOR);
                    break;

                case 4: // '\004'
                    as = zzb.zzC(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new Configuration(i, j, aflag, as);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Configuration[i];
    }
}
