// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.drive:
//            DriveId

public final class zze
    implements android.os.Parcelable.Creator
{

    public zze()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        long l1 = 0L;
        int k = zzb.zzdE(parcel);
        int j = 0;
        String s = null;
        int i = -1;
        long l2 = 0L;
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
                    j = parcel.readInt();
                    break;

                case 2: // '\002'
                    s = zzb.zzq(parcel, l);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, 8);
                    l2 = parcel.readLong();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, l, 8);
                    l1 = parcel.readLong();
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != k)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
                }
                return new DriveId(j, s, l2, l1, i);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DriveId[i];
    }
}
