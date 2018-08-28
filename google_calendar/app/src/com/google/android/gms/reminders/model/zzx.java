// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzl, zzy

public final class zzx
    implements android.os.Parcelable.Creator
{

    public zzx()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        zzl zzl1 = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        Boolean boolean1 = null;
        Integer integer = null;
        zzl zzl2 = null;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k = parcel.readInt();
                switch (0xffff & k)
                {
                case 3: // '\003'
                default:
                    zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzl2 = (zzl)zzb.zza(parcel, k, zzl.CREATOR);
                    break;

                case 4: // '\004'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 5: // '\005'
                    boolean1 = zzb.zzd(parcel, k);
                    break;

                case 6: // '\006'
                    zzl1 = (zzl)zzb.zza(parcel, k, zzl.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzy(i, zzl2, integer, boolean1, zzl1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzy[i];
    }
}
