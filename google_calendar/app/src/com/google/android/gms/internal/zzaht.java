// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.Permission;

// Referenced classes of package com.google.android.gms.internal:
//            zzahs

public final class zzaht
    implements android.os.Parcelable.Creator
{

    public zzaht()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = 0;
        int k = zzb.zzdE(parcel);
        java.util.ArrayList arraylist = null;
        int i = 0;
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
                    arraylist = zzb.zzc(parcel, l, Permission.CREATOR);
                    break;

                case 3: // '\003'
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
                return new zzahs(i, arraylist, j);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzahs[i];
    }
}
