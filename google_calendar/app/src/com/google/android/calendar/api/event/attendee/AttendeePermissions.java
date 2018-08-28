// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;


public interface AttendeePermissions
{

    public abstract boolean canAddAttendees();

    public abstract boolean canModifyResponseComment();

    public abstract boolean canModifyResponseStatus();

    public abstract boolean canProposeNewTime();

    public abstract boolean canRemoveAttendees();

    public abstract boolean isReadOnly();
}
