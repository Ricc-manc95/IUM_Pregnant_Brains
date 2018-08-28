// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar:
//            UpgradeReceiver

public class AllPrefsUpgradeReceiver extends UpgradeReceiver
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/AllPrefsUpgradeReceiver);

    public AllPrefsUpgradeReceiver()
    {
    }

    private static void upgradePrefs(Context context, String s, String s1)
    {
        if (s1 == null || s == null)
        {
            LogUtils.e(TAG, "Attempted to upgrade null sharedprefs file.", new Object[0]);
        }
        if (s1.equals(s))
        {
            LogUtils.d(TAG, "No upgrade necessary for shared prefs in %s", new Object[] {
                s1
            });
            return;
        }
        SharedPreferences sharedpreferences = context.getSharedPreferences(s, 0);
        s = context.getSharedPreferences(s1, 0);
        context = s.getAll();
        s = s.edit();
        s1 = sharedpreferences.getAll().entrySet().iterator();
        do
        {
            if (!s1.hasNext())
            {
                break;
            }
            Object obj = (java.util.Map.Entry)s1.next();
            String s2 = (String)((java.util.Map.Entry) (obj)).getKey();
            obj = ((java.util.Map.Entry) (obj)).getValue();
            if (obj != null && !context.containsKey(s2))
            {
                if (obj instanceof Integer)
                {
                    s.putInt(s2, ((Integer)obj).intValue());
                } else
                if (obj instanceof Boolean)
                {
                    s.putBoolean(s2, ((Boolean)obj).booleanValue());
                } else
                if (obj instanceof String)
                {
                    s.putString(s2, (String)obj);
                } else
                if (obj instanceof Float)
                {
                    s.putFloat(s2, ((Float)obj).floatValue());
                } else
                if (obj instanceof Set)
                {
                    obj = (Set)obj;
                    if (!((Set) (obj)).isEmpty() && (((Set) (obj)).toArray()[0] instanceof String))
                    {
                        s.putStringSet(s2, ((Set) (obj)));
                    }
                }
            }
        } while (true);
        s.apply();
    }

    protected final void doUpgrade(Context context)
    {
        upgradePrefs(context, "com.android.calendar_preferences", "com.google.android.calendar_preferences");
        upgradePrefs(context, "com.android.calendar_preferences_no_backup", "com.google.android.calendar_preferences_no_backup");
        (new BackupManager(context)).dataChanged();
    }

}
