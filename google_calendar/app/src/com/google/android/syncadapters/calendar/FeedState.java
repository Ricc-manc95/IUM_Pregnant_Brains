// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.android.calendarcommon2.LogUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class FeedState
{

    public final JSONObject data;

    FeedState(JSONObject jsonobject)
    {
        data = jsonobject;
    }

    public final void clear()
    {
        for (Iterator iterator = data.keys(); iterator.hasNext(); iterator.remove())
        {
            iterator.next();
        }

    }

    final boolean getBoolean(String s, boolean flag)
    {
        flag = false;
        try
        {
            if (data.has(s))
            {
                flag = data.getBoolean(s);
            }
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("FeedState", jsonexception, "Failed to get %s from %s", new Object[] {
                s, data
            });
            return false;
        }
        return flag;
    }

    final Map getInProgressParams(String s)
    {
        if (!data.has(s))
        {
            return null;
        }
        Object obj;
        try
        {
            obj = new HashMap();
            JSONObject jsonobject = data.getJSONObject(s);
            String s1;
            for (Iterator iterator = jsonobject.keys(); iterator.hasNext(); ((Map) (obj)).put(s1, jsonobject.get(s1)))
            {
                s1 = (String)iterator.next();
            }

        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            LogUtils.e("FeedState", ((Throwable) (obj)), "Failed to get %s.", new Object[] {
                s
            });
            return null;
        }
        return ((Map) (obj));
    }

    final long getLong(String s, long l)
    {
        l = 0L;
        try
        {
            if (data.has(s))
            {
                l = data.getLong(s);
            }
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("FeedState", jsonexception, "Failed to get %s from %s", new Object[] {
                s, data
            });
            return 0L;
        }
        return l;
    }

    public final String getString(String s, String s1)
    {
        s1 = null;
        try
        {
            if (data.has(s))
            {
                s1 = data.getString(s);
            }
        }
        // Misplaced declaration of an exception variable
        catch (String s1)
        {
            LogUtils.e("FeedState", s1, "Failed to get %s from %s", new Object[] {
                s, data
            });
            return null;
        }
        return s1;
    }

    final void putBoolean(String s, boolean flag)
    {
        try
        {
            data.put(s, flag);
            return;
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("FeedState", jsonexception, "Failed to put %s.", new Object[] {
                s
            });
        }
    }

    final void putLong(String s, long l)
    {
        try
        {
            data.put(s, l);
            return;
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("FeedState", jsonexception, "Failed to put %s.", new Object[] {
                s
            });
        }
    }

    final void putString(String s, String s1)
    {
        try
        {
            data.put(s, s1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s1)
        {
            LogUtils.e("FeedState", s1, "Failed to put %s.", new Object[] {
                s
            });
        }
    }

    final void setInProgressParams(String s, Map map)
    {
        JSONObject jsonobject;
        if (map == null)
        {
            data.remove(s);
            return;
        }
        jsonobject = new JSONObject();
        try
        {
            java.util.Map.Entry entry;
            for (map = map.entrySet().iterator(); map.hasNext(); jsonobject.put((String)entry.getKey(), entry.getValue()))
            {
                entry = (java.util.Map.Entry)map.next();
            }

        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            LogUtils.e("FeedState", map, "Failed to put %s.", new Object[] {
                s
            });
            return;
        }
        data.put(s, jsonobject);
        return;
    }

    public final String toString()
    {
        return data.toString();
    }
}
