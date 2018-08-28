// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.exchange;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.android.emailcommon.external.service.RemoteServiceProxy;
import com.android.emailcommon.provider.RecipientAvailability;
import com.android.emailcommon.service.EmailServiceProxy;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineExchangeAttendeeEvent;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ExchangeUtil
{

    private static final ImmutableSet SUPPORTED_PACKAGE_NAMES = ImmutableSet.construct(3, new Object[] {
        "com.google.android.gm.exchange", "com.google.android.gm", "com.google.android.gm.lite"
    });
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/exchange/ExchangeUtil);

    public ExchangeUtil()
    {
    }

    public static EmailServiceProxy getEasServiceProxy(Context context)
    {
        String s = getEasServiceSupportPackageName(context);
        if (s == null)
        {
            return null;
        }
        if (!SUPPORTED_PACKAGE_NAMES.contains(s))
        {
            LogUtils.wtf(TAG, "Unexpected package name used: %s", new Object[] {
                s
            });
            return null;
        } else
        {
            Intent intent = new Intent();
            intent.setClassName(s, "com.android.exchange.service.EasService");
            return new RemoteServiceProxy(context, intent);
        }
    }

    public static String getEasServiceSupportPackageName(Context context)
    {
        if (isEasServiceResolvable(context, "com.google.android.gm"))
        {
            return "com.google.android.gm";
        }
        if (isEasServiceResolvable(context, "com.google.android.gm.lite"))
        {
            return "com.google.android.gm.lite";
        }
        if (Utils.isApkVersionAtLeast(context, "com.google.android.gm.exchange", 0x7b186) && isEasServiceResolvable(context, "com.google.android.gm.exchange"))
        {
            return "com.google.android.gm.exchange";
        } else
        {
            return null;
        }
    }

    public static List getEventsFromRecipientAvailabilities(long l, TimeZone timezone, RecipientAvailability recipientavailability, Context context)
    {
        ArrayList arraylist = new ArrayList();
        if (TextUtils.isEmpty(recipientavailability.mergedFreeBusy)) goto _L2; else goto _L1
_L1:
        int ai[];
        int i;
        int j;
        j = recipientavailability.mergedFreeBusy.length();
        ai = new int[j];
        i = 0;
_L10:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_152;
        }
        recipientavailability.mergedFreeBusy.charAt(i) - 48;
        JVM INSTR tableswitch 0 4: default 92
    //                   0 107
    //                   1 116
    //                   2 125
    //                   3 134
    //                   4 143;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_143;
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        ai[i] = 4;
_L11:
        i++;
        if (true) goto _L10; else goto _L9
_L9:
        ai[i] = 0;
          goto _L11
_L5:
        ai[i] = 1;
          goto _L11
_L6:
        ai[i] = 2;
          goto _L11
_L7:
        ai[i] = 3;
          goto _L11
        ai[i] = 4;
          goto _L11
        j = -1;
        i = 0;
        recipientavailability = null;
_L24:
        if (i >= ai.length) goto _L2; else goto _L12
_L12:
        Object obj;
        int k;
        k = ai[i];
        obj = recipientavailability;
        if (k == 0) goto _L14; else goto _L13
_L13:
        if (k != j)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L16; else goto _L15
_L15:
        obj = new TimelineExchangeAttendeeEvent();
        recipientavailability = context.getResources();
        obj.status = k;
        ((TimelineExchangeAttendeeEvent) (obj)).status;
        JVM INSTR tableswitch 1 4: default 256
    //                   1 344
    //                   2 354
    //                   3 364
    //                   4 374;
           goto _L17 _L18 _L19 _L20 _L21
_L17:
        recipientavailability = null;
_L22:
        obj.title = recipientavailability;
        arraylist.add(obj);
        recipientavailability = ((RecipientAvailability) (obj));
_L16:
        if (recipientavailability != null)
        {
            long l1;
            if (j != 0)
            {
                l1 = l + 0x1b7740L * (long)i;
            } else
            {
                l1 = ((TimelineEvent) (recipientavailability)).timeRange.getUtcStartMillis();
            }
            recipientavailability.timeRange = TimeRange.newInstance(timezone, false, l1, l + 0x1b7740L * (long)(i + 1));
        }
        obj = recipientavailability;
_L14:
        i++;
        j = k;
        recipientavailability = ((RecipientAvailability) (obj));
        continue; /* Loop/switch isn't completed */
_L18:
        recipientavailability = recipientavailability.getString(0x7f1301f8);
        continue; /* Loop/switch isn't completed */
_L19:
        recipientavailability = recipientavailability.getString(0x7f1301f5);
        continue; /* Loop/switch isn't completed */
_L20:
        recipientavailability = recipientavailability.getString(0x7f1301f7);
        continue; /* Loop/switch isn't completed */
_L21:
        recipientavailability = recipientavailability.getString(0x7f1301f6);
        if (true) goto _L22; else goto _L2
_L2:
        return arraylist;
        if (true) goto _L24; else goto _L23
_L23:
    }

    public static boolean isAddNoteSupported(Context context)
    {
        context = getEasServiceProxy(context);
        return context != null && context.getApiVersion() >= 9;
    }

    public static boolean isEasServiceResolvable(Context context, String s)
    {
        boolean flag = false;
        context = context.getPackageManager();
        Intent intent = new Intent();
        intent.setClassName(s, "com.android.exchange.service.EasService");
        if (context.resolveService(intent, 0) != null)
        {
            flag = true;
        }
        return flag;
    }

    public static long roundUpTimeWindow(long l)
    {
        return (((l + 0x1b7740L) - 1L) / 0x1b7740L) * 0x1b7740L;
    }

}
