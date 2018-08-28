// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.internal:
//            zzajc

public final class zzajd
    implements android.os.Parcelable.Creator
{

    public zzajd()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        android.os.IBinder ibinder = null;
        ParcelFileDescriptor parcelfiledescriptor = null;
        int i = 0;
        String s = null;
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
                    parcelfiledescriptor = (ParcelFileDescriptor)zzb.zza(parcel, k, ParcelFileDescriptor.CREATOR);
                    break;

                case 3: // '\003'
                    ibinder = zzb.zzr(parcel, k);
                    break;

                case 4: // '\004'
                    s = zzb.zzq(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzajc(i, parcelfiledescriptor, ibinder, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzajc[i];
    }
}
