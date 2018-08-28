// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.calendarlist.DrawerListAdapter;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.android.calendar.time.clock.Clock;
import java.util.ArrayList;

public final class AllCalendarsHiddenDialogManager extends DataSetObserver
    implements com.google.android.calendar.calendarlist.DrawerFragment.OnDrawerClosedListener
{

    private final DrawerListAdapter adapter;
    private boolean allCalendarsHidden;
    private boolean alreadyCheckedOnChange;
    private final Context context;

    public AllCalendarsHiddenDialogManager(Context context1, DrawerListAdapter drawerlistadapter)
    {
        alreadyCheckedOnChange = false;
        allCalendarsHidden = false;
        context = context1;
        adapter = drawerlistadapter;
        adapter.registerDataSetObserver(this);
    }

    private final void checkIfShouldShowDialog()
    {
label0:
        {
            boolean flag = false;
            if (allCalendarsHidden)
            {
                long l2 = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getLong("com.android.calendar.timely.allCalendarsHidden.lastChecked", -1L);
                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                if (l - l2 > 0x5265c00L)
                {
                    flag = true;
                }
                if (flag)
                {
                    break label0;
                }
            }
            return;
        }
        Context context1 = context;
        AnalyticsLogger analyticslogger;
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        SharedPrefs.setSharedPreference(context1, "com.android.calendar.timely.allCalendarsHidden.lastChecked", l1);
        context1 = context;
        (new android.app.AlertDialog.Builder(context1)).setTitle(context1.getString(0x7f13035b)).setMessage(context1.getString(0x7f13035a)).setPositiveButton(context1.getString(0x7f130359), new _cls1()).create().show();
        context1 = context;
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context1, "sync_warnings", "all_calendars_hidden_dialog", "displayed", null);
            return;
        }
    }

    public final void onChanged()
    {
        Object obj = adapter;
        if (((DrawerListAdapter) (obj)).mItems != null && ((DrawerListAdapter) (obj)).mItems.size() != 0) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L12:
        allCalendarsHidden = flag;
        if (!alreadyCheckedOnChange)
        {
            alreadyCheckedOnChange = true;
            checkIfShouldShowDialog();
        }
        return;
_L2:
        int i;
        int j;
        obj = (ArrayList)((DrawerListAdapter) (obj)).mItems;
        j = ((ArrayList) (obj)).size();
        i = 0;
_L9:
        Object obj1;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = ((ArrayList) (obj)).get(i);
        i++;
        obj1 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)obj1;
        ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj1)).getType();
        JVM INSTR tableswitch 1 4: default 120
    //                   1 123
    //                   2 120
    //                   3 139
    //                   4 139;
           goto _L3 _L4 _L3 _L5 _L5
_L3:
        break; /* Loop/switch isn't completed */
_L5:
        continue; /* Loop/switch isn't completed */
_L4:
        if (!((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)obj1).isVisible) goto _L7; else goto _L6
_L7:
        if (true) goto _L9; else goto _L8
_L6:
        flag = false;
        continue; /* Loop/switch isn't completed */
        if (!((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)obj1).areVisible) goto _L9; else goto _L10
_L10:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L8:
        flag = true;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final void onDrawerClosed()
    {
        checkIfShouldShowDialog();
    }

    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            dialoginterface.dismiss();
        }

        _cls1()
        {
        }
    }

}
