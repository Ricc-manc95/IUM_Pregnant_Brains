// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import com.google.android.calendar.event.conference.PhoneNumberDetails;
import java.util.Set;

public interface ConferenceEvent
{

    public abstract Set getConferenceAccessTokens();

    public abstract PhoneNumberDetails getLocalPhoneNumber();
}
