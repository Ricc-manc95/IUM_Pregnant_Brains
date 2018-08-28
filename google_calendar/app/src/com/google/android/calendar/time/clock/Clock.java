// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time.clock;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Clock
{

    public static long mockedTimestamp;

    static 
    {
        mockedTimestamp = 0L;
        String s;
        try
        {
            s = (new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[] {
                "/system/bin/getprop", "fakeTimestamp"
            }).getInputStream()))).readLine();
        }
        catch (Exception exception)
        {
            Log.e("Clock", "Failed to parse timestamp");
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        if (!"".equals(s))
        {
            mockedTimestamp = (new SimpleDateFormat("dd.MM.yyyy kk:mm:ss", Locale.US)).parse(s).getTime();
        }
    }
}
