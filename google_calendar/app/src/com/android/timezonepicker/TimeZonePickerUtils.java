// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import android.content.Context;
import android.content.res.Resources;
import java.util.Locale;

public final class TimeZonePickerUtils
{

    public static final android.text.Spannable.Factory spannableFactory = android.text.Spannable.Factory.getInstance();
    public Locale defaultLocale;
    public String overrideIds[];
    public String overrideLabels[];

    public TimeZonePickerUtils(Context context)
    {
        context = context.getResources();
        overrideIds = context.getStringArray(0x7f0b0055);
        overrideLabels = context.getStringArray(0x7f0b0056);
    }

    public static void appendGmtOffset(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append("GMT");
        if (i < 0)
        {
            stringbuilder.append('-');
        } else
        {
            stringbuilder.append('+');
        }
        i = Math.abs(i);
        stringbuilder.append((long)i / 0x36ee80L);
        i = (i / 60000) % 60;
        if (i != 0)
        {
            stringbuilder.append(':');
            if (i < 10)
            {
                stringbuilder.append('0');
            }
            stringbuilder.append(i);
        }
    }

}
