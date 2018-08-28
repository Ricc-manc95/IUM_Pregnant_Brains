// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.utils.account.AccountsUtil;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFactory

public final class arg._cls2
    implements Consumer
{

    private final WhatsNewFactory arg$1;
    private final Activity arg$2;

    public final void accept(Object obj)
    {
        Activity activity;
        Object obj1;
label0:
        {
label1:
            {
                obj1 = arg$1;
                activity = arg$2;
                obj = (android.accounts.Account[])obj;
                if (!activity.isDestroyed())
                {
                    if (obj.length != 0)
                    {
                        break label0;
                    }
                    boolean flag;
                    if (!((UserManager)activity.getSystemService("user")).hasUserRestriction("no_modify_accounts"))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break label1;
                    }
                    LogUtils.d(WhatsNewFactory.TAG, "User is not allowed to create accounts.", new Object[0]);
                }
                return;
            }
            obj = new <init>(((WhatsNewFactory) (obj1)), activity);
            obj1 = AccountManager.get(activity);
            Bundle bundle = new Bundle();
            bundle.putCharSequence("introMessage", activity.getResources().getString(0x7f130141));
            bundle.putBoolean("allowSkip", false);
            ((AccountManager) (obj1)).addAccount("com.google", "com.android.calendar", AccountsUtil.GOOGLE_CALENDAR_SYNC_FEATURE, bundle, activity, ((android.accounts.AccountManagerCallback) (obj)), null);
            return;
        }
        ((WhatsNewFactory) (obj1)).processAccountChanges(activity, ((android.accounts.Account []) (obj)));
    }

    public (WhatsNewFactory whatsnewfactory, Activity activity)
    {
        arg$1 = whatsnewfactory;
        arg$2 = activity;
    }
}
