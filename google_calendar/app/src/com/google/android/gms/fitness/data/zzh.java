// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.fitness.data:
//            DataSource, DataType, DataSet

public final class zzh
    implements android.os.Parcelable.Creator
{

    public zzh()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        ArrayList arraylist = null;
        int j = zzb.zzdE(parcel);
        ArrayList arraylist1 = new ArrayList();
        boolean flag = false;
        DataSource datasource = null;
        int i = 0;
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
                    datasource = (DataSource)zzb.zza(parcel, k, DataSource.CREATOR);
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, k, DataType.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, k, arraylist1, getClass().getClassLoader());
                    break;

                case 4: // '\004'
                    arraylist = zzb.zzc(parcel, k, DataSource.CREATOR);
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new DataSet(i, datasource, arraylist1, arraylist, flag);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DataSet[i];
    }
}
