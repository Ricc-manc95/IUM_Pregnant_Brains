// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            FeedState

public class CalendarSyncState
{

    public final JSONObject data;
    public int originalVersion;
    public final Uri uri;

    CalendarSyncState(int i)
    {
        originalVersion = 0;
        uri = null;
        data = new JSONObject();
        try
        {
            data.put("version", 15);
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to set version.", new Object[0]);
        }
        try
        {
            data.put("firstSeen", true);
        }
        catch (JSONException jsonexception1)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception1, "Failed to set is first seen.", new Object[0]);
        }
        try
        {
            data.put("jellyBeanOrNewer", true);
        }
        catch (JSONException jsonexception2)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception2, "Failed to set is jelly bean.", new Object[0]);
        }
        try
        {
            data.put("b38085245", 0x7fffffff);
            return;
        }
        catch (JSONException jsonexception3)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception3, "Failed to set stage.", new Object[0]);
        }
    }

    CalendarSyncState(Uri uri1, CalendarSyncState calendarsyncstate)
    {
        originalVersion = 0;
        uri = uri1;
        data = calendarsyncstate.data;
    }

    CalendarSyncState(Uri uri1, JSONObject jsonobject)
    {
        originalVersion = 0;
        uri = uri1;
        data = jsonobject;
    }

    public final void addFeed(String s)
    {
        try
        {
            data.put(s, new JSONObject());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            LogUtils.e("CalendarSyncAdapter", s, "Failed to add feed.", new Object[0]);
        }
    }

    public final FeedState getFeedState(String s)
    {
        if (data.has(s))
        {
            try
            {
                s = new FeedState(data.getJSONObject(s));
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LogUtils.e("CalendarSyncAdapter", s, "Bad feed object in sync state", new Object[0]);
                return null;
            }
            return s;
        } else
        {
            return null;
        }
    }

    public final int getHtcMailIssueRecoveryStage()
    {
        int i = 0;
        try
        {
            if (data.has("b38085245"))
            {
                i = data.getInt("b38085245");
            }
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to get stage.", new Object[0]);
            return 0;
        }
        return i;
    }

    public final String getSyncingPackage()
    {
        String s = null;
        try
        {
            if (data.has("package"))
            {
                s = data.getString("package");
            }
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to get syncing package.", new Object[0]);
            return null;
        }
        return s;
    }

    public final int getVersion()
    {
        int i = 0;
        try
        {
            if (data.has("version"))
            {
                i = data.getInt("version");
            }
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to get version.", new Object[0]);
            return 0;
        }
        return i;
    }

    public final boolean isFirstSeen()
    {
        boolean flag;
        try
        {
            flag = data.getBoolean("firstSeen");
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to get is first seen.", new Object[0]);
            return false;
        }
        return flag;
    }

    public final boolean isJellyBean()
    {
        boolean flag;
        try
        {
            flag = data.getBoolean("jellyBeanOrNewer");
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to get is jelly bean.", new Object[0]);
            return true;
        }
        return flag;
    }

    public final void reset()
    {
        Iterator iterator = data.keys();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = (String)iterator.next();
            if (((String) (obj)).indexOf('@') >= 0)
            {
                obj = getFeedState(((String) (obj)));
                if (obj != null)
                {
                    ((FeedState) (obj)).clear();
                }
            }
        } while (true);
        try
        {
            data.put("firstSeen", true);
            return;
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to set is first seen.", new Object[0]);
        }
    }

    public final void setJellyBean(boolean flag)
    {
        try
        {
            data.put("jellyBeanOrNewer", true);
            return;
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to set is jelly bean.", new Object[0]);
        }
    }

    public final void setSyncingPackage(String s)
    {
        try
        {
            data.put("package", s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            LogUtils.e("CalendarSyncAdapter", s, "Failed to set syncing package.", new Object[0]);
        }
    }

    public final void setVersion(int i)
    {
        try
        {
            data.put("version", i);
            return;
        }
        catch (JSONException jsonexception)
        {
            LogUtils.e("CalendarSyncAdapter", jsonexception, "Failed to set version.", new Object[0]);
        }
    }

    public String toString()
    {
        return data.toString();
    }
}
