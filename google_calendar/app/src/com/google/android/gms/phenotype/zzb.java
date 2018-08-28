// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;

// Referenced classes of package com.google.android.gms.phenotype:
//            Configuration, Configurations

public final class zzb
    implements android.os.Parcelable.Creator
{

    public zzb()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        byte abyte0[] = null;
        int j = com.google.android.gms.common.internal.safeparcel.zzb.zzdE(parcel);
        boolean flag = false;
        Configuration aconfiguration[] = null;
        String s = null;
        String s1 = null;
        int i = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k = parcel.readInt();
                switch (0xffff & k)
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    s1 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    s = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 4: // '\004'
                    aconfiguration = (Configuration[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, k, Configuration.CREATOR);
                    break;

                case 5: // '\005'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 6: // '\006'
                    abyte0 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new Configurations(i, s1, s, aconfiguration, flag, abyte0);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Configurations[i];
    }
}
