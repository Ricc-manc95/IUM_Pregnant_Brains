// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.calendar.api.event.conference.ConferenceDataUtils;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, GooglePrivateDataHelper

final class SyncAdapterContentValuesPopulator
{

    static int computeEventExtrasFlags(Event event)
    {
        Object obj;
        com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder builder;
        boolean flag3;
        flag3 = true;
        if (event == null)
        {
            return 0;
        }
        obj = EventExtrasFlags.builder().setSmartMailAvailable(event.isSmartMailEvent());
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj1)
            {
                obj1 = (Attendee)obj1;
                return ((Attendee) (obj1)).response != null && ((Attendee) (obj1)).response.proposedStartTimeMillis != null && ((Attendee) (obj1)).response.proposedEndTimeMillis != null;
            }


            private .Lambda._cls0()
            {
            }
        }

        Predicate predicate;
        Iterator iterator;
        boolean flag;
        boolean flag1;
        if (event.isSmartMailEvent())
        {
            flag1 = true;
        } else
        if (!event.getLocation().getEventLocations().isEmpty() && ((EventLocation)event.getLocation().getEventLocations().iterator().next()).geo != null)
        {
            flag1 = true;
        } else
        {
            event.isSocial();
            flag1 = false;
        }
        obj = ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).setImageAvailable(flag1).setEndTimeUnspecified(event.isEndTimeUnspecified());
        if (event.getVisibility() == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj.flags = ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).flags | 8;
        } else
        {
            obj.flags = ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).flags & -9;
        }
        if (!event.getLocation().getEventLocations().isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj = ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).setStructuredLocation(flag1);
        if (!ConferenceDataUtils.isEmptyConference(event.getConferenceData()))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj = ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).setConferenceData(flag1);
        if (event.getParticipantStatus() != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        builder = ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).setParticipantStatus(flag1).setEveryoneDeclined(GooglePrivateDataHelper.hasEveryoneDeclined(event));
        obj = event.getAttendees();
        predicate = .Lambda._cls0..instance;
        iterator = ((Iterable) (obj)).iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_400;
            }
            obj = iterator.next();
        } while (!predicate.apply(obj));
_L1:
        boolean flag2;
        if (obj != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        obj = builder.setHasTimeProposals(flag2);
        if (event.getParticipantStatus() != null && event.getParticipantStatus().getOutOfOffice() != null)
        {
            flag2 = flag3;
        } else
        {
            flag2 = false;
        }
        return (new EventExtrasFlags(((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj)).setOoo(flag2).setEveryoneDeclinedDismissed(GooglePrivateDataHelper.isEveryoneDeclinedDismissed(event)).flags)).flags;
        obj = null;
          goto _L1
    }
}
