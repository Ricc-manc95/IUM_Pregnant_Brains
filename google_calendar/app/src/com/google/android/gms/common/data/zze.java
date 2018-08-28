// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.common.data:
//            DataHolder

public final class zze
    implements android.os.Parcelable.Creator
{

    public zze()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        Bundle bundle = null;
        boolean flag = false;
        int k1 = zzb.zzdE(parcel);
        int i = 0;
        CursorWindow acursorwindow[] = null;
        String as[] = null;
        int l = 0;
        do
        {
            if (parcel.dataPosition() < k1)
            {
                int i1 = parcel.readInt();
                switch (i1 & 0xffff)
                {
                default:
                    if ((i1 & 0xffff0000) != 0xffff0000)
                    {
                        i1 = i1 >> 16 & 0xffff;
                    } else
                    {
                        i1 = parcel.readInt();
                    }
                    parcel.setDataPosition(i1 + parcel.dataPosition());
                    break;

                case 1: // '\001'
                    as = zzb.zzC(parcel, i1);
                    break;

                case 2: // '\002'
                    acursorwindow = (CursorWindow[])zzb.zzb(parcel, i1, CursorWindow.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 4: // '\004'
                    bundle = zzb.zzs(parcel, i1);
                    break;

                case 1000: 
                    zzb.zza(parcel, i1, 4);
                    l = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != k1)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k1).toString(), parcel);
                }
                parcel = new DataHolder(l, as, acursorwindow, i, bundle);
                parcel.zzaNV = new Bundle();
                for (int j = 0; j < ((DataHolder) (parcel)).zzaNU.length; j++)
                {
                    ((DataHolder) (parcel)).zzaNV.putInt(((DataHolder) (parcel)).zzaNU[j], j);
                }

                parcel.zzaNY = new int[((DataHolder) (parcel)).zzaNW.length];
                l = 0;
                for (int k = ((flag) ? 1 : 0); k < ((DataHolder) (parcel)).zzaNW.length; k++)
                {
                    ((DataHolder) (parcel)).zzaNY[k] = l;
                    int j1 = ((DataHolder) (parcel)).zzaNW[k].getStartPosition();
                    l += ((DataHolder) (parcel)).zzaNW[k].getNumRows() - (l - j1);
                }

                parcel.zzaNZ = l;
                return parcel;
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DataHolder[i];
    }
}
