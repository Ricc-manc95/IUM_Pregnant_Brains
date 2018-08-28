// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            AutoReply

public abstract class OutOfOffice
    implements Parcelable
{
    public static abstract class Builder
    {

        public abstract OutOfOffice build();

        public abstract Builder setAutoDeclineEnabled(boolean flag);

        public abstract Builder setAutoReply(AutoReply autoreply);

        public abstract Builder setCalendarDeclineMessage(String s);

        public Builder()
        {
        }
    }


    public OutOfOffice()
    {
    }

    public abstract AutoReply getAutoReply();

    public abstract String getCalendarDeclineMessage();

    public abstract boolean isAutoDeclineEnabled();

    public abstract Builder toBuilder();
}
