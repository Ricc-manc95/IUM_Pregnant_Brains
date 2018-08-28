// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;


public final class AccountSyncState
{

    public static final AccountSyncState COMPLETE = new AccountSyncState(0, 0x7f130190);
    public static final AccountSyncState DISABLED = new AccountSyncState(0x7f020239, 0x7f130191);
    public static final AccountSyncState ENABLED = new AccountSyncState(0, 0);
    public static final AccountSyncState ERROR = new AccountSyncState(0, 0x7f130192);
    public static final AccountSyncState SYNCING = new AccountSyncState(0x7f02023a, 0x7f130193);
    public final int iconId;
    public final int textId;

    private AccountSyncState(int i, int j)
    {
        iconId = i;
        textId = j;
    }

}
