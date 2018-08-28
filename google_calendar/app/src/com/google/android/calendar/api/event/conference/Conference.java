// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcelable;

public abstract class Conference
    implements Parcelable
{
    public static abstract class Builder
    {

        public abstract Conference autoBuild();

        public abstract String getRegionCode();

        public abstract Builder setAccessCode(String s);

        public abstract Builder setEntryPointType(String s);

        public abstract Builder setGatewayAccess(int i);

        public abstract Builder setLabel(String s);

        public abstract Builder setMeetingCode(String s);

        public abstract Builder setName(String s);

        public abstract Builder setPassCode(String s);

        public abstract Builder setPasscode(String s);

        public abstract Builder setPassword(String s);

        public abstract Builder setPin(String s);

        public abstract Builder setRegionCode(String s);

        public abstract Builder setType(int i);

        public abstract Builder setUri(String s);

        public Builder()
        {
        }
    }


    public Conference()
    {
    }

    public static Builder builder()
    {
        return (new .AutoValue_Conference.Builder()).setType(0).setUri("").setName("").setPassCode("").setRegionCode("").setAccessCode("").setEntryPointType("unknown").setLabel("").setMeetingCode("").setPasscode("").setPassword("").setPin("").setGatewayAccess(0);
    }

    public abstract String getAccessCode();

    public abstract String getEntryPointType();

    public abstract int getGatewayAccess();

    public abstract String getLabel();

    public abstract String getMeetingCode();

    public abstract String getName();

    public abstract String getPassCode();

    public abstract String getPasscode();

    public abstract String getPassword();

    public abstract String getPin();

    public abstract String getRegionCode();

    public abstract int getType();

    public abstract String getUri();
}
