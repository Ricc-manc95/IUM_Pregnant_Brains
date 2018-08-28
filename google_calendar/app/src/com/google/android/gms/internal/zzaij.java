// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveFileRange;

// Referenced classes of package com.google.android.gms.internal:
//            zzaii

public final class zzaij
    implements android.os.Parcelable.Creator
{

    public zzaij()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        long l1 = 0L;
        int i = 0;
        int k = zzb.zzdE(parcel);
        java.util.ArrayList arraylist = null;
        long l2 = 0L;
        int j = 0;
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
                    zzb.zza(parcel, l, 8);
                    l2 = parcel.readLong();
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, 8);
                    l1 = parcel.readLong();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 5: // '\005'
                    arraylist = zzb.zzc(parcel, l, DriveFileRange.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new zzaii(j, l2, l1, i, arraylist);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzaii[i];
    }
}
