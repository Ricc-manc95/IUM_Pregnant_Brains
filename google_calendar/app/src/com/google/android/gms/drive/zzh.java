// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.drive:
//            Permission

public final class zzh
    implements android.os.Parcelable.Creator
{

    public zzh()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        String s = null;
        int l = zzb.zzdE(parcel);
        boolean flag = false;
        int i = 0;
        String s1 = null;
        int j = 0;
        String s2 = null;
        int k = 0;
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
                    s2 = zzb.zzq(parcel, i1);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 4: // '\004'
                    s1 = zzb.zzq(parcel, i1);
                    break;

                case 5: // '\005'
                    s = zzb.zzq(parcel, i1);
                    break;

                case 6: // '\006'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, i1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != l)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
                }
                return new Permission(k, s2, j, s1, s, i, flag);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Permission[i];
    }
}
