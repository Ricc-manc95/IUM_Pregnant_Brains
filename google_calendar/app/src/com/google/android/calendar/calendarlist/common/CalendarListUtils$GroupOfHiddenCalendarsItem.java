// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist.common;

import android.accounts.Account;
import java.util.ArrayList;

public final class priority extends priority
{

    public final ArrayList calendars = new ArrayList();

    public final int getType()
    {
        return 5;
    }

    public final int getViewType()
    {
        return 2;
    }

    public (Account account)
    {
        this.account = account;
        priority = 4;
    }
}
