// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.auth.api.signin:
//            GoogleSignInAccount

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
        String s7 = null;
        String s6 = null;
        String s5 = null;
        String s4 = null;
        Uri uri = null;
        String s3 = null;
        long l = 0L;
        String s2 = null;
        java.util.ArrayList arraylist = null;
        String s1 = null;
        String s = null;
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
                    s7 = zzb.zzq(parcel, j);
                    break;

                case 3: // '\003'
                    s6 = zzb.zzq(parcel, j);
                    break;

                case 4: // '\004'
                    s5 = zzb.zzq(parcel, j);
                    break;

                case 5: // '\005'
                    s4 = zzb.zzq(parcel, j);
                    break;

                case 6: // '\006'
                    uri = (Uri)zzb.zza(parcel, j, Uri.CREATOR);
                    break;

                case 7: // '\007'
                    s3 = zzb.zzq(parcel, j);
                    break;

                case 8: // '\b'
                    zzb.zza(parcel, j, 8);
                    l = parcel.readLong();
                    break;

                case 9: // '\t'
                    s2 = zzb.zzq(parcel, j);
                    break;

                case 10: // '\n'
                    arraylist = zzb.zzc(parcel, j, Scope.CREATOR);
                    break;

                case 11: // '\013'
                    s1 = zzb.zzq(parcel, j);
                    break;

                case 12: // '\f'
                    s = zzb.zzq(parcel, j);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new GoogleSignInAccount(i, s7, s6, s5, s4, uri, s3, l, s2, arraylist, s1, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new GoogleSignInAccount[i];
    }
}
