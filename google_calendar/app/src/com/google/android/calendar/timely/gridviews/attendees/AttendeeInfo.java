// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.attendees;


public interface AttendeeInfo
{

    public abstract String getDisplayName();

    public abstract String getEmail();

    public abstract String getSourceAccount();

    public abstract boolean isDisabled();
}
