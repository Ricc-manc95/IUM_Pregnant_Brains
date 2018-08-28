// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzaj, zzl

public final class zzk
    implements android.os.Parcelable.Creator
{

    public zzk()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        Boolean boolean1 = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        Boolean boolean2 = null;
        Long long1 = null;
        Integer integer = null;
        Integer integer1 = null;
        zzaj zzaj1 = null;
        Integer integer2 = null;
        Integer integer3 = null;
        Integer integer4 = null;
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
                    integer4 = zzb.zzh(parcel, k);
                    break;

                case 3: // '\003'
                    integer3 = zzb.zzh(parcel, k);
                    break;

                case 4: // '\004'
                    integer2 = zzb.zzh(parcel, k);
                    break;

                case 5: // '\005'
                    zzaj1 = (zzaj)zzb.zza(parcel, k, zzaj.CREATOR);
                    break;

                case 6: // '\006'
                    integer1 = zzb.zzh(parcel, k);
                    break;

                case 7: // '\007'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 8: // '\b'
                    long1 = zzb.zzj(parcel, k);
                    break;

                case 9: // '\t'
                    boolean2 = zzb.zzd(parcel, k);
                    break;

                case 10: // '\n'
                    boolean1 = zzb.zzd(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzl(i, integer4, integer3, integer2, zzaj1, integer1, integer, long1, boolean2, boolean1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzl[i];
    }
}
