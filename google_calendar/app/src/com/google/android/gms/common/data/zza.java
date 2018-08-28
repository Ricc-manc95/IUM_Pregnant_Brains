// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.common.data:
//            BitmapTeleporter

public final class zza
    implements android.os.Parcelable.Creator
{

    public zza()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int l = zzb.zzdE(parcel);
        ParcelFileDescriptor parcelfiledescriptor = null;
        int i = 0;
        int j = 0;
        do
        {
            if (parcel.dataPosition() < l)
            {
                int k = parcel.readInt();
                switch (k & 0xffff)
                {
                default:
                    if ((k & 0xffff0000) != 0xffff0000)
                    {
                        k = k >> 16 & 0xffff;
                    } else
                    {
                        k = parcel.readInt();
                    }
                    parcel.setDataPosition(k + parcel.dataPosition());
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    parcelfiledescriptor = (ParcelFileDescriptor)zzb.zza(parcel, k, ParcelFileDescriptor.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, k, 4);
                    j = parcel.readInt();
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new BitmapTeleporter(i, parcelfiledescriptor, j);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new BitmapTeleporter[i];
    }
}
