// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.net;

import android.content.Context;
import android.os.RemoteException;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.android.emailcommon.provider.RecipientAvailability;
import com.android.emailcommon.service.EmailServiceProxy;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.FindTimeAttendee;
import com.google.android.calendar.timely.FindTimeTimeframe;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.android.calendar.timely.OmittedAttendee;
import com.google.android.calendar.timely.TimelineAttendeeEvent;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineExchangeAttendeeEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineSuggestion;
import com.google.android.calendar.timely.net.BaseClientFragment;
import com.google.android.calendar.utils.exchange.ExchangeUtil;
import com.google.android.calendar.utils.network.NetworkUtil;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely.findatime.net:
//            FindTimeClient

public class FindTimeExchangeClient extends BaseClientFragment
    implements FindTimeClient
{
    static final class AttendeePenaltyComparator
        implements Comparator
    {

        private final String organizerEmail;

        public final int compare(Object obj, Object obj1)
        {
            obj = (Pair)obj;
            obj1 = (Pair)obj1;
            FindTimeAttendee findtimeattendee = (FindTimeAttendee)((Pair) (obj)).first;
            FindTimeAttendee findtimeattendee1 = (FindTimeAttendee)((Pair) (obj1)).first;
            if (organizerEmail.equals(findtimeattendee.email))
            {
                return -1;
            }
            if (organizerEmail.equals(findtimeattendee1.email))
            {
                return 1;
            } else
            {
                double d = ((Double)((Pair) (obj)).second).doubleValue();
                double d1 = ((Double)((Pair) (obj1)).second).doubleValue();
                return ComparisonChain.ACTIVE.compare(d1, d).compare(findtimeattendee.displayName, findtimeattendee1.displayName).result();
            }
        }

        public AttendeePenaltyComparator(String s)
        {
            organizerEmail = s;
        }
    }

    static final class TimelineSuggestionComparator
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (TimelineSuggestion)obj;
            obj1 = (TimelineSuggestion)obj1;
            return ComparisonChain.ACTIVE.compare(((TimelineSuggestion) (obj)).totalPenalty, ((TimelineSuggestion) (obj1)).totalPenalty).compare(((TimelineEvent) (obj)).timeRange.getUtcStartMillis(), ((TimelineEvent) (obj1)).timeRange.getUtcStartMillis()).result();
        }

        TimelineSuggestionComparator()
        {
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/net/FindTimeExchangeClient);
    private final String accountEmail;
    private final AttendeePenaltyComparator attendeePenaltyComparator;
    private final Context context;
    private final TimelineSuggestionComparator suggestionComparator = new TimelineSuggestionComparator();

    public FindTimeExchangeClient(Context context1, String s)
    {
        context = context1;
        accountEmail = s;
        attendeePenaltyComparator = new AttendeePenaltyComparator(accountEmail);
    }

    private static boolean isInsideWorkingHour(Calendar calendar, long l)
    {
        boolean flag = true;
        calendar.setTimeInMillis(l);
        int i = calendar.get(7);
        if (i == 7 || i == 1)
        {
            flag = false;
        } else
        {
            int j = calendar.get(11);
            int k = calendar.get(12);
            if (j < 7 || j == 21 && k > 0 || j > 21)
            {
                return false;
            }
        }
        return flag;
    }

    private final FindTimeClient.Result retrieveData(FindTimeClient.Request request)
        throws IOException
    {
        Object obj;
        ImmutableList immutablelist;
        Object obj1;
        long l3;
        long l5;
        long l6;
        immutablelist = request.attendees;
        if (immutablelist == null || immutablelist.size() == 0)
        {
            return new FindTimeClient.Result(ImmutableList.of(), ImmutableList.of(), ImmutableList.of(), 0, null);
        }
        obj = new ArrayList(immutablelist.size());
        obj1 = (ImmutableList)immutablelist;
        int i1 = ((ImmutableList) (obj1)).size();
        int i = 0;
        Object obj2 = (UnmodifiableIterator)null;
        while (i < i1) 
        {
            obj2 = ((ImmutableList) (obj1)).get(i);
            i++;
            ((List) (obj)).add(((FindTimeAttendee)obj2).email);
        }
        l3 = ExchangeUtil.roundUpTimeWindow(request.timeframe.startTimeMillis);
        l5 = ExchangeUtil.roundUpTimeWindow(request.timeframe.endTimeMillis);
        l6 = request.timeframe.durationMillis;
        long l1 = l3 - 0x5265c00L;
        obj1 = ExchangeUtil.getEasServiceProxy(context);
        if (obj1 == null)
        {
            throw new IllegalStateException("No EasServiceProxy is available");
        }
        Iterator iterator;
        FindTimeAttendee findtimeattendee;
        Object obj4;
        try
        {
            obj = ((EmailServiceProxy) (obj1)).retrieveRecipientAvailabilities(accountEmail, ((List) (obj)), l1, l3 + 0x5265c00L);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            LogUtils.e(TAG, ((Throwable) (obj)), "retrieveRecipientAvalabilities failed", new Object[0]);
            obj = null;
        }
        if (obj == null && NetworkUtil.isConnectedToInternet(context))
        {
            obj = Collections.emptyList();
        }
        if (obj == null) goto _L2; else goto _L1
_L1:
        Object obj3;
        obj2 = request.timezone;
        obj1 = new ArrayList();
        for (iterator = ((List) (obj)).iterator(); iterator.hasNext(); ((List) (obj1)).add(findtimeattendee))
        {
            obj4 = (RecipientAvailability)iterator.next();
            findtimeattendee = new FindTimeAttendee(accountEmail, ((RecipientAvailability) (obj4)).emailAddress, accountEmail.equals(((RecipientAvailability) (obj4)).emailAddress));
            findtimeattendee.displayName = ((RecipientAvailability) (obj4)).displayName;
            obj4 = ExchangeUtil.getEventsFromRecipientAvailabilities(l1, ((java.util.TimeZone) (obj2)), ((RecipientAvailability) (obj4)), context);
            Collections.sort(((List) (obj4)), TimelineItem.ItemComparator);
            findtimeattendee.setEvents(ImmutableList.copyOf(((java.util.Collection) (obj4))));
        }

        Calendar calendar;
        long l2;
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        l3 = Math.max(l3, l2);
        obj3 = request.timezone;
        request = new ArrayList();
        calendar = Calendar.getInstance();
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        calendar.setTimeInMillis(l2);
        calendar.setTimeZone(((java.util.TimeZone) (obj3)));
        l2 = (((0x1b7740L + l3) - 1L) / 0x1b7740L) * 0x1b7740L;
        for (long l4 = l2 + l6; l4 < l5; l4 = l2 + l6)
        {
            if (isInsideWorkingHour(calendar, l2) && isInsideWorkingHour(calendar, l4))
            {
                TimelineSuggestion timelinesuggestion1 = new TimelineSuggestion();
                timelinesuggestion1.timeRange = TimeRange.newInstance(((java.util.TimeZone) (obj3)), false, l2, l4);
                request.add(timelinesuggestion1);
            }
            l2 += 0x1b7740L;
        }

        obj3 = request.iterator();
_L19:
        if (!((Iterator) (obj3)).hasNext()) goto _L4; else goto _L3
_L3:
        TimelineSuggestion timelinesuggestion;
        HashSet hashset1;
        ArrayList arraylist;
        Iterator iterator3;
        timelinesuggestion = (TimelineSuggestion)((Iterator) (obj3)).next();
        hashset1 = new HashSet();
        arraylist = new ArrayList();
        iterator3 = ((List) (obj1)).iterator();
_L18:
        if (!iterator3.hasNext()) goto _L6; else goto _L5
_L5:
        double d1;
        FindTimeAttendee findtimeattendee1;
        Iterator iterator4;
        findtimeattendee1 = (FindTimeAttendee)iterator3.next();
        d1 = 0.0D;
        iterator4 = findtimeattendee1.events.iterator();
_L16:
        if (!iterator4.hasNext()) goto _L8; else goto _L7
_L7:
        TimelineAttendeeEvent timelineattendeeevent;
        TimelineExchangeAttendeeEvent timelineexchangeattendeeevent;
        timelineattendeeevent = (TimelineAttendeeEvent)iterator4.next();
        timelineexchangeattendeeevent = (TimelineExchangeAttendeeEvent)timelineattendeeevent;
        if (((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis() >= ((TimelineEvent) (timelineattendeeevent)).timeRange.getUtcEndMillis() || ((TimelineEvent) (timelineattendeeevent)).timeRange.getUtcStartMillis() >= ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis()) goto _L10; else goto _L9
_L9:
        timelineexchangeattendeeevent.status;
        JVM INSTR tableswitch 1 4: default 772
    //                   1 795
    //                   2 802
    //                   3 807
    //                   4 814;
           goto _L11 _L12 _L13 _L14 _L15
_L11:
        double d = 0.0D;
_L17:
        d += d1;
        hashset1.add(findtimeattendee1);
_L25:
        d1 = d;
          goto _L16
_L12:
        d = 0.29999999999999999D;
          goto _L17
_L13:
        d = 1.0D;
          goto _L17
_L14:
        d = 2D;
          goto _L17
_L15:
        d = 1.0D;
          goto _L17
_L8:
        timelinesuggestion.totalPenalty = timelinesuggestion.totalPenalty + d1;
        arraylist.add(Pair.create(findtimeattendee1, Double.valueOf(d1)));
          goto _L18
_L6:
        Collections.sort(arraylist, attendeePenaltyComparator);
        com.google.common.collect.ImmutableList.Builder builder1 = new com.google.common.collect.ImmutableList.Builder();
        arraylist = (ArrayList)arraylist;
        int j1 = arraylist.size();
        for (int j = 0; j < j1;)
        {
            Object obj6 = arraylist.get(j);
            j++;
            obj6 = (com.google.common.collect.ImmutableList.Builder)builder1.add((FindTimeAttendee)((Pair)obj6).first);
        }

        builder1.forceCopy = true;
        timelinesuggestion.attendees = ImmutableList.asImmutableList(builder1.contents, builder1.size);
        timelinesuggestion.attendeeExplanations = FindTimeUtil.convertToExplanationForExchange(hashset1);
          goto _L19
_L4:
        int l;
        int k1;
        Collections.sort(request, suggestionComparator);
        obj1 = new com.google.common.collect.ImmutableList.Builder();
        for (int k = 0; k < request.size() && k < 10; k++)
        {
            obj3 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj1)).add((TimelineSuggestion)request.get(k));
        }

        obj3 = new com.google.common.collect.ImmutableList.Builder();
        com.google.common.collect.ImmutableList.Builder builder;
        for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            builder = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add(((RecipientAvailability)((Iterator) (obj)).next()).emailAddress);
        }

        obj3.forceCopy = true;
        obj = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj3)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj3)).size);
        obj1.forceCopy = true;
        obj1 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj1)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj1)).size);
        obj3 = ImmutableList.builder();
        HashSet hashset = new HashSet();
        for (Iterator iterator1 = ((List) (obj)).iterator(); iterator1.hasNext(); hashset.add(((String)iterator1.next()).toLowerCase())) { }
        Iterator iterator2 = immutablelist.iterator();
        do
        {
            if (!iterator2.hasNext())
            {
                break;
            }
            Object obj5 = (FindTimeAttendee)iterator2.next();
            if (!hashset.contains(((FindTimeAttendee) (obj5)).email.toLowerCase()))
            {
                obj5 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add(new OmittedAttendee(((FindTimeAttendee) (obj5)).email, ((FindTimeAttendee) (obj5)).displayName, 0));
            }
        } while (true);
        obj3.forceCopy = true;
        obj3 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj3)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj3)).size);
        d = immutablelist.size();
        k1 = Math.min(request.size(), 10);
        l = 0;
_L24:
        if (l >= k1) goto _L21; else goto _L20
_L20:
        if (((TimelineSuggestion)request.get(l)).totalPenalty <= 1.5D * d) goto _L23; else goto _L22
_L22:
        return new FindTimeClient.Result(((ImmutableList) (obj1)), ((ImmutableList) (obj)), ((ImmutableList) (obj3)), l, null);
_L23:
        l++;
        continue; /* Loop/switch isn't completed */
_L21:
        l = k1;
        if (true) goto _L22; else goto _L2
_L2:
        throw new IOException("Failed to retrieve recipient availabilities");
        if (true) goto _L24; else goto _L10
_L10:
        d = d1;
          goto _L25
    }

    protected final volatile Object retrieveData(Object obj)
        throws Exception
    {
        return retrieveData((FindTimeClient.Request)obj);
    }

}
