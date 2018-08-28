// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.playlog.internal:
//            PlayLoggerContext

public final class zza
    implements android.os.Parcelable.Creator
{

    public zza()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int i1 = zzb.zzdE(parcel);
        int l = 0;
        String s3 = null;
        int k = 0;
        int j = 0;
        String s2 = null;
        String s1 = null;
        boolean flag1 = true;
        String s = null;
        boolean flag = false;
        int i = 0;
        do
        {
            if (parcel.dataPosition() < i1)
            {
                int j1 = parcel.readInt();
                switch (0xffff & j1)
                {
                default:
                    zzb.zzb(parcel, j1);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, j1, 4);
                    l = parcel.readInt();
                    break;

                case 2: // '\002'
                    s3 = zzb.zzq(parcel, j1);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, j1, 4);
                    k = parcel.readInt();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, j1, 4);
                    j = parcel.readInt();
                    break;

                case 5: // '\005'
                    s2 = zzb.zzq(parcel, j1);
                    break;

                case 6: // '\006'
                    s1 = zzb.zzq(parcel, j1);
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, j1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 8: // '\b'
                    s = zzb.zzq(parcel, j1);
                    break;

                case 9: // '\t'
                    zzb.zza(parcel, j1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 10: // '\n'
                    zzb.zza(parcel, j1, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            if (parcel.dataPosition() != i1)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(i1).toString(), parcel);
            } else
            {
                return new PlayLoggerContext(l, s3, k, j, s2, s1, flag1, s, flag, i);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new PlayLoggerContext[i];
    }
}
