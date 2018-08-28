// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.accounts.Account;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import com.google.android.calendar.calendarlist.DrawerFragment;
import com.google.android.calendar.calendarlist.SelectCalendarsAdapter;
import com.google.android.calendar.experimental.ExperimentalDashboardActivity;
import com.google.android.calendar.timely.GoogleFeedbackUtils;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.syncadapters.calendar.SyncProgressTracker;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, CalendarController, RefreshUiManager

final class this._cls0 extends ActionBarDrawerToggle
{

    public final AllInOneCalendarActivity this$0;

    public final void onDrawerClosed(View view)
    {
        super.onDrawerClosed(view);
        invalidateOptionsMenu();
        view = drawerFragment;
        ((DrawerFragment) (view)).adapter.reorderItems();
        ((DrawerFragment) (view)).list.setSelectionAfterHeaderView();
        for (view = ((DrawerFragment) (view)).drawerClosedListeners.iterator(); view.hasNext(); ((com.google.android.calendar.calendarlist.edListener)view.next()).onDrawerClosed()) { }
        if (pendingDrawerActionId == -1) goto _L2; else goto _L1
_L1:
        Object obj;
        int i;
        obj = AllInOneCalendarActivity.this;
        i = pendingDrawerActionId;
        if (i != 0x7f100004 && i != 0x7f100022 && i != 0x7f100026 && i != 0x7f100050 && i != 0x7f100027) goto _L4; else goto _L3
_L3:
        logMenuItemSelected(i);
        switchView(i, false);
_L6:
        class .Lambda._cls0
            implements Runnable
        {

            private final AllInOneCalendarActivity._cls3 arg$1;

            public final void run()
            {
                arg$1.this$0.pendingDrawerActionId = -1;
            }

            .Lambda._cls0()
            {
                arg$1 = AllInOneCalendarActivity._cls3.this;
            }
        }

        drawerLayout.getRootView().post(new .Lambda._cls0());
_L2:
        return;
_L4:
        if (i == 0x7f100037)
        {
            logMenuItemSelected(i);
            onSearchRequested();
        } else
        if (i == 0x7f100039 || i == 0x7f10017f)
        {
            CalendarController.launchSettings(((Activity) (obj)));
        } else
        if (i == 0x7f100020)
        {
            GoogleFeedbackUtils.launchHelpAndFeedback(((Activity) (obj)), ((Activity) (obj)).getString(0x7f13015a), Integer.valueOf(0x7f100114));
        } else
        if (i == 0x7f100012)
        {
            view = new Bundle(8);
            view.putBoolean("force", true);
            view.putBoolean("expedited", true);
            view.putBoolean("do_not_retry", true);
            view.putBoolean("sync_extra_get_settings", true);
            view.putBoolean("sync_extra_get_recent_notifications", true);
            view.putBoolean("sync_extra_get_default_notifications", true);
            view.putBoolean("sync_extra_check_consistency", true);
            obj = AccountsUtil.getGoogleAccounts(((android.content.Context) (obj)));
            int j = obj.length;
            i = 0;
            while (i < j) 
            {
                Account account = obj[i];
                view.putString("feed_internal", account.name);
                if (RefreshUiManager.instance == null)
                {
                    RefreshUiManager.instance = new RefreshUiManager();
                }
                SyncProgressTracker.getInstance().addPendingSync(account, view);
                ContentResolver.requestSync(account, "com.android.calendar", view);
                i++;
            }
        } else
        if (i == 0x7f100014)
        {
            view = new Bundle(1);
            view.putBoolean("db_dump_from_drawer", true);
            GoogleFeedbackUtils.launchGoogleFeedback(((Activity) (obj)), view);
        } else
        if (i == 0x7f100013)
        {
            ((Activity) (obj)).startActivity(new Intent(((android.content.Context) (obj)), com/google/android/calendar/experimental/ExperimentalDashboardActivity));
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void onDrawerOpened(View view)
    {
        super.onDrawerOpened(view);
        invalidateOptionsMenu();
    }

    public final void onDrawerSlide$51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0(float f)
    {
    }

    shboardActivity(Activity activity, DrawerLayout drawerlayout, int i, int j)
    {
        this$0 = AllInOneCalendarActivity.this;
        super(activity, drawerlayout, i, j);
    }
}
