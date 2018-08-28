// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.utils;


final class Q
{

    public static final int $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[];

    static 
    {
        $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus = new int[com.google.android.calendar.api.event.attendee.s.values().length];
        try
        {
            $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[com.google.android.calendar.api.event.attendee.s.ACCEPTED.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[com.google.android.calendar.api.event.attendee.s.DECLINED.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[com.google.android.calendar.api.event.attendee.s.TENTATIVE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror)
        {
            return;
        }
    }
}
