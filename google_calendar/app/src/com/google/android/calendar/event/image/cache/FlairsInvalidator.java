// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.cache;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.RequestQueue;
import com.google.android.calendar.volley.VolleyQueueHolder;

// Referenced classes of package com.google.android.calendar.event.image.cache:
//            InvalidateFlairsRequest

public final class FlairsInvalidator
{

    private static final String KEYS_TO_INVALIDATE[] = {
        "gym", "hiking", "cycling", "tennis", "badminton", "soccer", "bbq", "cinema"
    };

    public static void invalidateIfNeeded(Context context, int i)
    {
        if (context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("flairs_invalidated", 0) > 0)
        {
            return;
        }
        Object obj = VolleyQueueHolder.requestQueue;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        obj;
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt("flairs_invalidated", i).apply();
        (new BackupManager(context)).dataChanged();
        throw obj;
        obj = (RequestQueue)obj;
        ((RequestQueue) (obj)).add(new InvalidateFlairsRequest(((RequestQueue) (obj)).mCache, KEYS_TO_INVALIDATE));
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt("flairs_invalidated", i).apply();
        (new BackupManager(context)).dataChanged();
        return;
    }

}
