// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.ContactId;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.TimeChangeProposal;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, GooglePrivateData

final class ContentProviderShared
{

    static boolean canProposeTimeWithGoogleAccount(Event event)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).propose_new_time();
        return AccountUtil.isGoogleAccount(event.getCalendar().account);
    }

    static String getEventAttendeeList(Event event)
    {
        ArrayList arraylist;
        ImmutableList immutablelist;
        int i;
        int j;
        arraylist = new ArrayList();
        immutablelist = (ImmutableList)event.getAttendees();
        j = immutablelist.size();
        i = 0;
_L2:
        Attendee attendee;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_403;
        }
        attendee = (Attendee)immutablelist.get(i);
        if (attendee != null)
        {
            break; /* Loop/switch isn't completed */
        }
        event = null;
_L8:
        arraylist.add(event);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        EventAttendee eventattendee;
        eventattendee = new EventAttendee();
        event = attendee.response.proposedStartTimeMillis;
        Object obj = attendee.response.proposedEndTimeMillis;
        if (event != null && obj != null)
        {
            TimeChangeProposal timechangeproposal = new TimeChangeProposal();
            timechangeproposal.startTimeMillis = event;
            timechangeproposal.endTimeMillis = ((Long) (obj));
            eventattendee.timeChangeProposal = timechangeproposal;
        }
        eventattendee.comment = attendee.response.comment;
        obj = attendee.attendeeDescriptor.email;
        ContactId contactid = attendee.attendeeDescriptor.contactId;
        event = ((Event) (obj));
        if (contactid != null)
        {
            event = contactid.namespace;
            boolean flag;
            if (event == "com.google" || event != null && event.equals("com.google"))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            event = ((Event) (obj));
            if (flag)
            {
                event = String.valueOf(obj);
                obj = String.valueOf("@profile.calendar.google.com");
                if (((String) (obj)).length() != 0)
                {
                    event = event.concat(((String) (obj)));
                } else
                {
                    event = new String(event);
                }
            }
        }
        eventattendee.email = event;
        if (attendee.attendeeDescriptor.contactId != null)
        {
            eventattendee.id = attendee.attendeeDescriptor.contactId.identity;
        }
        eventattendee.displayName = attendee.displayName;
        if (attendee.type == 3)
        {
            eventattendee.resource = Boolean.valueOf(true);
        }
        if (attendee.role == 2)
        {
            eventattendee.optional = Boolean.valueOf(true);
        }
        attendee.response.status.ordinal();
        JVM INSTR tableswitch 0 3: default 344
    //                   0 397
    //                   1 372
    //                   2 391
    //                   3 385;
           goto _L3 _L4 _L5 _L6 _L7
_L4:
        break MISSING_BLOCK_LABEL_397;
_L3:
        throw new IllegalArgumentException("Illegal Attendee ResponseStatus.");
_L5:
        event = "accepted";
_L9:
        eventattendee.responseStatus = event;
        event = eventattendee;
          goto _L8
_L7:
        event = "declined";
          goto _L9
_L6:
        event = "tentative";
          goto _L9
        event = "needsAction";
          goto _L9
        boolean flag1;
        if (arraylist.isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return TimelyEventData.asString(flag1, "attendees", arraylist);
    }

    static GooglePrivateData.GuestNotification getGuestNotification(Event event)
    {
        Function function = GooglePrivateDataHelper..Lambda._cls0.$instance;
        GooglePrivateData googleprivatedata = event.getGooglePrivateData();
        event = googleprivatedata;
        if (googleprivatedata == null)
        {
            event = GooglePrivateData.DEFAULT;
        }
        return (GooglePrivateData.GuestNotification)function.apply(event);
    }
}
