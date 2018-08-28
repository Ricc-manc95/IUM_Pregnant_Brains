// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.account.AccountsUtil;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFactory

final class arg._cls2
    implements AccountManagerCallback
{

    private final WhatsNewFactory arg$1;
    private final Activity arg$2;

    public final void run(AccountManagerFuture accountmanagerfuture)
    {
        Activity activity;
        WhatsNewFactory whatsnewfactory;
        whatsnewfactory = arg$1;
        activity = arg$2;
        if (accountmanagerfuture.isCancelled())
        {
            activity.finish();
            return;
        }
        Object obj;
        obj = (Bundle)accountmanagerfuture.getResult();
        if (((Bundle) (obj)).getBoolean("setupSkipped"))
        {
            activity.finish();
            return;
        }
          goto _L1
_L6:
        LogUtils.w(WhatsNewFactory.TAG, accountmanagerfuture, "Exception in the AccountManagerCallback", new Object[0]);
_L3:
        activity.finish();
        return;
_L1:
        if (!((Bundle) (obj)).containsKey("authAccount")) goto _L3; else goto _L2
_L2:
        accountmanagerfuture = ((Bundle) (obj)).getString("authAccount");
        obj = ((Bundle) (obj)).getString("accountType");
        if (accountmanagerfuture == null || obj == null) goto _L3; else goto _L4
_L4:
        accountmanagerfuture = AccountsUtil.getGoogleAccounts(activity);
        if (!"com.google".equals(obj) || accountmanagerfuture.length == 0)
        {
            LogUtils.wtf(WhatsNewFactory.TAG, "Account added, but it's not a Google account. accountType = '%s', googleAccounts.length = %s", new Object[] {
                obj, Integer.valueOf(accountmanagerfuture.length)
            });
            activity.finish();
            return;
        }
        try
        {
            whatsnewfactory.processAccountChanges(activity, accountmanagerfuture);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (AccountManagerFuture accountmanagerfuture) { }
        // Misplaced declaration of an exception variable
        catch (AccountManagerFuture accountmanagerfuture) { }
        // Misplaced declaration of an exception variable
        catch (AccountManagerFuture accountmanagerfuture) { }
        if (true) goto _L6; else goto _L5
_L5:
    }

    (WhatsNewFactory whatsnewfactory, Activity activity)
    {
        arg$1 = whatsnewfactory;
        arg$2 = activity;
    }
}
