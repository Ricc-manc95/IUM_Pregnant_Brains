// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.util;

import android.support.v4.text.BidiFormatter;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import java.util.Locale;

public final class MeetingsUtils
{

    public static String formatPin(Locale locale, String s)
    {
        StringBuilder stringbuilder;
        int i;
        int j;
        if (s.endsWith("#"))
        {
            i = s.length() - 1;
        } else
        {
            i = s.length();
            s = String.valueOf(s).concat("#");
        }
        stringbuilder = new StringBuilder();
        for (j = 0; j + 3 < i - 1; j += 3)
        {
            stringbuilder.append(s.substring(j, j + 3));
            stringbuilder.append(' ');
        }

        stringbuilder.append(s.substring(j));
        return BidiFormatter.getInstance(locale).unicodeWrap(stringbuilder.toString(), TextDirectionHeuristicsCompat.LTR);
    }
}
