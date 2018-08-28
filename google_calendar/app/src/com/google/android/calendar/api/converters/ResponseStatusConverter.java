// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.converters;


public final class ResponseStatusConverter
{

    public static com.google.android.calendar.api.event.attendee.Response.ResponseStatus providerToApi(int i)
    {
        switch (i)
        {
        case 3: // '\003'
        default:
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;

        case 1: // '\001'
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;

        case 4: // '\004'
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE;

        case 2: // '\002'
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;
        }
    }
}
