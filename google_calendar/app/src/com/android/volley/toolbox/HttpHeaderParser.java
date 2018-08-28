// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.util.Log;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class HttpHeaderParser
{

    public static com.android.volley.Cache.Entry parseCacheHeaders(NetworkResponse networkresponse)
    {
        Map map;
        String as[];
        boolean flag;
        int i;
        long l;
        long l1;
        long l2;
        long l5;
        l5 = System.currentTimeMillis();
        map = networkresponse.headers;
        l2 = 0L;
        l1 = 0L;
        l = 0L;
        String s = (String)map.get("Date");
        if (s != null)
        {
            l2 = parseDateAsEpoch(s);
        }
        s = (String)map.get("Cache-Control");
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_455;
        }
        as = s.split(",", 0);
        i = 0;
        flag = false;
        l = 0L;
        l1 = 0L;
_L5:
        if (i >= as.length) goto _L2; else goto _L1
_L1:
        String s2;
        s2 = as[i].trim();
        if (s2.equals("no-cache") || s2.equals("no-store"))
        {
            return null;
        }
        if (!s2.startsWith("max-age=")) goto _L4; else goto _L3
_L3:
        long l4 = Long.parseLong(s2.substring(8));
        long l3 = l;
_L6:
        i++;
        l = l3;
        l1 = l4;
          goto _L5
_L4:
        if (!s2.startsWith("stale-while-revalidate="))
        {
            break MISSING_BLOCK_LABEL_180;
        }
        l3 = Long.parseLong(s2.substring(23));
        l4 = l1;
          goto _L6
        if (s2.equals("must-revalidate")) goto _L8; else goto _L7
_L7:
        l3 = l;
        l4 = l1;
        if (!s2.equals("proxy-revalidate")) goto _L6; else goto _L8
_L8:
        flag = true;
        l3 = l;
        l4 = l1;
          goto _L6
_L2:
        boolean flag1 = true;
_L9:
        String s1 = (String)map.get("Expires");
        Exception exception;
        if (s1 != null)
        {
            l4 = parseDateAsEpoch(s1);
        } else
        {
            l4 = 0L;
        }
        s1 = (String)map.get("Last-Modified");
        if (s1 != null)
        {
            l3 = parseDateAsEpoch(s1);
        } else
        {
            l3 = 0L;
        }
        s1 = (String)map.get("ETag");
        if (flag1)
        {
            l1 = l5 + 1000L * l1;
            com.android.volley.Cache.Entry entry;
            if (flag)
            {
                l = l1;
            } else
            {
                l = 1000L * l + l1;
            }
        } else
        if (l2 > 0L && l4 >= l2)
        {
            l = (l4 - l2) + l5;
            l1 = l;
        } else
        {
            l = 0L;
            l1 = 0L;
        }
        entry = new com.android.volley.Cache.Entry();
        entry.data = networkresponse.data;
        entry.etag = s1;
        entry.softTtl = l1;
        entry.ttl = l;
        entry.serverDate = l2;
        entry.lastModified = l3;
        entry.responseHeaders = map;
        entry.allResponseHeaders = networkresponse.allHeaders;
        return entry;
        exception;
        l3 = l;
        l4 = l1;
          goto _L6
        exception;
        l3 = l;
        l4 = l1;
          goto _L6
        flag = false;
        flag1 = false;
          goto _L9
    }

    private static long parseDateAsEpoch(String s)
    {
        long l;
        try
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
            simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
            l = simpledateformat.parse(s).getTime();
        }
        catch (ParseException parseexception)
        {
            Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Unable to parse dateStr: %s, falling back to 0", new Object[] {
                s
            }), parseexception);
            return 0L;
        }
        return l;
    }
}
