// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.accounts.Account;
import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListFilterOptions, CalendarDescriptor, CalendarListEntryModifications

public interface CalendarListClient
{

    public abstract ListenableFuture count(CalendarListFilterOptions calendarlistfilteroptions);

    public abstract void initialize(Context context);

    public abstract ListenableFuture list(CalendarListFilterOptions calendarlistfilteroptions);

    public abstract ListenableFuture read(CalendarDescriptor calendardescriptor);

    public abstract ListenableFuture subscribe(Account account, String s);

    public abstract ListenableFuture unsubscribe(Account account, String s);

    public abstract ListenableFuture update(CalendarListEntryModifications calendarlistentrymodifications);
}
