// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.account;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.calendar.utils.account:
//            AccountsUtil

final class arg._cls1
    implements AccountManagerCallback
{

    private final SettableFuture arg$1;

    public final void run(AccountManagerFuture accountmanagerfuture)
    {
        AccountsUtil.lambda$getGoogleAccounts$0$AccountsUtil(arg$1, accountmanagerfuture);
    }

    (SettableFuture settablefuture)
    {
        arg$1 = settablefuture;
    }
}
