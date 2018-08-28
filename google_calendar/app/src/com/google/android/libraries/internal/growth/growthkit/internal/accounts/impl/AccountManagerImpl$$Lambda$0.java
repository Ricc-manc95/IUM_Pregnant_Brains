// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl:
//            AccountManagerImpl

final class arg._cls3
    implements Callable
{

    private final AccountManagerImpl arg$1;
    private final String arg$2;
    private final String arg$3;

    public final Object call()
    {
        return arg$1.lambda$getOAuthTokenAsync$0$AccountManagerImpl(arg$2, arg$3);
    }

    (AccountManagerImpl accountmanagerimpl, String s, String s1)
    {
        arg$1 = accountmanagerimpl;
        arg$2 = s;
        arg$3 = s1;
    }
}
