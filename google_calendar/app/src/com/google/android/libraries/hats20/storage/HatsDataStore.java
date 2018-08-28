// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import java.util.concurrent.TimeUnit;

public final class HatsDataStore
{

    public static final long MILLIS_TO_CACHE_FAILED_DOWNLOAD;
    public final SharedPreferences sharedPreferences;

    public HatsDataStore(SharedPreferences sharedpreferences)
    {
        sharedPreferences = sharedpreferences;
    }

    public static String getKeyForPrefSuffix(String s, String s1)
    {
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append('_').append(s1).toString();
    }

    public static SharedPreferences getSharedPreferences(Context context)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy = StrictMode.allowThreadDiskReads();
        context = context.getSharedPreferences("com.google.android.libraries.hats20", 0);
        StrictMode.setThreadPolicy(threadpolicy);
        return context;
        context;
        StrictMode.setThreadPolicy(threadpolicy);
        throw context;
    }

    private final long getSurveyExpirationDate(String s)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy = StrictMode.allowThreadDiskReads();
        long l = sharedPreferences.getLong(getKeyForPrefSuffix(s, "EXPIRATION_DATE"), -1L);
        StrictMode.setThreadPolicy(threadpolicy);
        return l;
        s;
        StrictMode.setThreadPolicy(threadpolicy);
        throw s;
    }

    public final void removeSurvey(String s)
    {
        sharedPreferences.edit().remove(getKeyForPrefSuffix(s, "EXPIRATION_DATE")).remove(getKeyForPrefSuffix(s, "RESPONSE_CODE")).remove(getKeyForPrefSuffix(s, "CONTENT")).apply();
    }

    public final void removeSurveyIfExpired(String s)
    {
        long l = getSurveyExpirationDate(s);
        if (l == -1L)
        {
            sharedPreferences.edit().remove(getKeyForPrefSuffix(s, "RESPONSE_CODE")).remove(getKeyForPrefSuffix(s, "CONTENT")).apply();
        } else
        if (l < System.currentTimeMillis() / 1000L)
        {
            removeSurvey(s);
            return;
        }
    }

    static 
    {
        MILLIS_TO_CACHE_FAILED_DOWNLOAD = TimeUnit.HOURS.toMillis(24L);
    }
}
