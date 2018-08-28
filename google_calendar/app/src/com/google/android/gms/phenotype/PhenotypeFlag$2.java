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

    private static Integer zziI(String s)
    {
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return Integer.valueOf(i);
    }

    public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        return Integer.valueOf((int)sharedpreferences.getLong(zzcaP, 0L));
    }

    public final Object fromString(String s)
    {
        return zziI(s);
    }

    public final Object zzb(zzh zzh1)
    {
        return zzh1.getInteger(zzcaP, null);
    }

    (String s, String s1, String s2, Uri uri, Integer integer, String s3)
    {
        zzcaP = s3;
        super(s, s1, s2, uri, integer);
    }
}
