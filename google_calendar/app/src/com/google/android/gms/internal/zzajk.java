// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

// Referenced classes of package com.google.android.gms.internal:
//            zzajj

public final class zzajk
    implements android.os.Parcelable.Creator
{

    public zzajk()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        FilterHolder filterholder = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        DriveId driveid = null;
        String as[] = null;
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
                    as = zzb.zzC(parcel, k);
                    break;

                case 4: // '\004'
                    driveid = (DriveId)zzb.zza(parcel, k, DriveId.CREATOR);
                    break;

                case 5: // '\005'
                    filterholder = (FilterHolder)zzb.zza(parcel, k, FilterHolder.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzajj(i, s, as, driveid, filterholder);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzajj[i];
    }
}
