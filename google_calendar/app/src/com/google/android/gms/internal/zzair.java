// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.internal:
//            zzaiq

public final class zzair
    implements android.os.Parcelable.Creator
{

    public zzair()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        boolean flag = false;
        DataHolder dataholder = null;
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
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    dataholder = (DataHolder)zzb.zza(parcel, k, DataHolder.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != j)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
                }
                return new zzaiq(i, dataholder, flag);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzaiq[i];
    }
}
