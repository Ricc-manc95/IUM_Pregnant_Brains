// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            DataType, Device, Application, DataSource

public final class zzi
    implements android.os.Parcelable.Creator
{

    public zzi()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int i = 0;
        int ai[] = null;
        int k = zzb.zzdE(parcel);
        String s = null;
        Application application = null;
        Device device = null;
        String s1 = null;
        DataType datatype = null;
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
                    datatype = (DataType)zzb.zza(parcel, l, DataType.CREATOR);
                    break;

                case 2: // '\002'
                    s1 = zzb.zzq(parcel, l);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 4: // '\004'
                    device = (Device)zzb.zza(parcel, l, Device.CREATOR);
                    break;

                case 5: // '\005'
                    application = (Application)zzb.zza(parcel, l, Application.CREATOR);
                    break;

                case 6: // '\006'
                    s = zzb.zzq(parcel, l);
                    break;

                case 1000: 
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 8: // '\b'
                    ai = zzb.zzw(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new DataSource(j, datatype, s1, i, device, application, s, ai);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DataSource[i];
    }
}
