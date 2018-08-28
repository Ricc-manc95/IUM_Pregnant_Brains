// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.content.ContentResolver;
import android.os.AsyncTask;
import com.google.android.calendar.sync.SyncUtils;
import com.google.android.syncadapters.calendar.SyncProgressTracker;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely:
//            SyncOffNotificationsManager

final class val.accounts extends AsyncTask
{

    private final SyncOffNotificationsManager this$0;
    private final Set val$accounts;

    private final transient Void doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPFAPNMIP1R0()
    {
        SyncOffNotificationsManager syncoffnotificationsmanager = SyncOffNotificationsManager.this;
        synchronized (SyncProgressTracker.callbacks)
        {
            SyncProgressTracker.callbacks.add(syncoffnotificationsmanager);
        }
        obj = SyncOffNotificationsManager.this;
        boolean flag;
        if (!ContentResolver.getMasterSyncAutomatically())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            ContentResolver.setMasterSyncAutomatically(true);
        }
        obj = val$accounts.iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            Account account = (Account)((Iterator) (obj)).next();
            if (!SyncUtils.getSyncAutomatically(account))
            {
                SyncUtils.enableAutomaticSyncAndSyncNow(context, account, true, null);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_106;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        return null;
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        return doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPFAPNMIP1R0();
    }

    ()
    {
        this$0 = final_syncoffnotificationsmanager;
        val$accounts = Set.this;
        super();
    }
}
