// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.Context;
import java.util.Map;

public interface ObsoleteDataCleanerContract
{

    public abstract void removeAllDataForAccount(Account account, Context context, ContentProviderClient contentproviderclient);

    public abstract void removeAllObsoleteDataIfAccountsChanged(Context context, ContentProviderClient contentproviderclient);

    public abstract void removeObsoleteCalendarData(Account account, Context context, long l, String s);

    public abstract void removeObsoleteEventsData(Account account, Context context, Map map);

    public abstract void removeObsoleteEventsData(Context context, long l, Map map);
}
