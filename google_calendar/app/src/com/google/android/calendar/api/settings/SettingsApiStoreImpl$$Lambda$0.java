// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsApiStoreImpl

final class arg._cls1
    implements Callable
{

    private final Account arg$1;

    public final Object call()
    {
        return SettingsApiStoreImpl.lambda$read$0$SettingsApiStoreImpl(arg$1);
    }

    (Account account)
    {
        arg$1 = account;
    }
}
