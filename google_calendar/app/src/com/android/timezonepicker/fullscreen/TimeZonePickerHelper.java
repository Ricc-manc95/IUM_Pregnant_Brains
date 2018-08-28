// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker.fullscreen;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.android.timezonepicker.fullscreen:
//            TimeZonePickerActivity, AutoValue_TimeZonePickerHelper_Result

public final class TimeZonePickerHelper
{

    public static Intent createIntent(Context context, Integer integer, Long long1, String s)
    {
        Intent intent = new Intent(context, com/android/timezonepicker/fullscreen/TimeZonePickerActivity);
        if (integer != null)
        {
            intent.putExtra("toolbar_color", integer);
        }
        if (long1 != null)
        {
            intent.putExtra("time_to_display", long1);
        }
        if (!TextUtils.isEmpty(s))
        {
            updateSharedPrefs(context, s);
        }
        intent.putStringArrayListExtra("recent_time_zone_ids", getIdsFromSharedPrefs(context));
        intent.addFlags(0x4000000);
        return intent;
    }

    private static ArrayList getIdsFromSharedPrefs(Context context)
    {
        context = new ArrayList(Arrays.asList(context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("recent_time_zone_ids", "").split(",")));
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj)
            {
                return TextUtils.isEmpty((String)obj);
            }


            private .Lambda._cls0()
            {
            }
        }

        Iterables.removeIf(context, .Lambda._cls0..instance);
        return context;
    }

    public static Result processResultsIntent(Context context, int i, Intent intent)
    {
        if (i == -1)
        {
            String s = intent.getStringExtra("time_zone_id");
            intent = intent.getStringExtra("time_zone_display_name");
            updateSharedPrefs(context, s);
            return new AutoValue_TimeZonePickerHelper_Result(s, intent, true);
        } else
        {
            return new AutoValue_TimeZonePickerHelper_Result("", "", false);
        }
    }

    private static void updateSharedPrefs(Context context, String s)
    {
        ArrayList arraylist = getIdsFromSharedPrefs(context);
        arraylist.remove(s);
        if (arraylist.size() + 1 > 5)
        {
            arraylist.remove(arraylist.size() - 1);
        }
        arraylist.add(0, s);
        s = TextUtils.join(",", arraylist);
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putString("recent_time_zone_ids", s).apply();
        (new BackupManager(context)).dataChanged();
    }
}
