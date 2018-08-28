// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.net.Uri;
import com.google.android.gms.internal.zzbqq;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.gms.phenotype:
//            PhenotypeFlag, zzh

final class nit> extends PhenotypeFlag
{

    private final String zzcaP;

    public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        return Boolean.valueOf(sharedpreferences.getBoolean(zzcaP, false));
    }

    public final Object fromString(String s)
    {
        if (zzbqq.bu.matcher(s).matches())
        {
            return Boolean.valueOf(true);
        }
        if (zzbqq.bv.matcher(s).matches())
        {
            return Boolean.valueOf(false);
        } else
        {
            return null;
        }
    }

    public final Object zzb(zzh zzh1)
    {
        Object obj = null;
        String s = zzh1.getString(zzcaP, null);
        zzh1 = obj;
        if (s != null)
        {
            if (zzbqq.bu.matcher(s).matches())
            {
                zzh1 = Boolean.valueOf(true);
            } else
            {
                zzh1 = obj;
                if (zzbqq.bv.matcher(s).matches())
                {
                    return Boolean.valueOf(false);
                }
            }
        }
        return zzh1;
    }

    (String s, String s1, String s2, Uri uri, Boolean boolean1, String s3)
    {
        zzcaP = s3;
        super(s, s1, s2, uri, boolean1);
    }
}
