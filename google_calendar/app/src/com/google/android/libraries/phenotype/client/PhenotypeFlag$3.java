// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gsf.Gservices;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            PhenotypeFlag

final class ctory extends PhenotypeFlag
{

    private final Boolean fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        boolean flag;
        try
        {
            flag = sharedpreferences.getBoolean(mendelFlagName, false);
        }
        catch (ClassCastException classcastexception)
        {
            sharedpreferences = String.valueOf(mendelFlagName);
            if (sharedpreferences.length() != 0)
            {
                sharedpreferences = "Invalid boolean value in SharedPreferences for ".concat(sharedpreferences);
            } else
            {
                sharedpreferences = new String("Invalid boolean value in SharedPreferences for ");
            }
            Log.e("PhenotypeFlag", sharedpreferences, classcastexception);
            return null;
        }
        return Boolean.valueOf(flag);
    }

    protected final volatile Object fromSharedPreferences(SharedPreferences sharedpreferences)
    {
        return fromSharedPreferences(sharedpreferences);
    }

    protected final Object fromString(String s)
    {
        if (Gservices.TRUE_PATTERN.matcher(s).matches())
        {
            return Boolean.valueOf(true);
        }
        if (Gservices.FALSE_PATTERN.matcher(s).matches())
        {
            return Boolean.valueOf(false);
        } else
        {
            String s1 = mendelFlagName;
            Log.e("PhenotypeFlag", (new StringBuilder(String.valueOf(s1).length() + 28 + String.valueOf(s).length())).append("Invalid boolean value for ").append(s1).append(": ").append(s).toString());
            return null;
        }
    }

    ctory(ctory ctory, String s, Boolean boolean1)
    {
        super(ctory, s, boolean1);
    }
}
