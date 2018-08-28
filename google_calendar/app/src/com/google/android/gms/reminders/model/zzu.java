// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzv

public final class zzu
    implements android.os.Parcelable.Creator
{

    public zzu()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        Integer integer1 = null;
        int j = zzb.zzdE(parcel);
        java.util.ArrayList arraylist = null;
        int i = 0;
        Integer integer = null;
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
                    arraylist = zzb.zzD(parcel, k);
                    break;

                case 4: // '\004'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 5: // '\005'
                    integer1 = zzb.zzh(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzv(i, arraylist, integer, integer1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzv[i];
    }
}
