// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.utils.snackbar.SnackbarFeedbackUtils;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            DrawerSyncUIManager, AccountSyncState, DrawerListAdapter

final class arg._cls2
    implements Consumer
{

    private final DrawerSyncUIManager arg$1;
    private final Account arg$2;

    public final void accept(Object obj)
    {
        DrawerSyncUIManager drawersyncuimanager;
        drawersyncuimanager = arg$1;
        Account account = arg$2;
        obj = (Boolean)obj;
        AccountSyncState accountsyncstate = (AccountSyncState)drawersyncuimanager.stateMap.get(account);
        if (((Boolean) (obj)).booleanValue())
        {
            obj = AccountSyncState.ERROR;
        } else
        {
            obj = AccountSyncState.COMPLETE;
        }
        if (!AccountSyncState.SYNCING.equals(accountsyncstate)) goto _L2; else goto _L1
_L1:
        drawersyncuimanager.stateMap.put(account, obj);
_L4:
        drawersyncuimanager.updateDrawerOnUiThread();
        return;
_L2:
        if (((AccountSyncState) (obj)).textId != 0)
        {
            Context context = drawersyncuimanager.adapter.mContext;
            SnackbarFeedbackUtils.showSnackbarFeedback(context, context.getString(((AccountSyncState) (obj)).textId), true, null, 0);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    (DrawerSyncUIManager drawersyncuimanager, Account account)
    {
        arg$1 = drawersyncuimanager;
        arg$2 = account;
    }
}
