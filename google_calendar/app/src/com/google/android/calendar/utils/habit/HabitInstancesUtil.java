// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.habit;


public class HabitInstancesUtil
{

    public static String[] getSelectionArgs(String s)
    {
        return (new String[] {
            s, (new StringBuilder(String.valueOf(s).length() + 2)).append(s).append(",%").toString()
        });
    }
}
