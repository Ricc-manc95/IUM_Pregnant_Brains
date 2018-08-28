// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.gms.googlehelp.internal.common.TogglingData;

// Referenced classes of package com.google.android.gms.googlehelp:
//            OfflineSuggestion, GoogleHelp

public final class zzb
    implements android.os.Parcelable.Creator
{

    public zzb()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k1 = com.google.android.gms.common.internal.safeparcel.zzb.zzdE(parcel);
        int j1 = 0;
        String s3 = null;
        Account account = null;
        android.os.Bundle bundle1 = null;
        String s2 = null;
        String s1 = null;
        Bitmap bitmap1 = null;
        boolean flag3 = false;
        boolean flag2 = false;
        java.util.ArrayList arraylist2 = null;
        android.os.Bundle bundle = null;
        Bitmap bitmap = null;
        byte abyte0[] = null;
        int i1 = 0;
        int l = 0;
        String s = null;
        Uri uri = null;
        java.util.ArrayList arraylist1 = null;
        int k = 0;
        ThemeSettings themesettings = null;
        java.util.ArrayList arraylist = null;
        boolean flag1 = false;
        ErrorReport errorreport = null;
        TogglingData togglingdata = null;
        int j = 0;
        PendingIntent pendingintent = null;
        int i = 0;
        boolean flag = false;
        do
        {
            if (parcel.dataPosition() < k1)
            {
                int l1 = parcel.readInt();
                switch (0xffff & l1)
                {
                case 8: // '\b'
                case 9: // '\t'
                case 12: // '\f'
                case 13: // '\r'
                case 24: // '\030'
                case 26: // '\032'
                case 27: // '\033'
                case 29: // '\035'
                case 30: // '\036'
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, l1);
                    break;

                case 1: // '\001'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    j1 = parcel.readInt();
                    break;

                case 2: // '\002'
                    s3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, l1);
                    break;

                case 3: // '\003'
                    account = (Account)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, Account.CREATOR);
                    break;

                case 4: // '\004'
                    bundle1 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(parcel, l1);
                    break;

                case 5: // '\005'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    break;

                case 6: // '\006'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    break;

                case 7: // '\007'
                    arraylist2 = com.google.android.gms.common.internal.safeparcel.zzb.zzE(parcel, l1);
                    break;

                case 10: // '\n'
                    bundle = com.google.android.gms.common.internal.safeparcel.zzb.zzs(parcel, l1);
                    break;

                case 11: // '\013'
                    bitmap = (Bitmap)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, Bitmap.CREATOR);
                    break;

                case 14: // '\016'
                    s = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, l1);
                    break;

                case 15: // '\017'
                    uri = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, Uri.CREATOR);
                    break;

                case 16: // '\020'
                    arraylist1 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, l1, OverflowMenuItem.CREATOR);
                    break;

                case 17: // '\021'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    k = parcel.readInt();
                    break;

                case 18: // '\022'
                    arraylist = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, l1, OfflineSuggestion.CREATOR);
                    break;

                case 19: // '\023'
                    abyte0 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(parcel, l1);
                    break;

                case 20: // '\024'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    i1 = parcel.readInt();
                    break;

                case 21: // '\025'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    l = parcel.readInt();
                    break;

                case 22: // '\026'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 23: // '\027'
                    errorreport = (ErrorReport)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, ErrorReport.CREATOR);
                    break;

                case 25: // '\031'
                    themesettings = (ThemeSettings)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, ThemeSettings.CREATOR);
                    break;

                case 28: // '\034'
                    s2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, l1);
                    break;

                case 31: // '\037'
                    togglingdata = (TogglingData)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, TogglingData.CREATOR);
                    break;

                case 32: // ' '
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    j = parcel.readInt();
                    break;

                case 33: // '!'
                    pendingintent = (PendingIntent)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, PendingIntent.CREATOR);
                    break;

                case 34: // '"'
                    s1 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, l1);
                    break;

                case 35: // '#'
                    bitmap1 = (Bitmap)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, Bitmap.CREATOR);
                    break;

                case 36: // '$'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
                    i = parcel.readInt();
                    break;

                case 37: // '%'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l1, 4);
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
                if (parcel.dataPosition() != k1)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k1).toString(), parcel);
                }
                return new GoogleHelp(j1, s3, account, bundle1, s2, s1, bitmap1, flag3, flag2, arraylist2, bundle, bitmap, abyte0, i1, l, s, uri, arraylist1, k, themesettings, arraylist, flag1, errorreport, togglingdata, j, pendingintent, i, flag);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new GoogleHelp[i];
    }
}
