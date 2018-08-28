// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.analytics;

import android.accounts.Account;
import android.content.Context;

public interface AnalyticsLogger
{

    public abstract void addAccountTypeCustomDimensions(Context context, Account account);

    public abstract void logClearcutEvent(com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype);

    public abstract void logClearcutEvent(com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype, String s);

    public abstract void sendAdditionalEventsOnApplicationOpen(Context context, Iterable iterable);

    public abstract void setAdditionalDimensionsForApplicationOpenEvent(Context context, Iterable iterable);

    public abstract void setCustomDimension(Context context, int i, String s);

    public abstract void setCustomMetric(Context context, int i, long l);

    public abstract void trackEvent(Context context, String s, String s1);

    public abstract void trackEvent(Context context, String s, String s1, String s2, Long long1);

    public abstract void trackTiming(Context context, String s, long l, String s1, String s2);

    public abstract void trackView(Context context, String s);
}
