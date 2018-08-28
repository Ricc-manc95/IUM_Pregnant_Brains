// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.text.TextUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.common.ExtendedPropertyPair;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            Attendee, Response, AttendeeStoreUtils, AttendeeDescriptor

public final class ResponseExtrasStoreUtils
{

    public static final String RESPONSE_EXTRAS_EXTENDED_PROPERTIES[] = {
        "proposedStartTime", "proposedEndTime", "meetingRequestComment"
    };

    public static List createExtendedPropertyPairs(Event event)
    {
        event = AttendeeUtils.getCurrentAttendee(event);
        if (event == null)
        {
            event = Collections.emptyList();
        } else
        {
            Response response = ((Attendee) (event)).response;
            ArrayList arraylist = new ArrayList();
            if (response.proposedStartTimeMillis != null && response.proposedEndTimeMillis != null)
            {
                arraylist.add(new ExtendedPropertyPair("proposedStartTime", String.valueOf(response.proposedStartTimeMillis)));
                arraylist.add(new ExtendedPropertyPair("proposedEndTime", String.valueOf(response.proposedEndTimeMillis)));
            }
            event = arraylist;
            if (!TextUtils.isEmpty(response.comment))
            {
                arraylist.add(new ExtendedPropertyPair("meetingRequestComment", String.valueOf(response.comment)));
                return arraylist;
            }
        }
        return event;
    }

    private static Response getResponseOfAttendee(Event event, AttendeeDescriptor attendeedescriptor)
    {
        Object obj = null;
        ImmutableList immutablelist = (ImmutableList)event.getAttendees();
        int j = immutablelist.size();
        int i = 0;
        event = (UnmodifiableIterator)null;
        do
        {
            event = obj;
            if (i >= j)
            {
                break;
            }
            event = ((Event) (immutablelist.get(i)));
            i++;
            event = (Attendee)event;
            if (!AttendeeStoreUtils.descriptorsEquivalent(((Attendee) (event)).attendeeDescriptor, attendeedescriptor))
            {
                continue;
            }
            event = ((Attendee) (event)).response;
            break;
        } while (true);
        return event;
    }

    public static boolean isResponseCommentOfCurrentAttendeeModified(EventModifications eventmodifications)
    {
        if (isResponseOfCurrentAttendeeModified(eventmodifications))
        {
            Object obj = AttendeeUtils.getCurrentAttendee(eventmodifications);
            eventmodifications = getResponseOfAttendee(eventmodifications.getOriginal(), ((Attendee) (obj)).attendeeDescriptor).comment;
            obj = ((Attendee) (obj)).response.comment;
            boolean flag;
            if (eventmodifications == obj || eventmodifications != null && eventmodifications.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isResponseOfCurrentAttendeeModified(EventModifications eventmodifications)
    {
        Object obj = eventmodifications.getOriginal();
        if (obj != null)
        {
            if ((eventmodifications = AttendeeUtils.getCurrentAttendee(eventmodifications)) != null && ((obj = getResponseOfAttendee(((Event) (obj)), ((Attendee) (eventmodifications)).attendeeDescriptor)) != null && !((Response) (obj)).equals(((Attendee) (eventmodifications)).response)))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isResponseTimeProposalOfCurrentAttendeeModified(EventModifications eventmodifications)
    {
        if (isResponseOfCurrentAttendeeModified(eventmodifications)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Object obj = AttendeeUtils.getCurrentAttendee(eventmodifications);
        eventmodifications = getResponseOfAttendee(eventmodifications.getOriginal(), ((Attendee) (obj)).attendeeDescriptor);
        Long long1 = ((Response) (eventmodifications)).proposedStartTimeMillis;
        Long long2 = ((Attendee) (obj)).response.proposedStartTimeMillis;
        boolean flag;
        if (long1 == long2 || long1 != null && long1.equals(long2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        eventmodifications = ((Response) (eventmodifications)).proposedEndTimeMillis;
        obj = ((Attendee) (obj)).response.proposedEndTimeMillis;
        if (eventmodifications == obj || eventmodifications != null && eventmodifications.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return true;
    }

}
