// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.net.Uri;

// Referenced classes of package com.google.android.gms.phenotype:
//            PhenotypeFlag, zzh

final class nit> extends PhenotypeFlag
{

    private final String zzcaP;

    private static Double zziK(String s)
    {
        double d;
        try
        {
            d = Double.parseDouble(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return Double.valueOf(d);
    }

    public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        return Double.valueOf(sharedpreferences.getFloat(zzcaP, 0.0F));
    }

    public final Object fromString(String s)
    {
        return zziK(s);
    }

    public final Object zzb(zzh zzh1)
    {
        return zzh1.zza(zzcaP, null);
    }

    (String s, String s1, String s2, Uri uri, Double double1, String s3)
    {
        zzcaP = s3;
        super(s, s1, s2, uri, double1);
    }
}
