// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.time;

import java.util.Calendar;
import java.util.TimeZone;

public final class TimeFormatUtil
{

    public static String formatDateRfc5545(long l)
    {
        Object obj = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        ((Calendar) (obj)).setTimeInMillis(l);
        int i = ((Calendar) (obj)).get(1);
        int j = ((Calendar) (obj)).get(2);
        int k = ((Calendar) (obj)).get(5);
        obj = Integer.toString(i);
        String s = Integer.toString(j + 1);
        String s1 = Integer.toString(k);
        StringBuilder stringbuilder = new StringBuilder("00000000");
        stringbuilder.replace(4 - ((String) (obj)).length(), 4, ((String) (obj)));
        stringbuilder.replace(6 - s.length(), 6, s);
        stringbuilder.replace(8 - s1.length(), 8, s1);
        return stringbuilder.toString();
    }

    public static String formatDateTimeRfc5545(long l)
    {
        Object obj = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        ((Calendar) (obj)).setTimeInMillis(l);
        int i = ((Calendar) (obj)).get(1);
        int j = ((Calendar) (obj)).get(2);
        int k = ((Calendar) (obj)).get(5);
        int i1 = ((Calendar) (obj)).get(11);
        int j1 = ((Calendar) (obj)).get(12);
        int k1 = ((Calendar) (obj)).get(13);
        obj = Integer.toString(i);
        String s = Integer.toString(j + 1);
        String s1 = Integer.toString(k);
        String s2 = Integer.toString(i1);
        String s3 = Integer.toString(j1);
        String s4 = Integer.toString(k1);
        StringBuilder stringbuilder = new StringBuilder("00000000T000000Z");
        stringbuilder.replace(4 - ((String) (obj)).length(), 4, ((String) (obj)));
        stringbuilder.replace(6 - s.length(), 6, s);
        stringbuilder.replace(8 - s1.length(), 8, s1);
        stringbuilder.replace(11 - s2.length(), 11, s2);
        stringbuilder.replace(13 - s3.length(), 13, s3);
        stringbuilder.replace(15 - s4.length(), 15, s4);
        return stringbuilder.toString();
    }
}
