// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.accounts.Account;
import com.google.android.calendar.api.event.EventKey;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            ProposeNewTimeState, TimeProposal

public abstract class 
{

    public abstract ProposeNewTimeState build();

    public abstract  setAccount(Account account);

    public abstract  setAttendees(List list);

    public abstract  setCalendarId(String s);

    public abstract  setEventColor(int i);

    public abstract  setEventId(String s);

    public abstract  setEventKey(EventKey eventkey);

    public abstract  setMode( );

    public abstract  setOriginalEventEndTime(long l);

    public abstract  setOriginalEventStartTime(long l);

    public abstract  setResponseStatus(com.google.android.calendar.api.event.attendee.uilder uilder);

    public abstract  setSelectedProposalIndex(int i);

    public abstract  setTimeProposal(TimeProposal timeproposal);

    public ()
    {
    }
}
