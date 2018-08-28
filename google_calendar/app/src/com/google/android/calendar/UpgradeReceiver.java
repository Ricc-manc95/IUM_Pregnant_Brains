// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar:
//            SyncUpgradeReceiver, AllPrefsUpgradeReceiver

public abstract class UpgradeReceiver extends BroadcastReceiver
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/UpgradeReceiver);
    public static final List UPGRADE_RECEIVERS;

    public UpgradeReceiver()
    {
    }

    protected abstract void doUpgrade(Context context);

    public void onReceive(Context context, Intent intent)
    {
        tryUpgradeAndDisableReceiver(context);
    }

    final void tryUpgradeAndDisableReceiver(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        ComponentName componentname = new ComponentName(context, getClass());
        boolean flag;
        if (packagemanager.getComponentEnabledSetting(componentname) == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return;
        } else
        {
            doUpgrade(context);
            packagemanager.setComponentEnabledSetting(componentname, 2, 1);
            return;
        }
    }

    static 
    {
        ArrayList arraylist = new ArrayList();
        UPGRADE_RECEIVERS = arraylist;
        arraylist.add(com/google/android/calendar/SyncUpgradeReceiver);
        UPGRADE_RECEIVERS.add(com/google/android/calendar/AllPrefsUpgradeReceiver);
    }
}
