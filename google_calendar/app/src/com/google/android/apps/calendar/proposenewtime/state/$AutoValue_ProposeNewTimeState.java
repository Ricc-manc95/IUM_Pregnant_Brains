// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.accounts.Account;
import com.google.android.calendar.api.event.EventKey;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            ProposeNewTimeState, TimeProposal

abstract class $AutoValue_ProposeNewTimeState extends ProposeNewTimeState
{

    private final Account account;
    private final List attendees;
    private final String calendarId;
    private final int eventColor;
    private final String eventId;
    private final EventKey eventKey;
    private final ProposeNewTimeState.Mode mode;
    private final long originalEventEndTime;
    private final long originalEventStartTime;
    private final com.google.android.calendar.api.event.attendee.Response.ResponseStatus responseStatus;
    private final int selectedProposalIndex;
    private final TimeProposal timeProposal;

    $AutoValue_ProposeNewTimeState(int i, TimeProposal timeproposal, List list, int j, ProposeNewTimeState.Mode mode1, Account account1, long l, long l1, com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, String s, String s1, EventKey eventkey)
    {
        eventColor = i;
        if (timeproposal == null)
        {
            throw new NullPointerException("Null timeProposal");
        }
        timeProposal = timeproposal;
        if (list == null)
        {
            throw new NullPointerException("Null attendees");
        }
        attendees = list;
        selectedProposalIndex = j;
        if (mode1 == null)
        {
            throw new NullPointerException("Null mode");
        }
        mode = mode1;
        if (account1 == null)
        {
            throw new NullPointerException("Null account");
        }
        account = account1;
        originalEventStartTime = l;
        originalEventEndTime = l1;
        responseStatus = responsestatus;
        calendarId = s;
        eventId = s1;
        if (eventkey == null)
        {
            throw new NullPointerException("Null eventKey");
        } else
        {
            eventKey = eventkey;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ProposeNewTimeState)
            {
                if (eventColor != ((ProposeNewTimeState) (obj = (ProposeNewTimeState)obj)).getEventColor() || !timeProposal.equals(((ProposeNewTimeState) (obj)).getTimeProposal()) || !attendees.equals(((ProposeNewTimeState) (obj)).getAttendees()) || selectedProposalIndex != ((ProposeNewTimeState) (obj)).getSelectedProposalIndex() || !mode.equals(((ProposeNewTimeState) (obj)).getMode()) || !account.equals(((ProposeNewTimeState) (obj)).getAccount()) || originalEventStartTime != ((ProposeNewTimeState) (obj)).getOriginalEventStartTime() || originalEventEndTime != ((ProposeNewTimeState) (obj)).getOriginalEventEndTime() || (responseStatus != null ? !responseStatus.equals(((ProposeNewTimeState) (obj)).getResponseStatus()) : ((ProposeNewTimeState) (obj)).getResponseStatus() != null) || (calendarId != null ? !calendarId.equals(((ProposeNewTimeState) (obj)).getCalendarId()) : ((ProposeNewTimeState) (obj)).getCalendarId() != null) || (eventId != null ? !eventId.equals(((ProposeNewTimeState) (obj)).getEventId()) : ((ProposeNewTimeState) (obj)).getEventId() != null) || !eventKey.equals(((ProposeNewTimeState) (obj)).getEventKey()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final Account getAccount()
    {
        return account;
    }

    public final List getAttendees()
    {
        return attendees;
    }

    public final String getCalendarId()
    {
        return calendarId;
    }

    public final int getEventColor()
    {
        return eventColor;
    }

    public final String getEventId()
    {
        return eventId;
    }

    public final EventKey getEventKey()
    {
        return eventKey;
    }

    public final ProposeNewTimeState.Mode getMode()
    {
        return mode;
    }

    public final long getOriginalEventEndTime()
    {
        return originalEventEndTime;
    }

    public final long getOriginalEventStartTime()
    {
        return originalEventStartTime;
    }

    public final com.google.android.calendar.api.event.attendee.Response.ResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    public final int getSelectedProposalIndex()
    {
        return selectedProposalIndex;
    }

    public final TimeProposal getTimeProposal()
    {
        return timeProposal;
    }

    public int hashCode()
    {
        int k = 0;
        int l = eventColor;
        int i1 = timeProposal.hashCode();
        int j1 = attendees.hashCode();
        int k1 = selectedProposalIndex;
        int l1 = mode.hashCode();
        int i2 = account.hashCode();
        int j2 = (int)(originalEventStartTime >>> 32 ^ originalEventStartTime);
        int k2 = (int)(originalEventEndTime >>> 32 ^ originalEventEndTime);
        int i;
        int j;
        if (responseStatus == null)
        {
            i = 0;
        } else
        {
            i = responseStatus.hashCode();
        }
        if (calendarId == null)
        {
            j = 0;
        } else
        {
            j = calendarId.hashCode();
        }
        if (eventId != null)
        {
            k = eventId.hashCode();
        }
        return ((j ^ (i ^ ((((((((l ^ 0xf4243) * 0xf4243 ^ i1) * 0xf4243 ^ j1) * 0xf4243 ^ k1) * 0xf4243 ^ l1) * 0xf4243 ^ i2) * 0xf4243 ^ j2) * 0xf4243 ^ k2) * 0xf4243) * 0xf4243) * 0xf4243 ^ k) * 0xf4243 ^ eventKey.hashCode();
    }

    public final ProposeNewTimeState.Builder toBuilder()
    {
        class Builder extends ProposeNewTimeState.Builder
        {

            private Account account;
            private List attendees;
            private String calendarId;
            private Integer eventColor;
            private String eventId;
            private EventKey eventKey;
            private ProposeNewTimeState.Mode mode;
            private Long originalEventEndTime;
            private Long originalEventStartTime;
            private com.google.android.calendar.api.event.attendee.Response.ResponseStatus responseStatus;
            private Integer selectedProposalIndex;
            private TimeProposal timeProposal;

            public final ProposeNewTimeState build()
            {
                String s2 = "";
                if (eventColor == null)
                {
                    s2 = String.valueOf("").concat(" eventColor");
                }
                String s = s2;
                if (timeProposal == null)
                {
                    s = String.valueOf(s2).concat(" timeProposal");
                }
                s2 = s;
                if (attendees == null)
                {
                    s2 = String.valueOf(s).concat(" attendees");
                }
                s = s2;
                if (selectedProposalIndex == null)
                {
                    s = String.valueOf(s2).concat(" selectedProposalIndex");
                }
                s2 = s;
                if (mode == null)
                {
                    s2 = String.valueOf(s).concat(" mode");
                }
                s = s2;
                if (account == null)
                {
                    s = String.valueOf(s2).concat(" account");
                }
                s2 = s;
                if (originalEventStartTime == null)
                {
                    s2 = String.valueOf(s).concat(" originalEventStartTime");
                }
                s = s2;
                if (originalEventEndTime == null)
                {
                    s = String.valueOf(s2).concat(" originalEventEndTime");
                }
                s2 = s;
                if (eventKey == null)
                {
                    s2 = String.valueOf(s).concat(" eventKey");
                }
                if (!s2.isEmpty())
                {
                    String s1 = String.valueOf(s2);
                    if (s1.length() != 0)
                    {
                        s1 = "Missing required properties:".concat(s1);
                    } else
                    {
                        s1 = new String("Missing required properties:");
                    }
                    throw new IllegalStateException(s1);
                } else
                {
                    return new AutoValue_ProposeNewTimeState(eventColor.intValue(), timeProposal, attendees, selectedProposalIndex.intValue(), mode, account, originalEventStartTime.longValue(), originalEventEndTime.longValue(), responseStatus, calendarId, eventId, eventKey);
                }
            }

            public final ProposeNewTimeState.Builder setAccount(Account account1)
            {
                if (account1 == null)
                {
                    throw new NullPointerException("Null account");
                } else
                {
                    account = account1;
                    return this;
                }
            }

            public final ProposeNewTimeState.Builder setAttendees(List list)
            {
                if (list == null)
                {
                    throw new NullPointerException("Null attendees");
                } else
                {
                    attendees = list;
                    return this;
                }
            }

            public final ProposeNewTimeState.Builder setCalendarId(String s)
            {
                calendarId = s;
                return this;
            }

            public final ProposeNewTimeState.Builder setEventColor(int i)
            {
                eventColor = Integer.valueOf(i);
                return this;
            }

            public final ProposeNewTimeState.Builder setEventId(String s)
            {
                eventId = s;
                return this;
            }

            public final ProposeNewTimeState.Builder setEventKey(EventKey eventkey)
            {
                if (eventkey == null)
                {
                    throw new NullPointerException("Null eventKey");
                } else
                {
                    eventKey = eventkey;
                    return this;
                }
            }

            public final ProposeNewTimeState.Builder setMode(ProposeNewTimeState.Mode mode1)
            {
                if (mode1 == null)
                {
                    throw new NullPointerException("Null mode");
                } else
                {
                    mode = mode1;
                    return this;
                }
            }

            public final ProposeNewTimeState.Builder setOriginalEventEndTime(long l)
            {
                originalEventEndTime = Long.valueOf(l);
                return this;
            }

            public final ProposeNewTimeState.Builder setOriginalEventStartTime(long l)
            {
                originalEventStartTime = Long.valueOf(l);
                return this;
            }

            public final ProposeNewTimeState.Builder setResponseStatus(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
            {
                responseStatus = responsestatus;
                return this;
            }

            public final ProposeNewTimeState.Builder setSelectedProposalIndex(int i)
            {
                selectedProposalIndex = Integer.valueOf(i);
                return this;
            }

            public final ProposeNewTimeState.Builder setTimeProposal(TimeProposal timeproposal)
            {
                if (timeproposal == null)
                {
                    throw new NullPointerException("Null timeProposal");
                } else
                {
                    timeProposal = timeproposal;
                    return this;
                }
            }

            public Builder()
            {
            }

            Builder(ProposeNewTimeState proposenewtimestate)
            {
                eventColor = Integer.valueOf(proposenewtimestate.getEventColor());
                timeProposal = proposenewtimestate.getTimeProposal();
                attendees = proposenewtimestate.getAttendees();
                selectedProposalIndex = Integer.valueOf(proposenewtimestate.getSelectedProposalIndex());
                mode = proposenewtimestate.getMode();
                account = proposenewtimestate.getAccount();
                originalEventStartTime = Long.valueOf(proposenewtimestate.getOriginalEventStartTime());
                originalEventEndTime = Long.valueOf(proposenewtimestate.getOriginalEventEndTime());
                responseStatus = proposenewtimestate.getResponseStatus();
                calendarId = proposenewtimestate.getCalendarId();
                eventId = proposenewtimestate.getEventId();
                eventKey = proposenewtimestate.getEventKey();
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        int i = eventColor;
        String s = String.valueOf(timeProposal);
        String s1 = String.valueOf(attendees);
        int j = selectedProposalIndex;
        String s2 = String.valueOf(mode);
        String s3 = String.valueOf(account);
        long l = originalEventStartTime;
        long l1 = originalEventEndTime;
        String s4 = String.valueOf(responseStatus);
        String s5 = calendarId;
        String s6 = eventId;
        String s7 = String.valueOf(eventKey);
        return (new StringBuilder(String.valueOf(s).length() + 261 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length())).append("ProposeNewTimeState{eventColor=").append(i).append(", timeProposal=").append(s).append(", attendees=").append(s1).append(", selectedProposalIndex=").append(j).append(", mode=").append(s2).append(", account=").append(s3).append(", originalEventStartTime=").append(l).append(", originalEventEndTime=").append(l1).append(", responseStatus=").append(s4).append(", calendarId=").append(s5).append(", eventId=").append(s6).append(", eventKey=").append(s7).append("}").toString();
    }
}
