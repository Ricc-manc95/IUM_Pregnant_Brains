// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.permission;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.app.ApplicationUtils;

// Referenced classes of package com.google.android.calendar.utils.permission:
//            PermissionsUtil

public final class AndroidPermissionUtils
{

    private static final String PERMISSIONS_CALENDAR[] = {
        "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"
    };
    public static final String PERMISSIONS_CALL[] = {
        "android.permission.CALL_PHONE"
    };
    private static final String PERMISSIONS_CONTACTS[] = {
        "android.permission.READ_CONTACTS"
    };
    private static final String PERMISSIONS_LOCATION[] = {
        "android.permission.ACCESS_COARSE_LOCATION"
    };
    public static final String PERMISSIONS_MANDATORY[] = {
        "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"
    };

    public static int groupOfPermission(String s)
    {
        byte byte0;
        int i;
        i = 0;
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 6: default 68
    //                   -1928411001: 136
    //                   -63024214: 192
    //                   112197485: 206
    //                   603653886: 150
    //                   1271781903: 178
    //                   1977429404: 164;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_206;
_L8:
        switch (byte0)
        {
        default:
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Unexpected permission ".concat(s);
            } else
            {
                s = new String("Unexpected permission ");
            }
            throw new IllegalStateException(s);

        case 2: // '\002'
        case 3: // '\003'
            i = 1;
            // fall through

        case 0: // '\0'
        case 1: // '\001'
            return i;

        case 4: // '\004'
            return 2;

        case 5: // '\005'
            return 3;
        }
_L2:
        if (s.equals("android.permission.READ_CALENDAR"))
        {
            byte0 = 0;
        }
          goto _L8
_L5:
        if (s.equals("android.permission.WRITE_CALENDAR"))
        {
            byte0 = 1;
        }
          goto _L8
_L7:
        if (s.equals("android.permission.READ_CONTACTS"))
        {
            byte0 = 2;
        }
          goto _L8
_L6:
        if (s.equals("android.permission.GET_ACCOUNTS"))
        {
            byte0 = 3;
        }
          goto _L8
_L3:
        if (s.equals("android.permission.ACCESS_COARSE_LOCATION"))
        {
            byte0 = 4;
        }
          goto _L8
        if (s.equals("android.permission.CALL_PHONE"))
        {
            byte0 = 5;
        }
          goto _L8
    }

    public static boolean hasCallPermissions(Context context)
    {
        if (!PermissionsUtil.canRequestPermissions())
        {
            return false;
        }
        boolean flag = hasPermissions(context, PERMISSIONS_CALL);
        if (flag)
        {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("call_permissions_never_ask_detected", false).putLong("call_permissions_request_count", 0L).apply();
        }
        return flag;
    }

    public static boolean hasContactsPermissions(Context context)
    {
        boolean flag = false;
        if (!PermissionsUtil.canRequestPermissions())
        {
            flag = true;
        } else
        if (ApplicationUtils.isSystemApp(context) || context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("has_granted_contacts_permissions", false))
        {
            boolean flag1 = hasPermissions(context, PERMISSIONS_CONTACTS);
            if (flag1)
            {
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("contacts_permissions_never_ask_detected", false).putLong("contacts_permissions_request_count", 0L).apply();
            }
            return flag1;
        }
        return flag;
    }

    public static boolean hasLocationPermissions(Context context)
    {
        boolean flag;
        if (!PermissionsUtil.canRequestPermissions())
        {
            flag = true;
        } else
        {
            boolean flag1 = hasPermissions(context, PERMISSIONS_LOCATION);
            flag = flag1;
            if (flag1)
            {
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("location_permissions_never_ask_detected", false).putLong("location_permissions_request_count", 0L).apply();
                return flag1;
            }
        }
        return flag;
    }

    public static boolean hasMandatoryPermissions(Context context)
    {
        boolean flag = false;
        if (!PermissionsUtil.canRequestPermissions())
        {
            flag = true;
        } else
        if (ApplicationUtils.isSystemApp(context) || context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("has_granted_calendar_permissions", false))
        {
            boolean flag1 = hasPermissions(context, PERMISSIONS_CALENDAR);
            if (flag1)
            {
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("calendar_permissions_never_ask_detected", false).putLong("calendar_permissions_request_count", 0L).apply();
            }
            return flag1;
        }
        return flag;
    }

    private static boolean hasPermissions(Context context, String as[])
    {
        int j = as.length;
        for (int i = 0; i < j; i++)
        {
            String s = as[i];
            int k = PermissionsUtil.checkSelfPermission(context, s);
            LogUtils.d("AndroidPermissionUtils", "Permission: %s=%d", new Object[] {
                s, Integer.valueOf(k)
            });
            if (k != 0)
            {
                return false;
            }
        }

        return true;
    }

    public static void requestContactsPermissions(Activity activity)
    {
        if (PermissionsUtil.canRequestPermissions())
        {
            PermissionsUtil.requestPermissions(activity, PERMISSIONS_CONTACTS, 0);
        }
    }

    public static void requestContactsPermissions(Activity activity, int i)
    {
        if (PermissionsUtil.canRequestPermissions())
        {
            PermissionsUtil.requestPermissions(activity, PERMISSIONS_CONTACTS, i);
        }
    }

    public static void requestMandatoryPermissions(Activity activity, int i)
    {
        if (PermissionsUtil.canRequestPermissions())
        {
            PermissionsUtil.requestPermissions(activity, PERMISSIONS_MANDATORY, 1);
        }
    }

}
