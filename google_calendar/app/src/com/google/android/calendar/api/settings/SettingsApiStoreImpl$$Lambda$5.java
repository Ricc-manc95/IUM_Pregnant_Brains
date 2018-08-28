// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import com.google.common.base.Predicate;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsApiStoreImpl

final class arg._cls1
    implements Predicate
{

    private final Set arg$1;

    public final boolean apply(Object obj)
    {
        return SettingsApiStoreImpl.lambda$list$3$SettingsApiStoreImpl(arg$1, (Account)obj);
    }

    (Set set)
    {
        arg$1 = set;
    }
}
