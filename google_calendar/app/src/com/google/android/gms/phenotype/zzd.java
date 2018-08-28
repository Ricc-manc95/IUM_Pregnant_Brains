// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.phenotype:
//            ExperimentTokens

public final class zzd
    implements android.os.Parcelable.Creator
{

    public zzd()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        byte abyte0[][] = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        int ai[] = null;
        byte abyte1[][] = null;
        byte abyte2[][] = null;
        byte abyte3[][] = null;
        byte abyte4[][] = null;
        byte abyte5[] = null;
        String s = null;
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
                    s = zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    abyte5 = zzb.zzt(parcel, k);
                    break;

                case 4: // '\004'
                    abyte4 = zzb.zzu(parcel, k);
                    break;

                case 5: // '\005'
                    abyte3 = zzb.zzu(parcel, k);
                    break;

                case 6: // '\006'
                    abyte2 = zzb.zzu(parcel, k);
                    break;

                case 7: // '\007'
                    abyte1 = zzb.zzu(parcel, k);
                    break;

                case 8: // '\b'
                    ai = zzb.zzw(parcel, k);
                    break;

                case 9: // '\t'
                    abyte0 = zzb.zzu(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new ExperimentTokens(i, s, abyte5, abyte4, abyte3, abyte2, abyte1, ai, abyte0);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new ExperimentTokens[i];
    }
}
