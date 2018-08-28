// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.account.AccountUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalendarList
    implements Parcelable
{
    static final class EntryComparator
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            byte byte0 = 2;
            boolean flag = true;
            obj = (CalendarListEntry)obj;
            obj1 = (CalendarListEntry)obj1;
            Account account = ((CalendarListEntry) (obj)).getDescriptor().account;
            Account account1 = ((CalendarListEntry) (obj1)).getDescriptor().account;
            int i;
            if (AccountUtil.isGoogleAccount(account1))
            {
                i = 2;
            } else
            if (AccountUtil.isExchangeAccount(account1))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (!AccountUtil.isGoogleAccount(account))
            {
                if (AccountUtil.isExchangeAccount(account))
                {
                    byte0 = 1;
                } else
                {
                    byte0 = 0;
                }
            }
            i -= byte0;
            if (i == 0)
            {
                i = account.name.compareToIgnoreCase(account1.name);
            }
            if (i == 0)
            {
                int j;
                if (((CalendarListEntry) (obj1)).isPrimary())
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (((CalendarListEntry) (obj)).isPrimary())
                {
                    j = ((flag) ? 1 : 0);
                } else
                {
                    j = 0;
                }
                j = i - j;
                i = j;
                if (j == 0)
                {
                    return ((CalendarListEntry) (obj)).getDisplayName().compareToIgnoreCase(((CalendarListEntry) (obj1)).getDisplayName());
                }
            }
            return i;
        }

        EntryComparator()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final List calendars;
    public final Map fatSupportMap;

    protected CalendarList(Parcel parcel)
    {
        Parcelable aparcelable[] = parcel.readParcelableArray(com/google/android/calendar/api/calendarlist/CalendarListEntry.getClassLoader());
        ArrayList arraylist = new ArrayList();
        int k = aparcelable.length;
        for (int i = 0; i < k; i++)
        {
            arraylist.add((CalendarListEntry)aparcelable[i]);
        }

        if (parcel.readInt() != 1)
        {
            parcel = null;
        } else
        {
            HashMap hashmap = new HashMap();
            int l = parcel.readInt();
            for (int j = 0; j < l; j++)
            {
                hashmap.put((CalendarDescriptor)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarDescriptor.getClassLoader()), (Boolean)parcel.readValue(java/lang/Boolean.getClassLoader()));
            }

            parcel = hashmap;
        }
        this(((List) (arraylist)), ((Map) (parcel)));
    }

    private CalendarList(List list, Map map)
    {
        calendars = Collections.unmodifiableList(list);
        if (map == null)
        {
            list = null;
        } else
        {
            list = Collections.unmodifiableMap(map);
        }
        fatSupportMap = list;
    }

    public static CalendarList create(List list, Map map)
    {
        if (list == null)
        {
            throw new NullPointerException();
        } else
        {
            Collections.sort(list, new EntryComparator());
            return new CalendarList(list, map);
        }
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
        obj = (CalendarList)obj;
        Object obj1 = calendars;
        List list = ((CalendarList) (obj)).calendars;
        boolean flag;
        if (obj1 == list || obj1 != null && obj1.equals(list))
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
        obj1 = fatSupportMap;
        obj = ((CalendarList) (obj)).fatSupportMap;
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

    public final CalendarListEntry findEntry(String s)
    {
        for (int i = 0; i < calendars.size(); i++)
        {
            CalendarListEntry calendarlistentry = (CalendarListEntry)calendars.get(i);
            if (s.equals(calendarlistentry.getDescriptor().calendarId))
            {
                return calendarlistentry;
            }
        }

        return null;
    }

    public final CalendarListEntry getDefaultEntry()
    {
        CalendarDescriptor calendardescriptor = CalendarProperties.getDefaultCalendarId();
        for (int i = 0; i < calendars.size(); i++)
        {
            if (calendardescriptor != null && ((CalendarListEntry)calendars.get(i)).getDescriptor().equals(calendardescriptor))
            {
                return (CalendarListEntry)calendars.get(i);
            }
        }

        if (!calendars.isEmpty())
        {
            return (CalendarListEntry)calendars.get(0);
        } else
        {
            return null;
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            calendars, fatSupportMap
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelableArray((CalendarListEntry[])calendars.toArray(new CalendarListEntry[0]), i);
        if (fatSupportMap == null)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        parcel.writeInt(i);
        if (fatSupportMap != null)
        {
            parcel.writeInt(fatSupportMap.size());
            java.util.Map.Entry entry;
            for (Iterator iterator = fatSupportMap.entrySet().iterator(); iterator.hasNext(); parcel.writeValue(entry.getValue()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                parcel.writeParcelable((Parcelable)entry.getKey(), 0);
            }

        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new CalendarList(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new CalendarList[i];
        }

        _cls1()
        {
        }
    }

}
