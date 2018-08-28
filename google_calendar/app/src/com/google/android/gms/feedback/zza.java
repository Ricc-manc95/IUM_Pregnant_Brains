// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.RectF;
import android.os.Parcel;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.feedback:
//            FileTeleporter, ThemeSettings, LogOptions, ErrorReport

public final class zza
    implements android.os.Parcelable.Creator
{

    public zza()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k2 = zzb.zzdE(parcel);
        int j2 = 0;
        ApplicationErrorReport applicationerrorreport = null;
        String s29 = null;
        int i2 = 0;
        String s28 = null;
        String s27 = null;
        String s26 = null;
        String s25 = null;
        String s24 = null;
        String s23 = null;
        String s22 = null;
        int l1 = 0;
        String s21 = null;
        String s20 = null;
        String s19 = null;
        String s18 = null;
        String s17 = null;
        String as3[] = null;
        String as2[] = null;
        String as1[] = null;
        String s16 = null;
        String s15 = null;
        byte abyte0[] = null;
        int k1 = 0;
        int j1 = 0;
        int i1 = 0;
        int l = 0;
        String s14 = null;
        String s13 = null;
        String s12 = null;
        android.os.Bundle bundle1 = null;
        boolean flag3 = false;
        int k = 0;
        int j = 0;
        boolean flag2 = false;
        String s11 = null;
        String s10 = null;
        int i = 0;
        String s9 = null;
        String s8 = null;
        String s7 = null;
        String s6 = null;
        String s5 = null;
        String s4 = null;
        String s3 = null;
        BitmapTeleporter bitmapteleporter = null;
        String s2 = null;
        FileTeleporter afileteleporter[] = null;
        String as[] = null;
        boolean flag1 = false;
        String s1 = null;
        ThemeSettings themesettings = null;
        LogOptions logoptions = null;
        String s = null;
        boolean flag = false;
        android.os.Bundle bundle = null;
        java.util.ArrayList arraylist = null;
        do
        {
            if (parcel.dataPosition() < k2)
            {
                int l2 = parcel.readInt();
                switch (0xffff & l2)
                {
                default:
                    zzb.zzb(parcel, l2);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, l2, 4);
                    j2 = parcel.readInt();
                    break;

                case 2: // '\002'
                    applicationerrorreport = (ApplicationErrorReport)zzb.zza(parcel, l2, ApplicationErrorReport.CREATOR);
                    break;

                case 3: // '\003'
                    s29 = zzb.zzq(parcel, l2);
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, l2, 4);
                    i2 = parcel.readInt();
                    break;

                case 5: // '\005'
                    s28 = zzb.zzq(parcel, l2);
                    break;

                case 6: // '\006'
                    s27 = zzb.zzq(parcel, l2);
                    break;

                case 7: // '\007'
                    s26 = zzb.zzq(parcel, l2);
                    break;

                case 8: // '\b'
                    s25 = zzb.zzq(parcel, l2);
                    break;

                case 9: // '\t'
                    s24 = zzb.zzq(parcel, l2);
                    break;

                case 10: // '\n'
                    s23 = zzb.zzq(parcel, l2);
                    break;

                case 11: // '\013'
                    s22 = zzb.zzq(parcel, l2);
                    break;

                case 12: // '\f'
                    zzb.zza(parcel, l2, 4);
                    l1 = parcel.readInt();
                    break;

                case 13: // '\r'
                    s21 = zzb.zzq(parcel, l2);
                    break;

                case 14: // '\016'
                    s20 = zzb.zzq(parcel, l2);
                    break;

                case 15: // '\017'
                    s19 = zzb.zzq(parcel, l2);
                    break;

                case 16: // '\020'
                    s18 = zzb.zzq(parcel, l2);
                    break;

                case 17: // '\021'
                    s17 = zzb.zzq(parcel, l2);
                    break;

                case 18: // '\022'
                    as3 = zzb.zzC(parcel, l2);
                    break;

                case 19: // '\023'
                    as2 = zzb.zzC(parcel, l2);
                    break;

                case 20: // '\024'
                    as1 = zzb.zzC(parcel, l2);
                    break;

                case 21: // '\025'
                    s16 = zzb.zzq(parcel, l2);
                    break;

                case 22: // '\026'
                    s15 = zzb.zzq(parcel, l2);
                    break;

                case 23: // '\027'
                    abyte0 = zzb.zzt(parcel, l2);
                    break;

                case 24: // '\030'
                    zzb.zza(parcel, l2, 4);
                    k1 = parcel.readInt();
                    break;

                case 25: // '\031'
                    zzb.zza(parcel, l2, 4);
                    j1 = parcel.readInt();
                    break;

                case 26: // '\032'
                    zzb.zza(parcel, l2, 4);
                    i1 = parcel.readInt();
                    break;

                case 27: // '\033'
                    zzb.zza(parcel, l2, 4);
                    l = parcel.readInt();
                    break;

                case 28: // '\034'
                    s14 = zzb.zzq(parcel, l2);
                    break;

                case 29: // '\035'
                    s13 = zzb.zzq(parcel, l2);
                    break;

                case 30: // '\036'
                    s12 = zzb.zzq(parcel, l2);
                    break;

                case 31: // '\037'
                    bundle1 = zzb.zzs(parcel, l2);
                    break;

                case 32: // ' '
                    zzb.zza(parcel, l2, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    break;

                case 33: // '!'
                    zzb.zza(parcel, l2, 4);
                    k = parcel.readInt();
                    break;

                case 34: // '"'
                    zzb.zza(parcel, l2, 4);
                    j = parcel.readInt();
                    break;

                case 35: // '#'
                    zzb.zza(parcel, l2, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    break;

                case 36: // '$'
                    s11 = zzb.zzq(parcel, l2);
                    break;

                case 37: // '%'
                    s10 = zzb.zzq(parcel, l2);
                    break;

                case 38: // '&'
                    zzb.zza(parcel, l2, 4);
                    i = parcel.readInt();
                    break;

                case 39: // '\''
                    s9 = zzb.zzq(parcel, l2);
                    break;

                case 40: // '('
                    s8 = zzb.zzq(parcel, l2);
                    break;

                case 41: // ')'
                    s7 = zzb.zzq(parcel, l2);
                    break;

                case 42: // '*'
                    s6 = zzb.zzq(parcel, l2);
                    break;

                case 43: // '+'
                    s5 = zzb.zzq(parcel, l2);
                    break;

                case 44: // ','
                    s4 = zzb.zzq(parcel, l2);
                    break;

                case 45: // '-'
                    s3 = zzb.zzq(parcel, l2);
                    break;

                case 46: // '.'
                    bitmapteleporter = (BitmapTeleporter)zzb.zza(parcel, l2, BitmapTeleporter.CREATOR);
                    break;

                case 47: // '/'
                    s2 = zzb.zzq(parcel, l2);
                    break;

                case 48: // '0'
                    afileteleporter = (FileTeleporter[])zzb.zzb(parcel, l2, FileTeleporter.CREATOR);
                    break;

                case 49: // '1'
                    as = zzb.zzC(parcel, l2);
                    break;

                case 50: // '2'
                    zzb.zza(parcel, l2, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 51: // '3'
                    s1 = zzb.zzq(parcel, l2);
                    break;

                case 52: // '4'
                    themesettings = (ThemeSettings)zzb.zza(parcel, l2, ThemeSettings.CREATOR);
                    break;

                case 53: // '5'
                    logoptions = (LogOptions)zzb.zza(parcel, l2, LogOptions.CREATOR);
                    break;

                case 54: // '6'
                    s = zzb.zzq(parcel, l2);
                    break;

                case 55: // '7'
                    zzb.zza(parcel, l2, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 56: // '8'
                    bundle = zzb.zzs(parcel, l2);
                    break;

                case 57: // '9'
                    arraylist = zzb.zzc(parcel, l2, RectF.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != k2)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k2).toString(), parcel);
            } else
            {
                return new ErrorReport(j2, applicationerrorreport, s29, i2, s28, s27, s26, s25, s24, s23, s22, l1, s21, s20, s19, s18, s17, as3, as2, as1, s16, s15, abyte0, k1, j1, i1, l, s14, s13, s12, bundle1, flag3, k, j, flag2, s11, s10, i, s9, s8, s7, s6, s5, s4, s3, bitmapteleporter, s2, afileteleporter, as, flag1, s1, themesettings, logoptions, s, flag, bundle, arraylist);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new ErrorReport[i];
    }
}
