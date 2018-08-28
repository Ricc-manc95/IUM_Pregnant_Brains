// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcelable;
import com.google.android.calendar.api.color.CalendarColor;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarAccessLevel, CalendarDescriptor

public interface CalendarListEntry
    extends Parcelable
{

    public abstract boolean canOrganizerRespond();

    public abstract CalendarAccessLevel getAccessLevel();

    public abstract List getAllowedAvailabilityValues();

    public abstract List getAllowedConferenceTypes();

    public abstract List getCategories();

    public abstract CalendarColor getColor();

    public abstract List getDefaultNotifications(int i);

    public abstract CalendarDescriptor getDescriptor();

    public abstract String getDisplayName();

    public abstract boolean isDeleted();

    public abstract boolean isPotentiallyShared();

    public abstract boolean isPrimary();

    public abstract boolean isSyncEnabled();

    public abstract boolean isVisible();
}
