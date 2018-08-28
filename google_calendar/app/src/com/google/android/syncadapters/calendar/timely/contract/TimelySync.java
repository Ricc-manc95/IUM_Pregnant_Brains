// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.contract;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.Entity;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.apiary.ParseException;
import com.google.android.syncadapters.calendar.CalendarSyncInfo;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.contract:
//            TimelyEventData

public interface TimelySync
{

    public abstract void addAttachmentsToEntry(HashMap hashmap, String s, Long long1, Event event);

    public abstract void addConferenceDetailsToEvent(Entity entity, HashMap hashmap, boolean flag, Event event, String s, Account account);

    public abstract void addParticipantStatusToEntry(HashMap hashmap, String s, Long long1, Event event);

    public abstract void addTitleContactAnnotationsToEntry(HashMap hashmap, String s, Long long1, Event event);

    public abstract void apiaryEventToTimelyExtras(Event event, TimelyEventData timelyeventdata);

    public abstract void fillSyncInfo(CalendarSyncInfo calendarsyncinfo, String s);

    public abstract List getEventAttendees(String s, long l);

    public abstract void getTimelySettings(Account account)
        throws IOException;

    public abstract Uri insertOrUpdateEventData(String s, long l, TimelyEventData timelyeventdata);

    public abstract void onAfterUpsync(Account account, ContentProviderClient contentproviderclient)
        throws RemoteException;

    public abstract void onInitializeSync(Account account);

    public abstract void parseDefaultNotifications(CalendarListEntry calendarlistentry, long l, Account account, boolean flag);

    public abstract void prefetchTimelyTopLevelSyncRequests(Bundle bundle)
        throws IOException;

    public abstract void processNonUpdateFlags(Account account, Bundle bundle, ContentProviderClient contentproviderclient)
        throws RemoteException, IOException, ParseException;

    public abstract boolean processUpdateFlags(Account account, Bundle bundle, ContentProviderClient contentproviderclient)
        throws IOException, RemoteException, ParseException;

    public abstract boolean removeTimelyEventData(String s, long l);

    public abstract void saveTimelyDataForEvent(ArrayList arraylist, Event event, Account account, ContentProviderClient contentproviderclient, long l, String s)
        throws ParseException, RemoteException;

    public abstract void setLocalBirthdaySettings(Account account, ContentProviderClient contentproviderclient);

    public abstract void updateCalendarsSettings(Account account, List list);
}
