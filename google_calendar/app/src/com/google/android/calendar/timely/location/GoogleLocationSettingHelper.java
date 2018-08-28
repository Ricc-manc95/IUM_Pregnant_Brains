// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.location;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gsf.Gservices;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;

public class GoogleLocationSettingHelper
{

    private static final Uri GOOGLE_SETTINGS_CONTENT_URI = Uri.parse("content://com.google.settings/partner");
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/location/GoogleLocationSettingHelper);
    private static final ImmutableSet UNSUPPORTED_COUNTRY_CODES_FOR_LOCATION = new SingletonImmutableSet("kr");

    public GoogleLocationSettingHelper()
    {
    }

    private static int getUseLocationForServices(Context context)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj2 = null;
        obj1 = null;
        obj = null;
        context = context.getContentResolver();
        context = context.query(GOOGLE_SETTINGS_CONTENT_URI, new String[] {
            "value"
        }, "name=?", new String[] {
            "use_location_for_services"
        }, null);
        obj1 = obj;
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        obj1 = obj;
        obj = context;
        if (!context.moveToNext())
        {
            break MISSING_BLOCK_LABEL_69;
        }
        obj = context;
        obj1 = context.getString(0);
        obj = obj1;
        if (context != null)
        {
            context.close();
            obj = obj1;
        }
        break MISSING_BLOCK_LABEL_83;
        obj1;
        context = null;
_L4:
        obj = context;
        LogUtils.w(TAG, ((Throwable) (obj1)), "Failed to get 'Use My Location' setting", new Object[0]);
        obj = obj2;
        if (context != null)
        {
            context.close();
            obj = obj2;
        }
          goto _L1
        context;
        obj = obj1;
_L3:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw context;
_L1:
        if (obj == null)
        {
            return 2;
        }
        int i;
        try
        {
            i = Integer.parseInt(((String) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            i = 2;
        }
        return i;
        context;
        if (true) goto _L3; else goto _L2
_L2:
        obj1;
          goto _L4
    }

    public static boolean isGoogleLocationServicesEnabled(Context context)
    {
        String s = null;
        String s1 = Gservices.getString(context.getContentResolver(), "device_country", null);
        if (s1 != null)
        {
            s = s1.toLowerCase();
        }
        boolean flag;
        if (s != null && UNSUPPORTED_COUNTRY_CODES_FOR_LOCATION.contains(s))
        {
            flag = false;
        } else
        {
            flag = true;
        }
        if (flag)
        {
            boolean flag1;
            if (context.getPackageManager().resolveActivity(new Intent("com.google.android.gsf.GOOGLE_APPS_LOCATION_SETTINGS"), 0x10000) != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1 || getUseLocationForServices(context) == 1)
            {
                return true;
            }
        }
        return false;
    }

}
