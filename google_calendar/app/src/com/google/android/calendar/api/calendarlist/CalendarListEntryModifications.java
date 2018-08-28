// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcelable;
import com.google.android.calendar.api.color.CalendarColor;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListEntry

public interface CalendarListEntryModifications
    extends Parcelable, CalendarListEntry
{

    public abstract boolean areDefaultNotificationsModified(int i);

    public abstract CalendarListEntry getOriginal();

    public abstract boolean isColorModified();

    public abstract boolean isDisplayNameModified();

    public abstract boolean isModified();

    public abstract boolean isSyncEnabledModified();

    public abstract boolean isVisibleModified();

    public abstract CalendarListEntryModifications setColor(CalendarColor calendarcolor);

    public abstract CalendarListEntryModifications setDefaultNotifications(int i, List list);

    public abstract CalendarListEntryModifications setDisplayName(String s);

    public abstract CalendarListEntryModifications setIsSyncEnabled(boolean flag);

    public abstract CalendarListEntryModifications setIsVisible(boolean flag);
}
