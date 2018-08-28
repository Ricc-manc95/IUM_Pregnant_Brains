// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzaj;

public final class zzf
{

    public static Time zzaI(long l)
    {
        String s;
        boolean flag;
        if (l >= 0L && l < 0x5265c00L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s = (new StringBuilder(38)).append("Invalid timestamp:").append(l).toString();
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf(s));
        } else
        {
            int i = (int)(l / 0x36ee80L);
            l -= (long)i * 0x36ee80L;
            int j = (int)(l / 60000L);
            int k = (int)((l - (long)j * 60000L) / 1000L);
            com.google.android.gms.reminders.model.Time.Builder builder = new com.google.android.gms.reminders.model.Time.Builder();
            builder.zzcjG = Integer.valueOf(i);
            builder.zzcjH = Integer.valueOf(j);
            builder.zzcjI = Integer.valueOf(k);
            return new zzaj(builder.zzcjG, builder.zzcjH, builder.zzcjI);
        }
    }
}
