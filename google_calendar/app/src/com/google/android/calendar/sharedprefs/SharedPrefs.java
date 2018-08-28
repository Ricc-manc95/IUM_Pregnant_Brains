// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.sharedprefs;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPrefs
{

    public static void removeSharedPreference(Context context, String s)
    {
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().remove(s).apply();
        (new BackupManager(context)).dataChanged();
    }

    public static void setSharedPreference(Context context, String s, int i)
    {
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt(s, i).apply();
        (new BackupManager(context)).dataChanged();
    }

    public static void setSharedPreference(Context context, String s, long l)
    {
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putLong(s, l).apply();
        (new BackupManager(context)).dataChanged();
    }

    public static void setSharedPreference(Context context, String s, String s1)
    {
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putString(s, s1).apply();
        (new BackupManager(context)).dataChanged();
    }

    public static void setSharedPreference(Context context, String s, boolean flag)
    {
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean(s, flag).apply();
        (new BackupManager(context)).dataChanged();
    }
}
