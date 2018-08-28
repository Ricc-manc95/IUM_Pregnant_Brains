// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.accounts;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

public interface AccountManager
{

    public abstract String getAccountId(String s);

    public abstract List getAccountsNames();

    public abstract ListenableFuture getOAuthTokenAsync(String s, String s1);
}
