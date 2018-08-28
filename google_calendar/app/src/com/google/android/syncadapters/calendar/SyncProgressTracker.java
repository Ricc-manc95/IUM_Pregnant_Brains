// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v4.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.common.collect.Iterators;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            Utilities

public class SyncProgressTracker
{
    public static interface SyncProgressCallback
    {

        public abstract void onSyncCompleted(boolean flag);

        public abstract void onSyncPending();
    }


    private static final String TAG = com/google/android/syncadapters/calendar/SyncProgressTracker.getSimpleName();
    public static final Set callbacks = new HashSet();
    private static SyncProgressTracker instance;
    private boolean hasErrorsToReport;
    private Map pendingIndividualSyncs;
    private Set pendingSyncs;

    public SyncProgressTracker()
    {
        pendingSyncs = new HashSet();
        pendingIndividualSyncs = new HashMap();
    }

    public static SyncProgressTracker getInstance()
    {
        com/google/android/syncadapters/calendar/SyncProgressTracker;
        JVM INSTR monitorenter ;
        SyncProgressTracker syncprogresstracker;
        if (instance == null)
        {
            instance = new SyncProgressTracker();
        }
        syncprogresstracker = instance;
        com/google/android/syncadapters/calendar/SyncProgressTracker;
        JVM INSTR monitorexit ;
        return syncprogresstracker;
        Exception exception;
        exception;
        throw exception;
    }

    private static String getSyncTarget(Bundle bundle)
    {
        String s1 = bundle.getString("feed");
        String s = s1;
        if (s1 == null)
        {
            s = bundle.getString("feed_internal");
        }
        s1 = s;
        if (s != null)
        {
            s1 = Utilities.parseFeedId(s);
        }
        s = s1;
        if (s1 != null)
        {
            s = s1;
            if (bundle.getBoolean("only_groove"))
            {
                s = String.valueOf(s1).concat("_habits");
            }
        }
        s1 = s;
        if (s != null)
        {
            s1 = s;
            if (bundle.getBoolean("moveWindow"))
            {
                s1 = String.valueOf(s).concat("_move");
            }
        }
        return s1;
    }

    static final boolean lambda$isPendingAccountSync$0$SyncProgressTracker(Account account, Pair pair)
    {
        pair = ((Pair) (pair.first));
        return account == pair || account != null && account.equals(pair);
    }

    private final void logTotalSyncs()
    {
        this;
        JVM INSTR monitorenter ;
        LogUtils.v(TAG, "Num Syncs: %d", new Object[] {
            Integer.valueOf(pendingSyncs.size())
        });
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void addPendingIndividualSync(Account account, SyncProgressCallback syncprogresscallback)
    {
        this;
        JVM INSTR monitorenter ;
        if (!"com.google".equals(account.type)) goto _L2; else goto _L1
_L1:
        int i = ContentResolver.getIsSyncable(account, "com.android.calendar");
        if (i <= 0) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L8:
        if (flag) goto _L5; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L5:
        pendingIndividualSyncs.put(account, syncprogresscallback);
        if (true) goto _L4; else goto _L6
_L6:
        account;
        throw account;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void addPendingSync(Account account, Bundle bundle)
    {
        boolean flag = true;
        boolean flag1 = false;
        this;
        JVM INSTR monitorenter ;
        if (!"com.google".equals(account.type)) goto _L2; else goto _L1
_L1:
        int j = ContentResolver.getIsSyncable(account, "com.android.calendar");
        if (j <= 0) goto _L2; else goto _L3
_L3:
        if (flag) goto _L5; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        flag = false;
          goto _L3
_L5:
        SyncProgressCallback asyncprogresscallback[];
        synchronized (callbacks)
        {
            asyncprogresscallback = (SyncProgressCallback[])callbacks.toArray(new SyncProgressCallback[callbacks.size()]);
        }
        j = asyncprogresscallback.length;
        int i = ((flag1) ? 1 : 0);
_L7:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        asyncprogresscallback[i].onSyncPending();
        i++;
        if (true) goto _L7; else goto _L6
        account;
        set;
        JVM INSTR monitorexit ;
        throw account;
        account;
        this;
        JVM INSTR monitorexit ;
        throw account;
_L6:
        LogUtils.v(TAG, "Adding sync: %s %s", new Object[] {
            account, bundle
        });
        pendingSyncs.add(new Pair(account, getSyncTarget(bundle)));
        logTotalSyncs();
          goto _L4
    }

    public final boolean hasPendingSyncs()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = pendingSyncs.isEmpty();
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean isPendingAccountSync(Account account)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        Set set = pendingSyncs;
        class .Lambda._cls0
            implements Predicate
        {

            private final Account arg$1;

            public final boolean apply(Object obj)
            {
                return SyncProgressTracker.lambda$isPendingAccountSync$0$SyncProgressTracker(arg$1, (Pair)obj);
            }

            .Lambda._cls0(Account account)
            {
                arg$1 = account;
            }
        }

        account = new .Lambda._cls0(account);
        i = Iterators.indexOf(set.iterator(), account);
        boolean flag;
        if (i != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        account;
        throw account;
    }

    public final void removePendingSync(Account account, Bundle bundle, boolean flag)
    {
        int i = 0;
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if (!pendingIndividualSyncs.containsKey(account))
        {
            break MISSING_BLOCK_LABEL_57;
        }
        obj = (SyncProgressCallback)pendingIndividualSyncs.get(account);
        pendingIndividualSyncs.remove(account);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        ((SyncProgressCallback) (obj)).onSyncCompleted(flag);
        boolean flag1;
        obj = new Pair(account, getSyncTarget(bundle));
        flag1 = pendingSyncs.contains(obj);
        if (flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        hasErrorsToReport = hasErrorsToReport | flag;
        LogUtils.v(TAG, "Removing sync: %s %s", new Object[] {
            account, bundle
        });
        pendingSyncs.remove(obj);
        logTotalSyncs();
        if (!pendingSyncs.isEmpty()) goto _L1; else goto _L3
_L3:
        LogUtils.v(TAG, "Notify that sync is completed.", new Object[0]);
        synchronized (callbacks)
        {
            bundle = (SyncProgressCallback[])callbacks.toArray(new SyncProgressCallback[callbacks.size()]);
        }
        int j = bundle.length;
_L5:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        bundle[i].onSyncCompleted(hasErrorsToReport);
        i++;
        if (true) goto _L5; else goto _L4
        bundle;
        account;
        JVM INSTR monitorexit ;
        throw bundle;
        account;
        this;
        JVM INSTR monitorexit ;
        throw account;
_L4:
        hasErrorsToReport = false;
          goto _L1
    }

}
