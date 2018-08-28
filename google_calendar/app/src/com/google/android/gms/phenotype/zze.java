// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.phenotype:
//            Flag

public final class zze
    implements android.os.Parcelable.Creator
{

    public zze()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int l = zzb.zzdE(parcel);
        int k = 0;
        String s1 = null;
        long l1 = 0L;
        boolean flag = false;
        double d = 0.0D;
        String s = null;
        byte abyte0[] = null;
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
                    k = parcel.readInt();
                    break;

                case 2: // '\002'
                    s1 = zzb.zzq(parcel, i1);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 8);
                    l1 = parcel.readLong();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, i1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, i1, 8);
                    d = parcel.readDouble();
                    break;

                case 6: // '\006'
                    s = zzb.zzq(parcel, i1);
                    break;

                case 7: // '\007'
                    abyte0 = zzb.zzt(parcel, i1);
                    break;

                case 8: // '\b'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 9: // '\t'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new Flag(k, s1, l1, flag, d, s, abyte0, j, i);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Flag[i];
    }
}
