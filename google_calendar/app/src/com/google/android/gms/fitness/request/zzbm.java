// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

// Referenced classes of package com.google.android.gms.fitness.request:
//            UnsubscribeRequest

public final class zzbm
    implements android.os.Parcelable.Creator
{

    public zzbm()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        DataSource datasource = null;
        DataType datatype = null;
        int i = 0;
        android.os.IBinder ibinder = null;
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
                    datatype = (DataType)zzb.zza(parcel, k, DataType.CREATOR);
                    break;

                case 2: // '\002'
                    datasource = (DataSource)zzb.zza(parcel, k, DataSource.CREATOR);
                    break;

                case 3: // '\003'
                    ibinder = zzb.zzr(parcel, k);
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != j)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
                }
                return new UnsubscribeRequest(i, datatype, datasource, ibinder);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new UnsubscribeRequest[i];
    }
}
