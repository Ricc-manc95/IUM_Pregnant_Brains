// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.android.calendarcommon2.EventRecurrence;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.common.TimeZoneHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.launch.uri:
//            BaseUriHandler

public class EventTemplateUriHandler extends BaseUriHandler
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/launch/uri/EventTemplateUriHandler);
    private static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");

    public EventTemplateUriHandler(Intent intent)
    {
        super(intent, Arrays.asList(new String[] {
            "http", "https"
        }), Arrays.asList(new String[] {
            "calendar.google.com", "www.google.com"
        }), Arrays.asList(new String[] {
            "/calendar/m?event.*", "/calendar/render.*"
        }));
    }

    private static Date parseDate(Context context, String s, String s1)
    {
        s1 = new SimpleDateFormat(s1, Locale.US);
        boolean flag;
        if (!s.endsWith("Z"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            context = Utils.getTimeZone(context);
        } else
        {
            context = UTC_TIMEZONE;
        }
        s1.setTimeZone(context);
        try
        {
            context = s1.parse(s);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        return context;
    }

    static boolean setDates(Context context, String as[], String s, Intent intent)
    {
        Date date = parseDate(context, as[0], s);
        context = parseDate(context, as[1], s);
        if (date != null && context != null)
        {
            intent.putExtra("beginTime", date.getTime());
            intent.putExtra("endTime", context.getTime());
            return true;
        } else
        {
            return false;
        }
    }

    public static void setExtrasFromUri(Context context, Uri uri, Intent intent)
    {
        Object obj;
        obj = null;
        String s = uri.getQueryParameter("text");
        if (s != null && !TextUtils.isEmpty(s))
        {
            intent.putExtra("title", s);
        }
        s = uri.getQueryParameter("location");
        if (s != null && !TextUtils.isEmpty(s))
        {
            intent.putExtra("eventLocation", s);
        }
        s = uri.getQueryParameter("details");
        if (s != null && !TextUtils.isEmpty(s))
        {
            intent.putExtra("description", s);
        }
        s = uri.getQueryParameter("ctz");
        if (s != null)
        {
            String s1;
            boolean flag;
            if (s == null || TimeZoneHelper.isValidTimeZoneId(s))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                intent.putExtra("eventTimezone", s);
            }
        }
        s = uri.getQueryParameter("recur");
        if (s != null && s.startsWith("RRULE:"))
        {
            s1 = s.substring(6);
            try
            {
                (new EventRecurrence()).parse(s1);
                intent.putExtra("rrule", s1);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1)
            {
                LogUtils.e(TAG, ((Throwable) (obj1)), "Failed to parse event's recurrence rule. Got: %s.", new Object[] {
                    s
                });
            }
        }
        s = uri.getQueryParameter("dates");
        if (s != null)
        {
            Object obj1 = s.split("/");
            if (obj1.length != 2)
            {
                LogUtils.e(TAG, "Failed to parse event's start and end dates.Required format: yyyyMMdd['T'HHmmss]/yyyyMMdd['T'HHmmss], got: %s.", new Object[] {
                    s
                });
            } else
            if (!setDates(context, ((String []) (obj1)), "yyyyMMdd'T'HHmmss", intent))
            {
                if (setDates(context, ((String []) (obj1)), "yyyyMMdd", intent))
                {
                    intent.putExtra("allDay", true);
                } else
                {
                    LogUtils.e(TAG, "Failed to parse event's start and end dates. Got: %s.", new Object[] {
                        s
                    });
                }
            }
        }
        context = uri.getQueryParameters("add");
        if (context == null || context.isEmpty())
        {
            context = null;
        } else
        {
            context = TextUtils.join(",", context);
        }
        intent.putExtra("android.intent.extra.EMAIL", context);
        context = uri.getQueryParameter("icc");
        if (context != null) goto _L2; else goto _L1
_L1:
        context = null;
_L28:
        intent.putExtra("accessLevel", context);
        context = uri.getQueryParameter("crm");
        if (context != null) goto _L4; else goto _L3
_L3:
        context = obj;
_L26:
        intent.putExtra("availability", context);
        return;
_L2:
        context.hashCode();
        JVM INSTR lookupswitch 5: default 460
    //                   -2032180703: 522
    //                   -1924094359: 537
    //                   -1852947792: 582
    //                   364290440: 567
    //                   403485027: 552;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L5:
        byte byte0 = -1;
_L12:
        switch (byte0)
        {
        default:
            LogUtils.e(TAG, "Access level must be one of DEFAULT, PUBLIC, PRIVATE, CONFIDENTIAL, SECRET. Got %s.", new Object[] {
                context
            });
            context = null;
            break;

        case 0: // '\0'
            context = Integer.valueOf(0);
            break;

        case 1: // '\001'
            context = Integer.valueOf(3);
            break;

        case 2: // '\002'
            context = Integer.valueOf(2);
            break;

        case 3: // '\003'
            context = Integer.valueOf(0);
            break;

        case 4: // '\004'
            context = Integer.valueOf(2);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (!context.equals("DEFAULT")) goto _L5; else goto _L11
_L11:
        byte0 = 0;
          goto _L12
_L7:
        if (!context.equals("PUBLIC")) goto _L5; else goto _L13
_L13:
        byte0 = 1;
          goto _L12
_L10:
        if (!context.equals("PRIVATE")) goto _L5; else goto _L14
_L14:
        byte0 = 2;
          goto _L12
_L9:
        if (!context.equals("CONFIDENTIAL")) goto _L5; else goto _L15
_L15:
        byte0 = 3;
          goto _L12
_L8:
        if (!context.equals("SECRET")) goto _L5; else goto _L16
_L16:
        byte0 = 4;
          goto _L12
_L4:
        context.hashCode();
        JVM INSTR lookupswitch 3: default 676
    //                   2050553: 747
    //                   118053941: 763
    //                   2052692649: 731;
           goto _L17 _L18 _L19 _L20
_L17:
        byte0 = -1;
_L22:
        switch (byte0)
        {
        default:
            LogUtils.e(TAG, "Availability must be one of AVAILABLE,BUSY,BLOCKING. Got %s.", new Object[] {
                context
            });
            context = obj;
            break;

        case 0: // '\0'
            context = Integer.valueOf(1);
            break;

        case 1: // '\001'
            context = Integer.valueOf(0);
            break;

        case 2: // '\002'
            context = Integer.valueOf(0);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L20:
        if (!context.equals("AVAILABLE")) goto _L17; else goto _L21
_L21:
        byte0 = 0;
          goto _L22
_L18:
        if (!context.equals("BUSY")) goto _L17; else goto _L23
_L23:
        byte0 = 1;
          goto _L22
_L19:
        if (!context.equals("BLOCKING")) goto _L17; else goto _L24
_L24:
        byte0 = 2;
          goto _L22
        if (true) goto _L26; else goto _L25
_L25:
        if (true) goto _L28; else goto _L27
_L27:
    }

    public final boolean matches()
    {
        if (!super.matches())
        {
            return false;
        } else
        {
            return "TEMPLATE".equals(mIntent.getData().getQueryParameter("action"));
        }
    }

}
