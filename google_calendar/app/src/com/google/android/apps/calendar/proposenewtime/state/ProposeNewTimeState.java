// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.accounts.Account;
import android.os.Parcelable;
import com.google.android.calendar.api.event.EventKey;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            Attendee, TimeProposal

public abstract class ProposeNewTimeState
    implements Parcelable
{

    public ProposeNewTimeState()
    {
    }

    public abstract Account getAccount();

    public final Attendee getAttendeeForNthProposal(int i)
    {
        Iterator iterator = getAttendees().iterator();
        int j = 0;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Attendee attendee = (Attendee)iterator.next();
            if (attendee.getProposal() != null)
            {
                if (j == i)
                {
                    return attendee;
                }
                j++;
            }
        } while (true);
        return null;
    }

    public abstract List getAttendees();

    public abstract String getCalendarId();

    public abstract int getEventColor();

    public abstract String getEventId();

    public abstract EventKey getEventKey();

    public abstract Mode getMode();

    public abstract long getOriginalEventEndTime();

    public abstract long getOriginalEventStartTime();

    public abstract com.google.android.calendar.api.event.attendee.Response.ResponseStatus getResponseStatus();

    public abstract int getSelectedProposalIndex();

    public abstract TimeProposal getTimeProposal();

    public abstract Builder toBuilder();
}
