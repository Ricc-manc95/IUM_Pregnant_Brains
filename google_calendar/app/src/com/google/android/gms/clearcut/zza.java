// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.clearcut;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.playlog.internal.PlayLoggerContext;

// Referenced classes of package com.google.android.gms.clearcut:
//            LogEventParcelable

public final class zza
    implements android.os.Parcelable.Creator
{

    public zza()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = zzb.zzdE(parcel);
        int i = 0;
        PlayLoggerContext playloggercontext = null;
        byte abyte1[] = null;
        int ai1[] = null;
        String as[] = null;
        int ai[] = null;
        byte abyte0[][] = null;
        boolean flag = true;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int j = parcel.readInt();
                switch (0xffff & j)
                {
                default:
                    if ((0xffff0000 & j) != 0xffff0000)
                    {
                        j = j >> 16 & 0xffff;
                    } else
                    {
                        j = parcel.readInt();
                    }
                    parcel.setDataPosition(j + parcel.dataPosition());
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, j, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    playloggercontext = (PlayLoggerContext)zzb.zza(parcel, j, PlayLoggerContext.CREATOR);
                    break;

                case 3: // '\003'
                    abyte1 = zzb.zzt(parcel, j);
                    break;

                case 4: // '\004'
                    ai1 = zzb.zzw(parcel, j);
                    break;

                case 5: // '\005'
                    as = zzb.zzC(parcel, j);
                    break;

                case 6: // '\006'
                    ai = zzb.zzw(parcel, j);
                    break;

                case 7: // '\007'
                    abyte0 = zzb.zzu(parcel, j);
                    break;

                case 8: // '\b'
                    zzb.zza(parcel, j, 4);
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
                if (parcel.dataPosition() != k)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
                }
                return new LogEventParcelable(i, playloggercontext, abyte1, ai1, as, ai, abyte0, flag);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new LogEventParcelable[i];
    }
}
