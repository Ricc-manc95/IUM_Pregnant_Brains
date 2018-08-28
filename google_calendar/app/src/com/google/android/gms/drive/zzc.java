// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.drive:
//            DriveId, zzb

public final class zzc
    implements android.os.Parcelable.Creator
{

    public zzc()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        String s = null;
        int l = zzb.zzdE(parcel);
        boolean flag = false;
        DriveId driveid = null;
        int i = 0;
        int j = 0;
        ParcelFileDescriptor parcelfiledescriptor = null;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < l)
            {
                int i1 = parcel.readInt();
                switch (0xffff & i1)
                {
                case 6: // '\006'
                default:
                    zzb.zzb(parcel, i1);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, i1, 4);
                    k = parcel.readInt();
                    break;

                case 2: // '\002'
                    parcelfiledescriptor = (ParcelFileDescriptor)zzb.zza(parcel, i1, ParcelFileDescriptor.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 5: // '\005'
                    driveid = (DriveId)zzb.zza(parcel, i1, DriveId.CREATOR);
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, i1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 8: // '\b'
                    s = zzb.zzq(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new com.google.android.gms.drive.zzb(k, parcelfiledescriptor, j, i, driveid, flag, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new com.google.android.gms.drive.zzb[i];
    }
}
