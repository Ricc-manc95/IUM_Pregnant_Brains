// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.chromium.customtabsclient.shared;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CustomTabsHelper
{

    private static String sPackageNameToUse;

    public static String getPackageNameToUse(Context context)
    {
        if (sPackageNameToUse != null)
        {
            return sPackageNameToUse;
        }
        PackageManager packagemanager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        Object obj = packagemanager.resolveActivity(intent, 0);
        ArrayList arraylist;
        Object obj1;
        if (obj != null)
        {
            obj = ((ResolveInfo) (obj)).activityInfo.packageName;
        } else
        {
            obj = null;
        }
        obj1 = packagemanager.queryIntentActivities(intent, 0);
        arraylist = new ArrayList();
        obj1 = ((List) (obj1)).iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            ResolveInfo resolveinfo = (ResolveInfo)((Iterator) (obj1)).next();
            Intent intent1 = new Intent();
            intent1.setAction("android.support.customtabs.action.CustomTabsService");
            intent1.setPackage(resolveinfo.activityInfo.packageName);
            if (packagemanager.resolveService(intent1, 0) != null)
            {
                arraylist.add(resolveinfo.activityInfo.packageName);
            }
        } while (true);
        if (!arraylist.isEmpty()) goto _L2; else goto _L1
_L1:
        sPackageNameToUse = null;
_L4:
        return sPackageNameToUse;
_L2:
        if (arraylist.size() == 1)
        {
            sPackageNameToUse = (String)arraylist.get(0);
        } else
        if (!TextUtils.isEmpty(((CharSequence) (obj))) && !hasSpecializedHandlerIntents(context, intent) && arraylist.contains(obj))
        {
            sPackageNameToUse = ((String) (obj));
        } else
        if (arraylist.contains("com.android.chrome"))
        {
            sPackageNameToUse = "com.android.chrome";
        } else
        if (arraylist.contains("com.chrome.beta"))
        {
            sPackageNameToUse = "com.chrome.beta";
        } else
        if (arraylist.contains("com.chrome.dev"))
        {
            sPackageNameToUse = "com.chrome.dev";
        } else
        if (arraylist.contains("com.google.android.apps.chrome"))
        {
            sPackageNameToUse = "com.google.android.apps.chrome";
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static boolean hasSpecializedHandlerIntents(Context context, Intent intent)
    {
        context = context.getPackageManager().queryIntentActivities(intent, 64);
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        if (context.size() == 0)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        context = context.iterator();
_L2:
        IntentFilter intentfilter;
        do
        {
            if (!context.hasNext())
            {
                break MISSING_BLOCK_LABEL_96;
            }
            intent = (ResolveInfo)context.next();
            intentfilter = ((ResolveInfo) (intent)).filter;
        } while (intentfilter == null);
        if (intentfilter.countDataAuthorities() == 0 || intentfilter.countDataPaths() == 0) goto _L2; else goto _L1
_L1:
        intent = ((ResolveInfo) (intent)).activityInfo;
        if (intent != null)
        {
            return true;
        }
          goto _L2
        context;
        Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        return false;
        return false;
    }
}
