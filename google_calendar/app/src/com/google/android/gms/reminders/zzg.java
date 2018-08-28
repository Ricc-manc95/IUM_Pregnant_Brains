// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders:
//            UpdateRecurrenceOptions

public final class zzg
    implements android.os.Parcelable.Creator
{

    public zzg()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = zzb.zzdE(parcel);
        Long long1 = null;
        boolean flag = false;
        int j = 0;
        int i = 0;
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
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 4: // '\004'
                    long1 = zzb.zzj(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new UpdateRecurrenceOptions(i, Integer.valueOf(j), Boolean.valueOf(flag), long1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new UpdateRecurrenceOptions[i];
    }
}
