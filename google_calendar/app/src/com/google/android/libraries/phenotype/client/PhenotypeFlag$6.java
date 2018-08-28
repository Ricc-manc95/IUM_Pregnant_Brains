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

    private final String fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        try
        {
            sharedpreferences = sharedpreferences.getString(mendelFlagName, null);
        }
        catch (ClassCastException classcastexception)
        {
            sharedpreferences = String.valueOf(mendelFlagName);
            if (sharedpreferences.length() != 0)
            {
                sharedpreferences = "Invalid string value in SharedPreferences for ".concat(sharedpreferences);
            } else
            {
                sharedpreferences = new String("Invalid string value in SharedPreferences for ");
            }
            Log.e("PhenotypeFlag", sharedpreferences, classcastexception);
            return null;
        }
        return sharedpreferences;
    }

    protected final volatile Object fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        return fromSharedPreferences(sharedpreferences);
    }

    protected final Object fromString(String s)
    {
        return s;
    }

    ctory(ctory ctory, String s, String s1)
    {
        super(ctory, s, s1);
    }
}
