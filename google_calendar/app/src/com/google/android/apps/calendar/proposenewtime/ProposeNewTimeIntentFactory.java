// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.proposenewtime.state.AutoValue_TimeProposal;
import com.google.android.apps.calendar.proposenewtime.utils.ProposeNewTimeUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.newapi.model.AccountHolder;
import com.google.android.calendar.newapi.model.ColorHolder;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeActivity

public final class ProposeNewTimeIntentFactory
{

    public static Intent create(Context context, ColorHolder colorholder, com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Builder builder, Attendee attendee)
    {
        Intent intent = new Intent(context, com/google/android/apps/calendar/proposenewtime/ProposeNewTimeActivity);
        builder.setEventColor(colorholder.getColor(context));
        builder.setAccount(((AccountHolder)colorholder).getAccount());
        context = AttendeeUtils.getCurrentAttendee(((EventHolder)colorholder).getEvent());
        Object obj;
        ArrayList arraylist;
        String s;
        Object obj1;
        int i;
        if (context == null)
        {
            context = null;
        } else
        {
            context = ((Attendee) (context)).response;
        }
        if (context != null)
        {
            builder.setResponseStatus(((Response) (context)).status);
        }
        context = AttendeeUtils.getCurrentAttendee(colorholder.getEvent());
        if (context == null)
        {
            context = null;
        } else
        {
            context = ((Attendee) (context)).response;
        }
        if (ProposeNewTimeUtils.responseHasProposal(context))
        {
            builder.setTimeProposal(new AutoValue_TimeProposal(((Response) (context)).proposedStartTimeMillis.longValue(), ((Response) (context)).proposedEndTimeMillis.longValue(), ((Response) (context)).comment));
        } else
        {
            long l = colorholder.getEvent().getStartMillis();
            long l1 = colorholder.getEvent().getEndMillis();
            if (context != null)
            {
                context = ((Response) (context)).comment;
            } else
            {
                context = "";
            }
            builder.setTimeProposal(new AutoValue_TimeProposal(l, l1, context));
        }
        builder.setOriginalEventStartTime(colorholder.getEvent().getStartMillis());
        builder.setOriginalEventEndTime(colorholder.getEvent().getEndMillis());
        arraylist = new ArrayList();
        builder.setSelectedProposalIndex(0);
        s = ((AccountHolder)colorholder).getAccount().name;
        if (colorholder.getEvent().getCalendar() != null)
        {
            context = colorholder.getEvent().getCalendar().calendarId;
        } else
        {
            context = "";
        }
        obj = colorholder.getEvent().getCalendarListEntry().getDisplayName();
        obj1 = (new com.google.android.apps.calendar.proposenewtime.state..AutoValue_Attendee.Builder()).setDisabled(false).setSourceAccount(s).setEmail(context).setDisplayName(((String) (obj)));
        obj = AttendeeUtils.getCurrentAttendee(colorholder.getEvent());
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((Attendee) (obj)).response;
        }
        if (ProposeNewTimeUtils.responseHasProposal(((Response) (obj))))
        {
            ((com.google.android.apps.calendar.proposenewtime.state.Attendee.Builder) (obj1)).setProposal(new AutoValue_TimeProposal(((Response) (obj)).proposedStartTimeMillis.longValue(), ((Response) (obj)).proposedEndTimeMillis.longValue(), ((Response) (obj)).comment));
            if (attendee != null)
            {
                obj = attendee.attendeeDescriptor.email;
                Object obj2;
                Response response;
                int j;
                int k;
                if (context != null && context.equalsIgnoreCase(((String) (obj))))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    builder.setSelectedProposalIndex(0);
                }
            }
            i = 1;
        } else
        {
            i = 0;
        }
        arraylist.add(((com.google.android.apps.calendar.proposenewtime.state.Attendee.Builder) (obj1)).build());
        obj = (ImmutableList)colorholder.getEvent().getAttendees();
        k = ((ImmutableList) (obj)).size();
        obj1 = (UnmodifiableIterator)null;
        j = 0;
        do
        {
            if (j >= k)
            {
                break;
            }
            obj1 = ((ImmutableList) (obj)).get(j);
            j++;
            obj1 = (Attendee)obj1;
            obj2 = ((Attendee) (obj1)).attendeeDescriptor.email;
            boolean flag;
            if (context != null && context.equalsIgnoreCase(((String) (obj2))))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj2 = (new com.google.android.apps.calendar.proposenewtime.state..AutoValue_Attendee.Builder()).setDisabled(false).setSourceAccount(s).setEmail(((Attendee) (obj1)).attendeeDescriptor.email).setDisplayName(((Attendee) (obj1)).displayName);
                response = ((Attendee) (obj1)).response;
                if (ProposeNewTimeUtils.responseHasProposal(response))
                {
                    ((com.google.android.apps.calendar.proposenewtime.state.Attendee.Builder) (obj2)).setProposal(new AutoValue_TimeProposal(response.proposedStartTimeMillis.longValue(), response.proposedEndTimeMillis.longValue(), response.comment));
                    if (attendee != null && AttendeeUtils.isSameAttendee(((Attendee) (obj1)).attendeeDescriptor, attendee.attendeeDescriptor))
                    {
                        builder.setSelectedProposalIndex(i);
                    }
                    i++;
                }
                arraylist.add(((com.google.android.apps.calendar.proposenewtime.state.Attendee.Builder) (obj2)).build());
            }
        } while (true);
        builder.setAttendees(ImmutableList.copyOf(arraylist));
        if (((EventHolder)colorholder).getEvent().getCalendar() != null)
        {
            builder.setCalendarId(((EventHolder)colorholder).getEvent().getCalendar().calendarId);
        }
        builder.setEventId(((EventHolder)colorholder).getEvent().getGoogleSyncId());
        builder.setEventKey(((EventHolder)colorholder).getEvent().getDescriptor().getKey());
        intent.putExtra("propose_new_time_initial_state", builder.build());
        return intent;
    }
}
