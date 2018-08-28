// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzz, zzab

public final class zzaa
    implements android.os.Parcelable.Creator
{

    public zzaa()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        Boolean boolean1 = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        Boolean boolean2 = null;
        String s = null;
        zzz zzz1 = null;
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
                    zzz1 = (zzz)zzb.zza(parcel, k, zzz.CREATOR);
                    break;

                case 3: // '\003'
                    s = zzb.zzq(parcel, k);
                    break;

                case 4: // '\004'
                    boolean2 = zzb.zzd(parcel, k);
                    break;

                case 5: // '\005'
                    boolean1 = zzb.zzd(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzab(i, zzz1, s, boolean2, boolean1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzab[i];
    }
}
