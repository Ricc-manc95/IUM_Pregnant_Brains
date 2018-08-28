// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.customchooser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.google.android.calendar.utils.intent.IntentUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SendEmailChooserHelper
{

    public static SendEmailChooserHelper instance = new SendEmailChooserHelper();

    protected SendEmailChooserHelper()
    {
    }

    public final Intent createCustomChooser(Context context, String s, Intent intent, String s1)
    {
        ArrayList arraylist;
        PackageManager packagemanager;
        List list;
        int i;
        packagemanager = context.getPackageManager();
        list = packagemanager.queryIntentActivities(intent, 0);
        if (list.isEmpty())
        {
            return Intent.createChooser(intent, s);
        }
        Collections.sort(list, new android.content.pm.ResolveInfo.DisplayNameComparator(packagemanager));
        arraylist = new ArrayList();
        i = 0;
_L9:
        if (i >= list.size()) goto _L2; else goto _L1
_L1:
        ResolveInfo resolveinfo;
        ActivityInfo activityinfo;
        Intent intent1;
        resolveinfo = (ResolveInfo)list.get(i);
        activityinfo = resolveinfo.activityInfo;
        intent1 = new Intent(intent);
        intent1.setComponent(new ComponentName(activityinfo.packageName, activityinfo.name));
        intent1.setPackage(activityinfo.packageName);
        if (s1 == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        String s2 = activityinfo.packageName;
        if ("com.google.android.gm" == s2 || "com.google.android.gm" != null && "com.google.android.gm".equals(s2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L6; else goto _L5
_L5:
        s2 = activityinfo.packageName;
        if ("com.google.android.gm.lite" == s2 || "com.google.android.gm.lite" != null && "com.google.android.gm.lite".equals(s2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L7; else goto _L6
_L6:
        intent1.putExtra("fromAccountString", s1);
_L4:
        arraylist.add(new LabeledIntent(intent1, activityinfo.packageName, resolveinfo.loadLabel(packagemanager), resolveinfo.icon));
        i++;
        continue; /* Loop/switch isn't completed */
_L7:
        if ("com.google.android.apps.inbox".equals(activityinfo.packageName))
        {
            IntentUtils.addAccountDataToIntent(context, intent1, s1);
        }
        if (true) goto _L4; else goto _L2
_L2:
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            context = new Intent();
        } else
        {
            context = (Intent)arraylist.remove(arraylist.size() - 1);
        }
        context = Intent.createChooser(context, s);
        context.putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[])arraylist.toArray(new LabeledIntent[arraylist.size()]));
        return context;
        if (true) goto _L9; else goto _L8
_L8:
    }

}
