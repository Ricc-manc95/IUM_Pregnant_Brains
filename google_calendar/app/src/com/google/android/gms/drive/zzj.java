// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.drive:
//            UserMetadata

public final class zzj
    implements android.os.Parcelable.Creator
{

    public zzj()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        String s = null;
        int j = zzb.zzdE(parcel);
        boolean flag = false;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        int i = 0;
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
                    s3 = zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    s2 = zzb.zzq(parcel, k);
                    break;

                case 4: // '\004'
                    s1 = zzb.zzq(parcel, k);
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 6: // '\006'
                    s = zzb.zzq(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new UserMetadata(i, s3, s2, s1, flag, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new UserMetadata[i];
    }
}
