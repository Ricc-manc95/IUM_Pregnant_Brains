// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.os.Parcel;
import com.google.android.gms.common.data.BitmapTeleporter;

// Referenced classes of package com.google.android.gms.feedback:
//            FileTeleporter, ThemeSettings, LogOptions, FeedbackOptions

public final class zzb
    implements android.os.Parcelable.Creator
{

    public zzb()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = com.google.android.gms.common.internal.safeparcel.zzb.zzdE(parcel);
        int i = 0;
        String s3 = null;
        android.os.Bundle bundle = null;
        String s2 = null;
        ApplicationErrorReport applicationerrorreport = null;
        String s1 = null;
        BitmapTeleporter bitmapteleporter = null;
        String s = null;
        java.util.ArrayList arraylist = null;
        boolean flag = false;
        ThemeSettings themesettings = null;
        LogOptions logoptions = null;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k = parcel.readInt();
                switch (0xffff & k)
                {
                case 4: // '\004'
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    s3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    bundle = com.google.android.gms.common.internal.safeparcel.zzb.zzs(parcel, k);
                    break;

                case 5: // '\005'
                    s2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 6: // '\006'
                    applicationerrorreport = (ApplicationErrorReport)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, ApplicationErrorReport.CREATOR);
                    break;

                case 7: // '\007'
                    s1 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 8: // '\b'
                    bitmapteleporter = (BitmapTeleporter)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, BitmapTeleporter.CREATOR);
                    break;

                case 9: // '\t'
                    s = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 10: // '\n'
                    arraylist = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, k, FileTeleporter.CREATOR);
                    break;

                case 11: // '\013'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 12: // '\f'
                    themesettings = (ThemeSettings)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, ThemeSettings.CREATOR);
                    break;

                case 13: // '\r'
                    logoptions = (LogOptions)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, LogOptions.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new FeedbackOptions(i, s3, bundle, s2, applicationerrorreport, s1, bitmapteleporter, s, arraylist, flag, themesettings, logoptions);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new FeedbackOptions[i];
    }
}
