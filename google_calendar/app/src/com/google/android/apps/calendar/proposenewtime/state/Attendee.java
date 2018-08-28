// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.os.Parcelable;
import com.google.android.calendar.timely.gridviews.attendees.AttendeeInfo;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            TimeProposal

public abstract class Attendee
    implements Parcelable, AttendeeInfo
{
    public static abstract class Builder
    {

        public abstract Attendee build();

        public abstract Builder setDisabled(boolean flag);

        public abstract Builder setDisplayName(String s);

        public abstract Builder setEmail(String s);

        public abstract Builder setProposal(TimeProposal timeproposal);

        public abstract Builder setSourceAccount(String s);

        public Builder()
        {
        }
    }


    public Attendee()
    {
    }

    public abstract String getDisplayName();

    public abstract String getEmail();

    public abstract TimeProposal getProposal();

    public abstract String getSourceAccount();

    public abstract boolean isDisabled();

    public abstract Builder toBuilder();
}
