// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.intent.IntentUtils;
import java.util.Iterator;
import java.util.List;

public class ActivityUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/activity/ActivityUtils);

    private ActivityUtils()
    {
    }

    public static void prepareIntentToOpenLink(Intent intent)
    {
        intent.addFlags(0x80000);
    }

    public static boolean startActivity(Context context, Intent intent, String s)
    {
        return startActivity(context, intent, s, true);
    }

    private static boolean startActivity(Context context, Intent intent, String s, boolean flag)
    {
        if (intent != null) goto _L2; else goto _L1
_L1:
        LogUtils.d(s, "Did not start null intent.", new Object[0]);
_L4:
        return false;
_L2:
        if (context == null)
        {
            LogUtils.w(s, "Did not start intent %s: context is null", new Object[] {
                intent
            });
            return false;
        }
        context.startActivity(intent);
        return true;
        Object obj;
        obj;
        LogUtils.e(s, "Did not start intent %s: could not resolve.", new Object[] {
            intent
        });
        if (flag)
        {
            Toast.makeText(context, 0x7f13008a, 0).show();
            return false;
        }
        continue; /* Loop/switch isn't completed */
        obj;
        LogUtils.e(s, ((Throwable) (obj)), "Did not start intent %s:", new Object[] {
            intent
        });
        if (flag)
        {
            Toast.makeText(context, 0x7f1301e2, 0).show();
            return false;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static boolean startActivityForResult(Activity activity, Intent intent)
    {
        try
        {
            activity.startActivityForResult(intent, 0);
        }
        // Misplaced declaration of an exception variable
        catch (Intent intent)
        {
            Toast.makeText(activity, 0x7f1301ab, 0).show();
            LogUtils.e(TAG, intent, "Unable to find activity for intent", new Object[0]);
            return false;
        }
        return true;
    }

    public static boolean startActivityForUri(Context context, Uri uri, String s)
    {
        Object obj = null;
        Intent intent = obj;
        if (context != null)
        {
            intent = obj;
            if (uri != null)
            {
                intent = new Intent("android.intent.action.VIEW", uri);
                intent.putExtra("com.android.browser.application_id", context.getPackageName());
                intent.addFlags(0x80000);
            }
        }
        return startActivity(context, intent, s, true);
    }

    public static boolean startActivityForUrl(Context context, String s, String s1)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            s = Uri.parse(s);
        }
        return startActivityForUri(context, s, s1);
    }

    public static boolean startActivityForUrlWithApp(Context context, String s, String s1, Bundle bundle, String s2, String s3)
    {
        s = new Intent("android.intent.action.VIEW", Uri.parse(s));
        for (bundle = context.getPackageManager().queryIntentActivities(s, 0).iterator(); bundle.hasNext();)
        {
            ActivityInfo activityinfo = ((ResolveInfo)bundle.next()).activityInfo;
            if (s1.equals(activityinfo.packageName))
            {
                s.setComponent(new ComponentName(activityinfo.packageName, activityinfo.name));
                s.setPackage(activityinfo.packageName);
                if (!TextUtils.isEmpty(s2))
                {
                    IntentUtils.addAccountDataToIntent(context, s, s2);
                }
                return startActivity(context, s, s3, false);
            }
        }

        return false;
    }

}
