// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzp, zzb, zzr

public final class zzq
    implements android.os.Parcelable.Creator
{

    public zzq()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        String s = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        com.google.android.gms.reminders.model.zzb zzb1 = null;
        String s1 = null;
        zzp zzp1 = null;
        Integer integer = null;
        Integer integer1 = null;
        String s2 = null;
        Double double1 = null;
        Double double2 = null;
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
                    double2 = zzb.zzo(parcel, k);
                    break;

                case 3: // '\003'
                    double1 = zzb.zzo(parcel, k);
                    break;

                case 4: // '\004'
                    s2 = zzb.zzq(parcel, k);
                    break;

                case 5: // '\005'
                    integer1 = zzb.zzh(parcel, k);
                    break;

                case 6: // '\006'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 7: // '\007'
                    zzp1 = (zzp)zzb.zza(parcel, k, zzp.CREATOR);
                    break;

                case 8: // '\b'
                    s1 = zzb.zzq(parcel, k);
                    break;

                case 9: // '\t'
                    s = zzb.zzq(parcel, k);
                    break;

                case 10: // '\n'
                    zzb1 = (com.google.android.gms.reminders.model.zzb)zzb.zza(parcel, k, zzb.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzr(i, double2, double1, s2, integer1, integer, zzp1, s1, zzb1, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzr[i];
    }
}
