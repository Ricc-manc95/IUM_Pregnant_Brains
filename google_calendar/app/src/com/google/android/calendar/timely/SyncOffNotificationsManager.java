// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.syncadapters.calendar.SyncProgressTracker;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely:
//            BottomSheet, SyncOffNotification

public final class SyncOffNotificationsManager
    implements com.google.android.syncadapters.calendar.SyncProgressTracker.SyncProgressCallback
{

    public static SyncOffNotificationsManager instance;
    public Account account;
    public Set allAccounts;
    public Context context;
    public boolean isShowing;
    public boolean needsRefresh;
    public SyncOffNotification notification;
    public boolean shouldNotShow;
    public int showReason;
    public Set syncOffAccounts;
    public String text;

    public SyncOffNotificationsManager(Context context1)
    {
        isShowing = false;
        needsRefresh = false;
        syncOffAccounts = new HashSet();
        allAccounts = new HashSet();
        showReason = 0;
        shouldNotShow = false;
        context = context1.getApplicationContext();
    }

    public final void checkDismissCycle()
    {
        if (allAccounts.size() != 0)
        {
            Iterator iterator = syncOffAccounts.iterator();
            int i;
            for (i = 0; iterator.hasNext(); i = ((Account)iterator.next()).hashCode() + i) { }
            if (context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("com.android.calendar.timely.syncOffNotification.accountsHash", 0) != i)
            {
                SharedPrefs.setSharedPreference(context, "com.android.calendar.timely.syncOffNotification.accountsHash", i);
                SharedPrefs.removeSharedPreference(context, "com.android.calendar.timely.syncOffNotification.numDismisses");
                return;
            }
        }
    }

    public final void onAppOpen()
    {
        boolean flag;
        if (notification != null && !allAccounts.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            needsRefresh = true;
            return;
        }
        if (!isShowing && shouldShowOnAppOpen())
        {
            Object obj;
            boolean flag1;
            if (!ContentResolver.getMasterSyncAutomatically())
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                obj = context.getString(0x7f13046e);
            } else
            {
                int i = syncOffAccounts.size();
                int j = allAccounts.size();
                if (i == 0)
                {
                    obj = null;
                } else
                if (i == 1)
                {
                    if (j == 1)
                    {
                        obj = context.getString(0x7f13046f);
                    } else
                    {
                        obj = (Account)syncOffAccounts.iterator().next();
                        obj = context.getString(0x7f13046c, new Object[] {
                            ((Account) (obj)).name
                        });
                    }
                } else
                {
                    obj = context.getString(0x7f13046d);
                }
            }
            text = ((String) (obj));
            showReason = 1;
            if (syncOffAccounts.size() == 1)
            {
                obj = (Account)syncOffAccounts.iterator().next();
            } else
            {
                obj = null;
            }
            account = ((Account) (obj));
            notification.show();
        }
        needsRefresh = false;
    }

    public final void onSyncCompleted(boolean flag)
    {
        synchronized (SyncProgressTracker.callbacks)
        {
            SyncProgressTracker.callbacks.remove(this);
        }
        class .Lambda._cls0
            implements Runnable
        {

            private final SyncOffNotificationsManager arg$1;
            private final int arg$2;

            public final void run()
            {
                SyncOffNotificationsManager syncoffnotificationsmanager = arg$1;
                int j = arg$2;
                Toast.makeText(syncoffnotificationsmanager.context, j, 0).show();
            }

            .Lambda._cls0(int i)
            {
                arg$1 = SyncOffNotificationsManager.this;
                arg$2 = i;
            }
        }

        int i;
        if (flag)
        {
            i = 0x7f130192;
        } else
        {
            i = 0x7f130190;
        }
        (new Handler(Looper.getMainLooper())).post(new .Lambda._cls0(i));
        return;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onSyncPending()
    {
    }

    public final boolean shouldShowOnAppOpen()
    {
        boolean flag;
        boolean flag1 = true;
        checkDismissCycle();
        if (context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("com.android.calendar.timely.syncOffNotification.numDismisses", 0) > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        if (!ContentResolver.getMasterSyncAutomatically())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || syncOffAccounts.size() != 0) goto _L3; else goto _L2
_L2:
        flag1 = false;
_L5:
        return flag1;
_L3:
        long l1 = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getLong("com.android.calendar.timely.syncOffNotification.lastShown", -1L);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (l - l1 <= 0x3dcc500L)
        {
            return false;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }
}
