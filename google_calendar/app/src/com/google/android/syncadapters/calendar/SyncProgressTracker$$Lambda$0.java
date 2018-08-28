// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.support.v4.util.Pair;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            SyncProgressTracker

final class arg._cls1
    implements Predicate
{

    private final Account arg$1;

    public final boolean apply(Object obj)
    {
        return SyncProgressTracker.lambda$isPendingAccountSync$0$SyncProgressTracker(arg$1, (Pair)obj);
    }

    (Account account)
    {
        arg$1 = account;
    }
}
