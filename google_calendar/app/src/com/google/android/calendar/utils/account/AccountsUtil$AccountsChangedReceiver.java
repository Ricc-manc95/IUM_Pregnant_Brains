// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.google.android.calendar.utils.account:
//            AccountsUtil

final class  extends BroadcastReceiver
{

    public final void onReceive(Context context, Intent intent)
    {
        AccountsUtil.cachedGoogleAccounts = null;
    }

    ()
    {
    }
}
