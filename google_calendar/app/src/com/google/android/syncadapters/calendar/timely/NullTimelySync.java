// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.Entity;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.apiary.ParseException;
import com.google.android.syncadapters.calendar.CalendarSyncInfo;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.android.syncadapters.calendar.timely.contract.TimelySync;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public final class NullTimelySync
    implements TimelySync
{

    public NullTimelySync()
    {
    }

    public final void addAttachmentsToEntry(HashMap hashmap, String s, Long long1, Event event)
    {
    }

    public final void addConferenceDetailsToEvent(Entity entity, HashMap hashmap, boolean flag, Event event, String s, Account account)
    {
    }

    public final void addParticipantStatusToEntry(HashMap hashmap, String s, Long long1, Event event)
    {
    }

    public final void addTitleContactAnnotationsToEntry(HashMap hashmap, String s, Long long1, Event event)
    {
    }

    public final void apiaryEventToTimelyExtras(Event event, TimelyEventData timelyeventdata)
    {
    }

    public final void fillSyncInfo(CalendarSyncInfo calendarsyncinfo, String s)
    {
        calendarsyncinfo.defaultAllDayReminders = new ArrayList();
        calendarsyncinfo.defaultReminders = new ArrayList();
    }

    public final List getEventAttendees(String s, long l)
    {
        return Collections.emptyList();
    }

    public final void getTimelySettings(Account account)
        throws IOException
    {
    }

    public final Uri insertOrUpdateEventData(String s, long l, TimelyEventData timelyeventdata)
    {
        return null;
    }

    public final void onAfterUpsync(Account account, ContentProviderClient contentproviderclient)
        throws RemoteException
    {
    }

    public final void onInitializeSync(Account account)
    {
    }

    public final void parseDefaultNotifications(CalendarListEntry calendarlistentry, long l, Account account, boolean flag)
    {
    }

    public final void prefetchTimelyTopLevelSyncRequests(Bundle bundle)
        throws IOException
    {
    }

    public final void processNonUpdateFlags(Account account, Bundle bundle, ContentProviderClient contentproviderclient)
        throws RemoteException, IOException, ParseException
    {
    }

    public final boolean processUpdateFlags(Account account, Bundle bundle, ContentProviderClient contentproviderclient)
        throws IOException, RemoteException, ParseException
    {
        return false;
    }

    public final boolean removeTimelyEventData(String s, long l)
    {
        return false;
    }

    public final void saveTimelyDataForEvent(ArrayList arraylist, Event event, Account account, ContentProviderClient contentproviderclient, long l, String s)
        throws ParseException, RemoteException
    {
    }

    public final void setLocalBirthdaySettings(Account account, ContentProviderClient contentproviderclient)
    {
    }

    public final void updateCalendarsSettings(Account account, List list)
    {
    }
}
