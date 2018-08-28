// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.common.FieldModification;
import com.google.android.calendar.api.event.notification.Notification;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListEntryModifications, CalendarListEntryImpl, CalendarListEntry, CalendarAccessLevel, 
//            CalendarDescriptor

public final class CalendarListEntryModificationsImpl
    implements CalendarListEntryModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private FieldModification allDayEventsNotifications;
    private FieldModification color;
    private FieldModification displayName;
    private FieldModification isSyncEnabled;
    private FieldModification isVisible;
    private final CalendarListEntry original;
    private FieldModification timedEventsNotifications;

    CalendarListEntryModificationsImpl(Parcel parcel)
    {
        isSyncEnabled = new FieldModification();
        isVisible = new FieldModification();
        timedEventsNotifications = new FieldModification();
        allDayEventsNotifications = new FieldModification();
        displayName = new FieldModification();
        color = new FieldModification();
        original = (CalendarListEntry)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarListEntryImpl.getClassLoader());
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            isSyncEnabled = new com.google.android.calendar.api.common.FieldModification._cls1((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            isVisible = new com.google.android.calendar.api.common.FieldModification._cls1((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            ArrayList arraylist = new ArrayList();
            parcel.readList(arraylist, com/google/android/calendar/api/event/notification/Notification.getClassLoader());
            timedEventsNotifications = new com.google.android.calendar.api.common.FieldModification._cls1(arraylist);
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            ArrayList arraylist1 = new ArrayList();
            parcel.readList(arraylist1, com/google/android/calendar/api/event/notification/Notification.getClassLoader());
            allDayEventsNotifications = new com.google.android.calendar.api.common.FieldModification._cls1(arraylist1);
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            displayName = new com.google.android.calendar.api.common.FieldModification._cls1(parcel.readString());
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            color = new com.google.android.calendar.api.common.FieldModification._cls1((CalendarColor)parcel.readParcelable(com/google/android/calendar/api/color/CalendarColor.getClassLoader()));
        }
    }

    CalendarListEntryModificationsImpl(CalendarListEntry calendarlistentry)
    {
        isSyncEnabled = new FieldModification();
        isVisible = new FieldModification();
        timedEventsNotifications = new FieldModification();
        allDayEventsNotifications = new FieldModification();
        displayName = new FieldModification();
        color = new FieldModification();
        original = calendarlistentry;
    }

    public final boolean areDefaultNotificationsModified(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException();

        case 1: // '\001'
            return timedEventsNotifications.shouldModify();

        case 2: // '\002'
            return allDayEventsNotifications.shouldModify();
        }
    }

    public final boolean canOrganizerRespond()
    {
        return original.canOrganizerRespond();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final CalendarAccessLevel getAccessLevel()
    {
        return original.getAccessLevel();
    }

    public final List getAllowedAvailabilityValues()
    {
        return original.getAllowedAvailabilityValues();
    }

    public final List getAllowedConferenceTypes()
    {
        return original.getAllowedConferenceTypes();
    }

    public final List getCategories()
    {
        return original.getCategories();
    }

    public final CalendarColor getColor()
    {
        if (color.shouldModify())
        {
            return (CalendarColor)color.getModificationValue();
        } else
        {
            return original.getColor();
        }
    }

    public final List getDefaultNotifications(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException();

        case 1: // '\001'
            if (timedEventsNotifications.shouldModify())
            {
                return (List)timedEventsNotifications.getModificationValue();
            } else
            {
                return original.getDefaultNotifications(i);
            }

        case 2: // '\002'
            break;
        }
        if (allDayEventsNotifications.shouldModify())
        {
            return (List)allDayEventsNotifications.getModificationValue();
        } else
        {
            return original.getDefaultNotifications(i);
        }
    }

    public final CalendarDescriptor getDescriptor()
    {
        return original.getDescriptor();
    }

    public final String getDisplayName()
    {
        if (displayName.shouldModify())
        {
            return (String)displayName.getModificationValue();
        } else
        {
            return original.getDisplayName();
        }
    }

    public final CalendarListEntry getOriginal()
    {
        return original;
    }

    public final boolean isColorModified()
    {
        return color.shouldModify();
    }

    public final boolean isDeleted()
    {
        return original.isDeleted();
    }

    public final boolean isDisplayNameModified()
    {
        return displayName.shouldModify();
    }

    public final boolean isModified()
    {
        return isVisible.shouldModify() || isSyncEnabled.shouldModify() || areDefaultNotificationsModified(1) || areDefaultNotificationsModified(2) || displayName.shouldModify() || color.shouldModify();
    }

    public final boolean isPotentiallyShared()
    {
        return original.isPotentiallyShared();
    }

    public final boolean isPrimary()
    {
        return original.isPrimary();
    }

    public final boolean isSyncEnabled()
    {
        if (isSyncEnabled.shouldModify())
        {
            return ((Boolean)isSyncEnabled.getModificationValue()).booleanValue();
        } else
        {
            return original.isSyncEnabled();
        }
    }

    public final boolean isSyncEnabledModified()
    {
        return isSyncEnabled.shouldModify();
    }

    public final boolean isVisible()
    {
        if (isVisible.shouldModify())
        {
            return ((Boolean)isVisible.getModificationValue()).booleanValue();
        } else
        {
            return original.isVisible();
        }
    }

    public final boolean isVisibleModified()
    {
        return isVisible.shouldModify();
    }

    public final CalendarListEntryModifications setColor(CalendarColor calendarcolor)
    {
        color = new com.google.android.calendar.api.common.FieldModification._cls1(calendarcolor);
        return this;
    }

    public final CalendarListEntryModifications setDefaultNotifications(int i, List list)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException();

        case 1: // '\001'
            timedEventsNotifications = new com.google.android.calendar.api.common.FieldModification._cls1(list);
            return this;

        case 2: // '\002'
            allDayEventsNotifications = new com.google.android.calendar.api.common.FieldModification._cls1(list);
            break;
        }
        return this;
    }

    public final CalendarListEntryModifications setDisplayName(String s)
    {
        displayName = new com.google.android.calendar.api.common.FieldModification._cls1(s);
        return this;
    }

    public final CalendarListEntryModifications setIsSyncEnabled(boolean flag)
    {
        isSyncEnabled = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        return this;
    }

    public final CalendarListEntryModifications setIsVisible(boolean flag)
    {
        isVisible = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(original, i);
        parcel.writeValue(Boolean.valueOf(isSyncEnabled.shouldModify()));
        if (isSyncEnabled.shouldModify())
        {
            parcel.writeValue(Boolean.valueOf(isSyncEnabled()));
        }
        parcel.writeValue(Boolean.valueOf(isVisible.shouldModify()));
        if (isVisible.shouldModify())
        {
            parcel.writeValue(Boolean.valueOf(isVisible()));
        }
        parcel.writeValue(Boolean.valueOf(areDefaultNotificationsModified(1)));
        if (areDefaultNotificationsModified(1))
        {
            parcel.writeList(getDefaultNotifications(1));
        }
        parcel.writeValue(Boolean.valueOf(areDefaultNotificationsModified(2)));
        if (areDefaultNotificationsModified(2))
        {
            parcel.writeList(getDefaultNotifications(2));
        }
        parcel.writeValue(Boolean.valueOf(displayName.shouldModify()));
        if (displayName.shouldModify())
        {
            parcel.writeString((String)displayName.getModificationValue());
        }
        parcel.writeValue(Boolean.valueOf(color.shouldModify()));
        if (color.shouldModify())
        {
            parcel.writeParcelable((Parcelable)color.getModificationValue(), 0);
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new CalendarListEntryModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new CalendarListEntryModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
