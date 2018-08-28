// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.feedback:
//            FileTeleporter

public final class zzc
    implements android.os.Parcelable.Creator
{

    public zzc()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        String s = null;
        ParcelFileDescriptor parcelfiledescriptor = null;
        int i = 0;
        String s1 = null;
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
                    s = zzb.zzq(parcel, k);
                    break;

                case 4: // '\004'
                    s1 = zzb.zzq(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new FileTeleporter(i, parcelfiledescriptor, s, s1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new FileTeleporter[i];
    }
}
