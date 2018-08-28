// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders:
//            LoadRemindersOptions

public final class zzd
    implements android.os.Parcelable.Creator
{

    public zzd()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int i1 = zzb.zzdE(parcel);
        int l = 0;
        java.util.ArrayList arraylist2 = null;
        java.util.ArrayList arraylist1 = null;
        Long long6 = null;
        Long long5 = null;
        Long long4 = null;
        Long long3 = null;
        boolean flag2 = false;
        int k = 0;
        boolean flag1 = false;
        boolean flag = false;
        int j = 0;
        int i = 0;
        java.util.ArrayList arraylist = null;
        Long long2 = null;
        Long long1 = null;
        do
        {
            if (parcel.dataPosition() < i1)
            {
                int j1 = parcel.readInt();
                switch (0xffff & j1)
                {
                case 2: // '\002'
                default:
                    zzb.zzb(parcel, j1);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, j1, 4);
                    l = parcel.readInt();
                    break;

                case 3: // '\003'
                    arraylist2 = zzb.zzE(parcel, j1);
                    break;

                case 4: // '\004'
                    arraylist1 = zzb.zzD(parcel, j1);
                    break;

                case 5: // '\005'
                    long6 = zzb.zzj(parcel, j1);
                    break;

                case 6: // '\006'
                    long5 = zzb.zzj(parcel, j1);
                    break;

                case 7: // '\007'
                    long4 = zzb.zzj(parcel, j1);
                    break;

                case 8: // '\b'
                    long3 = zzb.zzj(parcel, j1);
                    break;

                case 9: // '\t'
                    zzb.zza(parcel, j1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    break;

                case 10: // '\n'
                    zzb.zza(parcel, j1, 4);
                    k = parcel.readInt();
                    break;

                case 11: // '\013'
                    zzb.zza(parcel, j1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 12: // '\f'
                    zzb.zza(parcel, j1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 13: // '\r'
                    zzb.zza(parcel, j1, 4);
                    j = parcel.readInt();
                    break;

                case 14: // '\016'
                    zzb.zza(parcel, j1, 4);
                    i = parcel.readInt();
                    break;

                case 15: // '\017'
                    arraylist = zzb.zzE(parcel, j1);
                    break;

                case 16: // '\020'
                    long2 = zzb.zzj(parcel, j1);
                    break;

                case 17: // '\021'
                    long1 = zzb.zzj(parcel, j1);
                    break;
                }
            } else
            if (parcel.dataPosition() != i1)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(i1).toString(), parcel);
            } else
            {
                return new LoadRemindersOptions(l, arraylist2, arraylist1, long6, long5, long4, long3, flag2, k, flag1, flag, j, i, arraylist, long2, long1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new LoadRemindersOptions[i];
    }
}
