// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzo, zzq

public final class zzr
    implements android.os.Parcelable.Creator
{

    public zzr()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        android.os.IBinder ibinder = null;
        int k = zzb.zzdE(parcel);
        int j = 0;
        int i = 1;
        android.os.IBinder ibinder1 = null;
        PendingIntent pendingintent = null;
        android.os.IBinder ibinder2 = null;
        zzo zzo1 = null;
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
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzo1 = (zzo)zzb.zza(parcel, l, zzo.CREATOR);
                    break;

                case 3: // '\003'
                    ibinder2 = zzb.zzr(parcel, l);
                    break;

                case 4: // '\004'
                    pendingintent = (PendingIntent)zzb.zza(parcel, l, PendingIntent.CREATOR);
                    break;

                case 5: // '\005'
                    ibinder1 = zzb.zzr(parcel, l);
                    break;

                case 6: // '\006'
                    ibinder = zzb.zzr(parcel, l);
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
                return new zzq(j, i, zzo1, ibinder2, pendingintent, ibinder1, ibinder);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzq[i];
    }
}
