// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzah, zzl, zzr, zzt, 
//            zzab, zzn, TaskEntity

public final class zzaf
    implements android.os.Parcelable.Creator
{

    public zzaf()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        int i = 0;
        zzah zzah1 = null;
        Integer integer1 = null;
        String s = null;
        Long long6 = null;
        Long long5 = null;
        Boolean boolean4 = null;
        Boolean boolean3 = null;
        Boolean boolean2 = null;
        Boolean boolean1 = null;
        Long long4 = null;
        zzl zzl2 = null;
        zzl zzl1 = null;
        zzr zzr1 = null;
        zzt zzt1 = null;
        Long long3 = null;
        byte abyte1[] = null;
        zzab zzab1 = null;
        byte abyte0[] = null;
        Integer integer = null;
        zzn zzn1 = null;
        Long long2 = null;
        Long long1 = null;
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
                    zzah1 = (zzah)zzb.zza(parcel, k, zzah.CREATOR);
                    break;

                case 3: // '\003'
                    integer1 = zzb.zzh(parcel, k);
                    break;

                case 4: // '\004'
                    s = zzb.zzq(parcel, k);
                    break;

                case 6: // '\006'
                    zzl2 = (zzl)zzb.zza(parcel, k, zzl.CREATOR);
                    break;

                case 7: // '\007'
                    zzr1 = (zzr)zzb.zza(parcel, k, zzr.CREATOR);
                    break;

                case 8: // '\b'
                    zzl1 = (zzl)zzb.zza(parcel, k, zzl.CREATOR);
                    break;

                case 9: // '\t'
                    boolean4 = zzb.zzd(parcel, k);
                    break;

                case 1001: 
                    long1 = zzb.zzj(parcel, k);
                    break;

                case 11: // '\013'
                    boolean3 = zzb.zzd(parcel, k);
                    break;

                case 12: // '\f'
                    long5 = zzb.zzj(parcel, k);
                    break;

                case 13: // '\r'
                    zzt1 = (zzt)zzb.zza(parcel, k, zzt.CREATOR);
                    break;

                case 15: // '\017'
                    long3 = zzb.zzj(parcel, k);
                    break;

                case 16: // '\020'
                    abyte1 = zzb.zzt(parcel, k);
                    break;

                case 17: // '\021'
                    zzab1 = (zzab)zzb.zza(parcel, k, zzab.CREATOR);
                    break;

                case 18: // '\022'
                    abyte0 = zzb.zzt(parcel, k);
                    break;

                case 19: // '\023'
                    long6 = zzb.zzj(parcel, k);
                    break;

                case 20: // '\024'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 22: // '\026'
                    boolean2 = zzb.zzd(parcel, k);
                    break;

                case 23: // '\027'
                    boolean1 = zzb.zzd(parcel, k);
                    break;

                case 24: // '\030'
                    long4 = zzb.zzj(parcel, k);
                    break;

                case 26: // '\032'
                    zzn1 = (zzn)zzb.zza(parcel, k, zzn.CREATOR);
                    break;

                case 27: // '\033'
                    long2 = zzb.zzj(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new TaskEntity(i, zzah1, integer1, s, long6, long5, boolean4, boolean3, boolean2, boolean1, long4, zzl2, zzl1, zzr1, zzt1, long3, abyte1, zzab1, abyte0, integer, zzn1, long2, long1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new TaskEntity[i];
    }
}
