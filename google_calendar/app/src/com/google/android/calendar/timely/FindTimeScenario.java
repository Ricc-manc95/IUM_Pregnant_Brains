// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;

interface FindTimeScenario
{

    public abstract boolean isEnabled(Context context, CalendarListEntry calendarlistentry);
}
