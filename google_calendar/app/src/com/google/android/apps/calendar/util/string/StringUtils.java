// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.string;

import java.util.Locale;

public final class StringUtils
{

    public static String capitalizeStandalone(String s, Locale locale)
    {
        try
        {
            int i = s.codePointAt(0);
            locale = String.valueOf((new String(new int[] {
                i
            }, 0, 1)).toUpperCase(locale));
            String s1 = String.valueOf(s.substring(Character.charCount(i)));
            if (s1.length() != 0)
            {
                return locale.concat(s1);
            }
            locale = new String(locale);
        }
        // Misplaced declaration of an exception variable
        catch (Locale locale)
        {
            return s;
        }
        return locale;
    }
}
