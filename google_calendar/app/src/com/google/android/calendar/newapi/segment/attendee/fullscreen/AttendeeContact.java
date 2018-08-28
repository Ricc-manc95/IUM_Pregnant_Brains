// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.os.Parcelable;
import com.google.android.calendar.avatar.ContactInfo;

abstract class AttendeeContact
    implements Parcelable
{

    AttendeeContact()
    {
    }

    public abstract ContactInfo getContact();

    public abstract Type getType();
}
