// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.content.SharedPreferences;
import android.util.Log;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            PhenotypeFlag

final class ctory extends PhenotypeFlag
{

    private final Long fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        long l;
        try
        {
            l = sharedpreferences.getLong(mendelFlagName, 0L);
        }
        catch (ClassCastException classcastexception)
        {
            sharedpreferences = String.valueOf(mendelFlagName);
            if (sharedpreferences.length() != 0)
            {
                sharedpreferences = "Invalid long value in SharedPreferences for ".concat(sharedpreferences);
            } else
            {
                sharedpreferences = new String("Invalid long value in SharedPreferences for ");
            }
            Log.e("PhenotypeFlag", sharedpreferences, classcastexception);
            return null;
        }
        return Long.valueOf(l);
    }

    private final Long fromString(String s)
    {
        long l;
        try
        {
            l = Long.parseLong(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            String s1 = mendelFlagName;
            Log.e("PhenotypeFlag", (new StringBuilder(String.valueOf(s1).length() + 25 + String.valueOf(s).length())).append("Invalid long value for ").append(s1).append(": ").append(s).toString());
            return null;
        }
        return Long.valueOf(l);
    }

    protected final volatile Object fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        return fromSharedPreferences(sharedpreferences);
    }

    protected final volatile Object fromString(String s)
    {
        return fromString(s);
    }

    ctory(ctory ctory, String s, Long long1)
    {
        super(ctory, s, long1);
    }
}
