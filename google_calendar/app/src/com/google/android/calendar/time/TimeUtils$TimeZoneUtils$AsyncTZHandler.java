// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.time:
//            TimeUtils

final class  extends AsyncQueryHandler
{

    protected final void onQueryComplete(int i, Object obj, Cursor cursor)
    {
        HashSet hashset = ;
        hashset;
        JVM INSTR monitorenter ;
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        ss = false;
        ss = true;
        hashset;
        JVM INSTR monitorexit ;
        return;
        int j;
        int k;
        j = cursor.getColumnIndexOrThrow("key");
        k = cursor.getColumnIndexOrThrow("value");
        i = 0;
_L3:
        String s;
        String s1;
        if (!cursor.moveToNext())
        {
            break MISSING_BLOCK_LABEL_157;
        }
        s = cursor.getString(j);
        s1 = cursor.getString(k);
        if (!TextUtils.equals(s, "timezoneType"))
        {
            break MISSING_BLOCK_LABEL_114;
        }
        boolean flag;
        if (!TextUtils.equals(s1, "auto"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag == ss)
        {
            continue; /* Loop/switch isn't completed */
        }
        ss = flag;
        break MISSING_BLOCK_LABEL_249;
        if (!TextUtils.equals(s, "timezoneInstancesPrevious") || TextUtils.isEmpty(s1) || TextUtils.equals(ss, s1))
        {
            continue; /* Loop/switch isn't completed */
        }
        ss = s1;
        break MISSING_BLOCK_LABEL_249;
        obj;
        hashset;
        JVM INSTR monitorexit ;
        throw obj;
        cursor.close();
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        obj = ((Context)obj).getSharedPreferences("com.google.android.calendar_preferences", 0);
        TimeUtils.setSharedPreference(((android.content.SharedPreferences) (obj)), "preferences_home_tz_enabled", ss);
        TimeUtils.setSharedPreference(((android.content.SharedPreferences) (obj)), "preferences_home_tz", ss);
        ss = false;
        obj = ss.iterator();
_L1:
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break MISSING_BLOCK_LABEL_239;
            }
            cursor = (Runnable)((Iterator) (obj)).next();
        } while (cursor == null);
        cursor.run();
          goto _L1
        ss.clear();
        hashset;
        JVM INSTR monitorexit ;
        return;
        i = 1;
        if (true) goto _L3; else goto _L2
_L2:
    }

    public (ContentResolver contentresolver)
    {
        super(contentresolver);
    }
}
