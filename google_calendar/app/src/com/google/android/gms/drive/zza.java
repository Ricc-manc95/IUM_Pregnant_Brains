// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.drive:
//            ChangeSequenceNumber

public final class zza
    implements android.os.Parcelable.Creator
{

    public zza()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        long l = 0L;
        int j = zzb.zzdE(parcel);
        int i = 0;
        long l1 = 0L;
        long l2 = 0L;
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
                    zzb.zza(parcel, k, 8);
                    l2 = parcel.readLong();
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, k, 8);
                    l1 = parcel.readLong();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, k, 8);
                    l = parcel.readLong();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != j)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
                }
                return new ChangeSequenceNumber(i, l2, l1, l);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new ChangeSequenceNumber[i];
    }
}
