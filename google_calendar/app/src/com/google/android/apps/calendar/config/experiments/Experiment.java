// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.experiments;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import java.util.UUID;

public abstract class Experiment
{

    public final int id;
    private Boolean mActive;
    public final String name;
    public final int salt;

    protected Experiment(int i, String s, int j, Boolean boolean1)
    {
        id = i;
        salt = j;
        name = s;
        mActive = boolean1;
    }

    protected static int getBucket(Context context, int i, int j)
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences("sync_adapter_prefs.xml", 0);
        String s = sharedpreferences.getString("device_id", null);
        if (s != null)
        {
            context = s;
        } else
        {
            String s1 = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
            context = s1;
            if (TextUtils.isEmpty(s1))
            {
                context = UUID.randomUUID().toString();
            }
            sharedpreferences.edit().putString("device_id", context).apply();
        }
        return Math.abs(context.hashCode() ^ i) % j;
    }

    public final boolean isActive(Context context)
    {
        if (mActive == null)
        {
            Object obj = String.valueOf("EXP_");
            Object obj1 = String.valueOf(name);
            if (((String) (obj1)).length() != 0)
            {
                obj = ((String) (obj)).concat(((String) (obj1)));
            } else
            {
                obj = new String(((String) (obj)));
            }
            if (LogUtils.isLoggableFixed(((String) (obj)), 2))
            {
                obj = Boolean.valueOf(true);
            } else
            if (!LogUtils.isLoggableFixed(((String) (obj)), 4))
            {
                obj = Boolean.valueOf(false);
            } else
            {
                obj = null;
            }
            obj1 = obj;
            if (obj == null)
            {
                obj1 = Boolean.valueOf(isActiveInternal(context));
            }
            mActive = ((Boolean) (obj1));
        }
        return mActive.booleanValue();
    }

    protected abstract boolean isActiveInternal(Context context);

    public String toString()
    {
        String s = name;
        return (new StringBuilder(String.valueOf(s).length() + 17)).append("Experiment{name=").append(s).append("}").toString();
    }
}
