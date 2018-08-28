// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.calendar.api.event.EventKey;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            $AutoValue_ProposeNewTimeState, ProposeNewTimeState, TimeProposal

final class AutoValue_ProposeNewTimeState extends $AutoValue_ProposeNewTimeState
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_ProposeNewTimeState(int i, TimeProposal timeproposal, List list, int j, ProposeNewTimeState.Mode mode, Account account, long l, long l1, com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, String s, String s1, EventKey eventkey)
    {
        super(i, timeproposal, list, j, mode, account, l, l1, responsestatus, s, s1, eventkey);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(getEventColor());
        parcel.writeParcelable(getTimeProposal(), i);
        parcel.writeList(getAttendees());
        parcel.writeInt(getSelectedProposalIndex());
        parcel.writeString(getMode().name());
        parcel.writeParcelable(getAccount(), i);
        parcel.writeLong(getOriginalEventStartTime());
        parcel.writeLong(getOriginalEventEndTime());
        if (getResponseStatus() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getResponseStatus().name());
        }
        if (getCalendarId() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getCalendarId());
        }
        if (getEventId() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getEventId());
        }
        parcel.writeParcelable(getEventKey(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            TimeProposal timeproposal = (TimeProposal)parcel.readParcelable(com/google/android/apps/calendar/proposenewtime/state/TimeProposal.getClassLoader());
            java.util.ArrayList arraylist = parcel.readArrayList(com/google/android/apps/calendar/proposenewtime/state/Attendee.getClassLoader());
            int j = parcel.readInt();
            ProposeNewTimeState.Mode mode = ProposeNewTimeState.Mode.valueOf(parcel.readString());
            Account account = (Account)parcel.readParcelable(android/accounts/Account.getClassLoader());
            long l = parcel.readLong();
            long l1 = parcel.readLong();
            com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus;
            String s;
            String s1;
            if (parcel.readInt() == 0)
            {
                responsestatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.valueOf(parcel.readString());
            } else
            {
                responsestatus = null;
            }
            if (parcel.readInt() == 0)
            {
                s = parcel.readString();
            } else
            {
                s = null;
            }
            if (parcel.readInt() == 0)
            {
                s1 = parcel.readString();
            } else
            {
                s1 = null;
            }
            return new AutoValue_ProposeNewTimeState(i, timeproposal, arraylist, j, mode, account, l, l1, responsestatus, s, s1, (EventKey)parcel.readParcelable(com/google/android/calendar/api/event/EventKey.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ProposeNewTimeState[i];
        }

        _cls1()
        {
        }
    }

}
