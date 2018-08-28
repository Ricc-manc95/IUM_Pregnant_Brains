// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            SingleEventTime

public abstract class RecurringTimes
    implements Parcelable
{
    public static abstract class Builder
    {

        public abstract RecurringTimes build();

        public abstract Builder setConsiderExceptions(boolean flag);

        public abstract Builder setFirstEventTime(SingleEventTime singleeventtime);

        public abstract Builder setRecurrenceOption(int i);

        public abstract Builder setRecurrenceRule(String s);

        public abstract Builder setTimezone(String s);

        public Builder()
        {
        }
    }


    public RecurringTimes()
    {
    }

    public abstract boolean getConsiderExceptions();

    public abstract SingleEventTime getFirstEventTime();

    public abstract int getRecurrenceOption();

    public abstract String getRecurrenceRule();

    public abstract String getTimezone();

    public abstract Builder toBuilder();
}
