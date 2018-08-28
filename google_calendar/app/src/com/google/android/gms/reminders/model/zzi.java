// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzaj, zzj

public final class zzi
    implements android.os.Parcelable.Creator
{

    public zzi()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        Integer integer = null;
        zzaj zzaj1 = null;
        int i = 0;
        Boolean boolean1 = null;
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
                    zzaj1 = (zzaj)zzb.zza(parcel, k, zzaj.CREATOR);
                    break;

                case 3: // '\003'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 4: // '\004'
                    boolean1 = zzb.zzd(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzj(i, zzaj1, integer, boolean1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzj[i];
    }
}
