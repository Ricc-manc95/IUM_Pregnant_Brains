// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.apiary.GoogleRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class Utils
{

    public static boolean containsSyncFlag(ContentValues contentvalues, String s)
    {
        if (contentvalues != null && contentvalues.containsKey("cal_sync2"))
        {
            if ((contentvalues = contentvalues.getAsString("cal_sync2")).length() >= s.length())
            {
                String s1 = (new StringBuilder(String.valueOf(s).length() + 2)).append('|').append(s).append('|').toString();
                if (contentvalues.equals(s) || contentvalues.startsWith((new StringBuilder(String.valueOf(s).length() + 1)).append(s).append('|').toString()) || contentvalues.endsWith((new StringBuilder(String.valueOf(s).length() + 1)).append('|').append(s).toString()) || contentvalues.contains(s1))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static Map getMapFromRequest(AbstractGoogleJsonClientRequest abstractgooglejsonclientrequest)
    {
        HashMap hashmap = new HashMap();
        abstractgooglejsonclientrequest = abstractgooglejsonclientrequest.entrySet().iterator();
        do
        {
            if (!abstractgooglejsonclientrequest.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)abstractgooglejsonclientrequest.next();
            String s = (String)entry.getKey();
            if (!s.equals("calendarId"))
            {
                hashmap.put(s, entry.getValue());
            }
        } while (true);
        return hashmap;
    }

    public static void setRequestFromMap(Map map, com.google.api.services.calendar.Calendar.Events.List list)
    {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) 
        {
            map = (java.util.Map.Entry)iterator.next();
            String s = (String)map.getKey();
            if (s.equals("timeMin") || s.equals("timeMax") || s.equals("updatedMin"))
            {
                map = DateTime.parseRfc3339(map.getValue().toString());
            } else
            {
                map = ((Map) (map.getValue()));
            }
            map = (com.google.api.services.calendar.Calendar.Events.List)list.set(s, map);
        }
    }

    public static transient void setSyncFlags(ContentValues contentvalues, String as[])
    {
        if (as.length == 0)
        {
            contentvalues.remove("cal_sync2");
            return;
        }
        if (as.length == 1)
        {
            contentvalues.put("cal_sync2", as[0]);
            return;
        } else
        {
            contentvalues.put("cal_sync2", TextUtils.join("|", as));
            return;
        }
    }

    public static void setUserAgentFromContext(GoogleRequestInitializer googlerequestinitializer, Context context)
    {
        try
        {
            String s = context.getPackageName();
            int i = context.getPackageManager().getPackageInfo(s, 0).versionCode;
            googlerequestinitializer.userAgentString = (new StringBuilder(String.valueOf(s).length() + 12)).append(s).append(":").append(i).toString();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (GoogleRequestInitializer googlerequestinitializer)
        {
            return;
        }
    }
}
