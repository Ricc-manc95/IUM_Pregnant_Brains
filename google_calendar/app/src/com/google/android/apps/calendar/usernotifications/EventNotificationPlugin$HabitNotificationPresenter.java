// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.habit.Habit;

public interface 
{

    public abstract void hide(UserNotification usernotification);

    public abstract void show(Habit habit, Event event, UserNotification usernotification);
}
