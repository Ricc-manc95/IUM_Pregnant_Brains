// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.LocationRequest;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzo, zzc

public final class zzp
    implements android.os.Parcelable.Creator
{

    public zzp()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        String s = null;
        int j = zzb.zzdE(parcel);
        Object obj = zzo.zzbCq;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = true;
        LocationRequest locationrequest = null;
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
                    locationrequest = (LocationRequest)zzb.zza(parcel, k, LocationRequest.CREATOR);
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    break;

                case 5: // '\005'
                    obj = zzb.zzc(parcel, k, zzc.CREATOR);
                    break;

                case 6: // '\006'
                    s = zzb.zzq(parcel, k);
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 8: // '\b'
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
                return new zzo(i, locationrequest, flag2, ((java.util.List) (obj)), s, flag1, flag);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzo[i];
    }
}
