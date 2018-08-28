// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.fitness.result:
//            DataReadResult

public final class zzd
    implements android.os.Parcelable.Creator
{

    public zzd()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int i = 0;
        ArrayList arraylist = null;
        int k = zzb.zzdE(parcel);
        ArrayList arraylist2 = new ArrayList();
        ArrayList arraylist3 = new ArrayList();
        ArrayList arraylist1 = null;
        Status status = null;
        int j = 0;
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
                    zzb.zza(parcel, l, arraylist2, getClass().getClassLoader());
                    break;

                case 2: // '\002'
                    status = (Status)zzb.zza(parcel, l, Status.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, arraylist3, getClass().getClassLoader());
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 6: // '\006'
                    arraylist1 = zzb.zzc(parcel, l, DataSource.CREATOR);
                    break;

                case 7: // '\007'
                    arraylist = zzb.zzc(parcel, l, DataType.CREATOR);
                    break;

                case 1000: 
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != k)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
                }
                return new DataReadResult(j, arraylist2, status, arraylist3, i, arraylist1, arraylist);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DataReadResult[i];
    }
}
