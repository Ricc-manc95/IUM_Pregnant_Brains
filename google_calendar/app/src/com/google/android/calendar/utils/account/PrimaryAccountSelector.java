// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.account;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;

// Referenced classes of package com.google.android.calendar.utils.account:
//            AccountsUtil

public final class PrimaryAccountSelector
{

    public static PrimaryAccountSelector instance;
    public Account account;

    public PrimaryAccountSelector(Context context)
    {
        recompute(context);
    }

    public final void recompute(Context context)
    {
        context = AccountsUtil.getGoogleAccounts(context);
        for (int i = 0; i < context.length; i++)
        {
            if (ContentResolver.getIsSyncable(context[i], "com.android.calendar") > 0)
            {
                account = context[i];
                return;
            }
        }

        if (context.length != 0)
        {
            account = context[0];
            return;
        } else
        {
            account = null;
            return;
        }
    }
}
