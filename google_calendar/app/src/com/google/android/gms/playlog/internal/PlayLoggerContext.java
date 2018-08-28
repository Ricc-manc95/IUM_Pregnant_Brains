// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.playlog.internal:
//            zza

public class PlayLoggerContext extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.playlog.internal.zza();
    public final boolean isAnonymous;
    public final boolean logAndroidId;
    public final int logSource;
    public final String logSourceName;
    public final String loggingId;
    public final String packageName;
    public final int packageVersionCode;
    public final int qosTier;
    public final String uploadAccountName;
    public final int versionCode;

    public PlayLoggerContext(int i, String s, int j, int k, String s1, String s2, boolean flag, 
            String s3, boolean flag1, int l)
    {
        versionCode = i;
        packageName = s;
        packageVersionCode = j;
        logSource = k;
        uploadAccountName = s1;
        loggingId = s2;
        logAndroidId = flag;
        logSourceName = s3;
        isAnonymous = flag1;
        qosTier = l;
    }

    public PlayLoggerContext(String s, int i, int j, String s1, String s2, String s3, boolean flag, 
            int k)
    {
        boolean flag1 = true;
        super();
        versionCode = 1;
        if (s == null)
        {
            throw new NullPointerException("null reference");
        }
        packageName = (String)s;
        packageVersionCode = i;
        logSource = j;
        logSourceName = s1;
        uploadAccountName = s2;
        loggingId = s3;
        if (flag)
        {
            flag1 = false;
        }
        logAndroidId = flag1;
        isAnonymous = flag;
        qosTier = k;
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof PlayLoggerContext))
        {
            break MISSING_BLOCK_LABEL_224;
        }
        obj = (PlayLoggerContext)obj;
        if (versionCode != ((PlayLoggerContext) (obj)).versionCode || !packageName.equals(((PlayLoggerContext) (obj)).packageName) || packageVersionCode != ((PlayLoggerContext) (obj)).packageVersionCode || logSource != ((PlayLoggerContext) (obj)).logSource)
        {
            break; /* Loop/switch isn't completed */
        }
        String s = logSourceName;
        String s1 = ((PlayLoggerContext) (obj)).logSourceName;
        boolean flag;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        s = uploadAccountName;
        s1 = ((PlayLoggerContext) (obj)).uploadAccountName;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        s = loggingId;
        s1 = ((PlayLoggerContext) (obj)).loggingId;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && logAndroidId == ((PlayLoggerContext) (obj)).logAndroidId && isAnonymous == ((PlayLoggerContext) (obj)).isAnonymous && qosTier == ((PlayLoggerContext) (obj)).qosTier) goto _L1; else goto _L3
_L3:
        return false;
        return false;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(versionCode), packageName, Integer.valueOf(packageVersionCode), Integer.valueOf(logSource), logSourceName, uploadAccountName, loggingId, Boolean.valueOf(logAndroidId), Boolean.valueOf(isAnonymous), Integer.valueOf(qosTier)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PlayLoggerContext[");
        stringbuilder.append("versionCode=").append(versionCode).append(',');
        stringbuilder.append("package=").append(packageName).append(',');
        stringbuilder.append("packageVersionCode=").append(packageVersionCode).append(',');
        stringbuilder.append("logSource=").append(logSource).append(',');
        stringbuilder.append("logSourceName=").append(logSourceName).append(',');
        stringbuilder.append("uploadAccount=").append(uploadAccountName).append(',');
        stringbuilder.append("loggingId=").append(loggingId).append(',');
        stringbuilder.append("logAndroidId=").append(logAndroidId).append(',');
        stringbuilder.append("isAnonymous=").append(isAnonymous).append(',');
        stringbuilder.append("qosTier=").append(qosTier);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        i = versionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(i);
        zzc.zza(parcel, 2, packageName, false);
        i = packageVersionCode;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(i);
        i = logSource;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(i);
        zzc.zza(parcel, 5, uploadAccountName, false);
        zzc.zza(parcel, 6, loggingId, false);
        boolean flag1 = logAndroidId;
        zzc.zzb(parcel, 7, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zza(parcel, 8, logSourceName, false);
        flag1 = isAnonymous;
        zzc.zzb(parcel, 9, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = qosTier;
        zzc.zzb(parcel, 10, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
