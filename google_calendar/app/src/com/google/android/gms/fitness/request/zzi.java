// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;

// Referenced classes of package com.google.android.gms.fitness.request:
//            DataReadRequest

public final class zzi
    implements android.os.Parcelable.Creator
{

    public zzi()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int l = zzb.zzdE(parcel);
        int k = 0;
        java.util.ArrayList arraylist5 = null;
        java.util.ArrayList arraylist4 = null;
        long l3 = 0L;
        long l2 = 0L;
        java.util.ArrayList arraylist3 = null;
        java.util.ArrayList arraylist2 = null;
        int j = 0;
        long l1 = 0L;
        DataSource datasource = null;
        int i = 0;
        boolean flag1 = false;
        boolean flag = false;
        android.os.IBinder ibinder = null;
        java.util.ArrayList arraylist1 = null;
        java.util.ArrayList arraylist = null;
        do
        {
            if (parcel.dataPosition() < l)
            {
                int i1 = parcel.readInt();
                switch (0xffff & i1)
                {
                default:
                    zzb.zzb(parcel, i1);
                    break;

                case 1: // '\001'
                    arraylist5 = zzb.zzc(parcel, i1, DataType.CREATOR);
                    break;

                case 2: // '\002'
                    arraylist4 = zzb.zzc(parcel, i1, DataSource.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 8);
                    l3 = parcel.readLong();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, i1, 8);
                    l2 = parcel.readLong();
                    break;

                case 5: // '\005'
                    arraylist3 = zzb.zzc(parcel, i1, DataType.CREATOR);
                    break;

                case 6: // '\006'
                    arraylist2 = zzb.zzc(parcel, i1, DataSource.CREATOR);
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 1000: 
                    zzb.zza(parcel, i1, 4);
                    k = parcel.readInt();
                    break;

                case 8: // '\b'
                    zzb.zza(parcel, i1, 8);
                    l1 = parcel.readLong();
                    break;

                case 9: // '\t'
                    datasource = (DataSource)zzb.zza(parcel, i1, DataSource.CREATOR);
                    break;

                case 10: // '\n'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 12: // '\f'
                    zzb.zza(parcel, i1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 13: // '\r'
                    zzb.zza(parcel, i1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 14: // '\016'
                    ibinder = zzb.zzr(parcel, i1);
                    break;

                case 16: // '\020'
                    arraylist1 = zzb.zzc(parcel, i1, Device.CREATOR);
                    break;

                case 17: // '\021'
                    arraylist = zzb.zzD(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new DataReadRequest(k, arraylist5, arraylist4, l3, l2, arraylist3, arraylist2, j, l1, datasource, i, flag1, flag, ibinder, arraylist1, arraylist);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DataReadRequest[i];
    }
}
