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

    public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        return sharedpreferences.getString(zzcaP, "");
    }

    public final Object fromString(String s)
    {
        return s;
    }

    public final Object zzb(zzh zzh1)
    {
        return zzh1.getString(zzcaP, null);
    }

    (String s, String s1, String s2, Uri uri, String s3, String s4)
    {
        zzcaP = s4;
        super(s, s1, s2, uri, s3);
    }
}
