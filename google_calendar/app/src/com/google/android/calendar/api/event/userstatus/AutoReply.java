// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;

import android.os.Parcelable;

public abstract class AutoReply
    implements Parcelable
{
    public static abstract class Builder
    {

        public abstract AutoReply build();

        public abstract Builder setBody(String s);

        public abstract Builder setEnabled(boolean flag);

        public abstract Builder setRestrictToContacts(boolean flag);

        public abstract Builder setRestrictToDomain(boolean flag);

        public abstract Builder setSubject(String s);

        public Builder()
        {
        }
    }


    public AutoReply()
    {
    }

    public abstract String getBody();

    public abstract String getSubject();

    public abstract boolean isEnabled();

    public abstract boolean isRestrictToContacts();

    public abstract boolean isRestrictToDomain();
}
