// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Application, Session

public final class zzx
    implements android.os.Parcelable.Creator
{

    public zzx()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = zzb.zzdE(parcel);
        int j = 0;
        long l2 = 0L;
        long l1 = 0L;
        String s2 = null;
        String s1 = null;
        String s = null;
        int i = 0;
        Application application = null;
        Long long1 = null;
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
                    zzb.zza(parcel, l, 8);
                    l2 = parcel.readLong();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, l, 8);
                    l1 = parcel.readLong();
                    break;

                case 3: // '\003'
                    s2 = zzb.zzq(parcel, l);
                    break;

                case 4: // '\004'
                    s1 = zzb.zzq(parcel, l);
                    break;

                case 5: // '\005'
                    s = zzb.zzq(parcel, l);
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 1000: 
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 8: // '\b'
                    application = (Application)zzb.zza(parcel, l, Application.CREATOR);
                    break;

                case 9: // '\t'
                    long1 = zzb.zzj(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new Session(j, l2, l1, s2, s1, s, i, application, long1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Session[i];
    }
}
