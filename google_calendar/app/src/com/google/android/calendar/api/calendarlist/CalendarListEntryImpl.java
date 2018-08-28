// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcel;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.common.ParcelHelper;
import com.google.android.calendar.api.common.TimeZoneHelper;
import com.google.android.calendar.api.event.notification.Notification;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListEntry, CalendarDescriptor, CalendarAccessLevel, CalendarCategory

public class CalendarListEntryImpl
    implements CalendarListEntry
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final CalendarAccessLevel accessLevel;
    private final List allowedAvailabilityValues;
    private final List allowedConferenceTypeValues;
    private final List allowedNotificationMethodValues;
    private final boolean canOrganizerRespond;
    private final List categories;
    private final CalendarColor color;
    private final List defaultAllDayNotifications;
    private final List defaultTimedNotifications;
    private final boolean deleted;
    private final CalendarDescriptor descriptor;
    private final String displayName;
    private final boolean isPotentiallyShared;
    private final int maxNumberOfNotifications;
    private final boolean primary;
    private final boolean syncEnabled;
    private final String timeZoneId;
    private final boolean visible;

    public CalendarListEntryImpl(Parcel parcel)
    {
        CalendarDescriptor calendardescriptor = (CalendarDescriptor)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarDescriptor.getClassLoader());
        boolean flag5 = ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue();
        String s = parcel.readString();
        CalendarColor calendarcolor = (CalendarColor)parcel.readParcelable(com/google/android/calendar/api/color/CalendarColor.getClassLoader());
        String s1;
        Notification anotification[];
        Notification anotification1[];
        CalendarAccessLevel calendaraccesslevel;
        List list;
        List list1;
        List list2;
        int i;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        if (parcel.readByte() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (parcel.readByte() == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (parcel.readByte() == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (parcel.readByte() == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        s1 = parcel.readString();
        anotification = (Notification[])parcel.createTypedArray(Notification.CREATOR);
        anotification1 = (Notification[])parcel.createTypedArray(Notification.CREATOR);
        if (parcel.readByte() == 1)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        calendaraccesslevel = (CalendarAccessLevel)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarAccessLevel.getClassLoader());
        i = parcel.readInt();
        list = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        list1 = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        list2 = ParcelHelper.unparcelUnmodifiableIntegerList(parcel);
        parcel = parcel.createTypedArrayList(CalendarCategory.CREATOR);
        if (parcel.isEmpty())
        {
            parcel = Collections.emptyList();
        } else
        {
            parcel = Collections.unmodifiableList(parcel);
        }
        this(calendardescriptor, flag5, s, calendarcolor, flag, flag1, flag2, flag3, s1, anotification, anotification1, flag4, calendaraccesslevel, i, list, list1, list2, ((List) (parcel)));
    }

    public CalendarListEntryImpl(CalendarDescriptor calendardescriptor, boolean flag, String s, CalendarColor calendarcolor, boolean flag1, boolean flag2, boolean flag3, 
            boolean flag4, String s1, Notification anotification[], Notification anotification1[], boolean flag5, CalendarAccessLevel calendaraccesslevel, int i, 
            List list, List list1, List list2, List list3)
    {
        if (calendardescriptor == null)
        {
            throw new NullPointerException();
        }
        descriptor = (CalendarDescriptor)calendardescriptor;
        primary = flag;
        if (s == null)
        {
            throw new NullPointerException();
        }
        displayName = (String)s;
        color = calendarcolor;
        visible = flag1;
        syncEnabled = flag2;
        isPotentiallyShared = flag3;
        deleted = flag4;
        boolean flag6;
        if (s1 == null || TimeZoneHelper.isValidTimeZoneId(s1))
        {
            flag6 = true;
        } else
        {
            flag6 = false;
        }
        if (!flag6)
        {
            throw new IllegalArgumentException();
        }
        timeZoneId = s1;
        if (anotification == null)
        {
            throw new NullPointerException();
        }
        if (((Notification[])anotification).length == 0)
        {
            calendardescriptor = Collections.emptyList();
        } else
        {
            calendardescriptor = Collections.unmodifiableList(Arrays.asList(anotification));
        }
        defaultTimedNotifications = calendardescriptor;
        if (anotification1 == null)
        {
            throw new NullPointerException();
        }
        if (((Notification[])anotification1).length == 0)
        {
            calendardescriptor = Collections.emptyList();
        } else
        {
            calendardescriptor = Collections.unmodifiableList(Arrays.asList(anotification1));
        }
        defaultAllDayNotifications = calendardescriptor;
        canOrganizerRespond = flag5;
        if (calendaraccesslevel == null)
        {
            throw new NullPointerException();
        }
        accessLevel = (CalendarAccessLevel)calendaraccesslevel;
        if (i >= 0)
        {
            flag6 = true;
        } else
        {
            flag6 = false;
        }
        if (!flag6)
        {
            throw new IllegalArgumentException();
        }
        maxNumberOfNotifications = i;
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (((List)list).isEmpty())
        {
            calendardescriptor = Collections.emptyList();
        } else
        {
            calendardescriptor = Collections.unmodifiableList(list);
        }
        allowedNotificationMethodValues = calendardescriptor;
        if (list1 == null)
        {
            throw new NullPointerException();
        }
        if (((List)list1).isEmpty())
        {
            calendardescriptor = Collections.emptyList();
        } else
        {
            calendardescriptor = Collections.unmodifiableList(list1);
        }
        allowedAvailabilityValues = calendardescriptor;
        if (list2 == null)
        {
            throw new NullPointerException();
        }
        if (((List)list2).isEmpty())
        {
            calendardescriptor = Collections.emptyList();
        } else
        {
            calendardescriptor = Collections.unmodifiableList(list2);
        }
        allowedConferenceTypeValues = calendardescriptor;
        if (list3 == null)
        {
            throw new NullPointerException();
        } else
        {
            categories = (List)list3;
            return;
        }
    }

    public final boolean canOrganizerRespond()
    {
        return canOrganizerRespond;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        obj = (CalendarListEntryImpl)obj;
        if (primary != ((CalendarListEntryImpl) (obj)).primary || visible != ((CalendarListEntryImpl) (obj)).visible || syncEnabled != ((CalendarListEntryImpl) (obj)).syncEnabled || isPotentiallyShared != ((CalendarListEntryImpl) (obj)).isPotentiallyShared || deleted != ((CalendarListEntryImpl) (obj)).deleted || canOrganizerRespond != ((CalendarListEntryImpl) (obj)).canOrganizerRespond || maxNumberOfNotifications != ((CalendarListEntryImpl) (obj)).maxNumberOfNotifications)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = descriptor;
        Object obj2 = ((CalendarListEntryImpl) (obj)).descriptor;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = displayName;
        obj2 = ((CalendarListEntryImpl) (obj)).displayName;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = color;
        obj2 = ((CalendarListEntryImpl) (obj)).color;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = timeZoneId;
        obj2 = ((CalendarListEntryImpl) (obj)).timeZoneId;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = defaultTimedNotifications;
        obj2 = ((CalendarListEntryImpl) (obj)).defaultTimedNotifications;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = defaultAllDayNotifications;
        obj2 = ((CalendarListEntryImpl) (obj)).defaultAllDayNotifications;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = accessLevel;
        obj2 = ((CalendarListEntryImpl) (obj)).accessLevel;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = allowedNotificationMethodValues;
        obj2 = ((CalendarListEntryImpl) (obj)).allowedNotificationMethodValues;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = allowedAvailabilityValues;
        obj2 = ((CalendarListEntryImpl) (obj)).allowedAvailabilityValues;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = allowedConferenceTypeValues;
        obj2 = ((CalendarListEntryImpl) (obj)).allowedConferenceTypeValues;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = categories;
        obj = ((CalendarListEntryImpl) (obj)).categories;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final CalendarAccessLevel getAccessLevel()
    {
        return accessLevel;
    }

    public final List getAllowedAvailabilityValues()
    {
        return allowedAvailabilityValues;
    }

    public final List getAllowedConferenceTypes()
    {
        return allowedConferenceTypeValues;
    }

    public final List getCategories()
    {
        return categories;
    }

    public final CalendarColor getColor()
    {
        return color;
    }

    public final List getDefaultNotifications(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Illegal notification category");

        case 1: // '\001'
            return defaultTimedNotifications;

        case 2: // '\002'
            return defaultAllDayNotifications;
        }
    }

    public final CalendarDescriptor getDescriptor()
    {
        return descriptor;
    }

    public final String getDisplayName()
    {
        return displayName;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            descriptor, Boolean.valueOf(primary), displayName, color, Boolean.valueOf(visible), Boolean.valueOf(syncEnabled), Boolean.valueOf(isPotentiallyShared), Boolean.valueOf(deleted), timeZoneId, defaultTimedNotifications, 
            defaultAllDayNotifications, Boolean.valueOf(canOrganizerRespond), accessLevel, Integer.valueOf(maxNumberOfNotifications), allowedNotificationMethodValues, allowedAvailabilityValues, allowedConferenceTypeValues, categories
        });
    }

    public final boolean isDeleted()
    {
        return deleted;
    }

    public final boolean isPotentiallyShared()
    {
        return isPotentiallyShared;
    }

    public final boolean isPrimary()
    {
        return primary;
    }

    public final boolean isSyncEnabled()
    {
        return syncEnabled;
    }

    public final boolean isVisible()
    {
        return visible;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeParcelable(descriptor, i);
        parcel.writeValue(Boolean.valueOf(primary));
        parcel.writeString(displayName);
        parcel.writeParcelable(color, i);
        int j;
        if (visible)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        if (syncEnabled)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        if (isPotentiallyShared)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        if (deleted)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        parcel.writeString(timeZoneId);
        parcel.writeTypedArray((Notification[])defaultTimedNotifications.toArray(new Notification[defaultTimedNotifications.size()]), i);
        parcel.writeTypedArray((Notification[])defaultAllDayNotifications.toArray(new Notification[defaultAllDayNotifications.size()]), i);
        if (canOrganizerRespond)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        parcel.writeParcelable(accessLevel, i);
        parcel.writeInt(maxNumberOfNotifications);
        parcel.writeList(allowedNotificationMethodValues);
        parcel.writeList(allowedAvailabilityValues);
        parcel.writeList(allowedConferenceTypeValues);
        parcel.writeTypedList(categories);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new CalendarListEntryImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new CalendarListEntryImpl[i];
        }

        _cls1()
        {
        }
    }

}
