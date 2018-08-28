// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model.edit;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.CalendarListHolder;
import com.google.android.calendar.newapi.model.CalendarModification;
import com.google.android.calendar.newapi.model.ColorModification;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.TitleModification;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.model.ViewTypeHolder;
import com.google.android.calendar.newapi.model.VisibilityModification;
import com.google.android.calendar.timely.TimelineItem;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.model.edit:
//            EditScreenModel, EventModificationsHolder

public abstract class BasicEditScreenModel extends EditScreenModel
    implements CalendarListHolder, CalendarModification, ColorModification, EventHolder, TimeZoneHolder, TitleModification, ViewTypeHolder, VisibilityModification, EventModificationsHolder
{

    public CalendarList calendarList;
    public EventModifications eventModifications;
    private int fallbackColor;

    public BasicEditScreenModel()
    {
        fallbackColor = -1;
    }

    public BasicEditScreenModel(Parcel parcel)
    {
        fallbackColor = -1;
        EventModifications eventmodifications = (EventModifications)parcel.readParcelable(com/google/android/calendar/api/event/EventModifications.getClassLoader());
        if (eventmodifications != null)
        {
            setEventModifications(eventmodifications);
        }
        calendarList = (CalendarList)parcel.readParcelable(com/google/android/calendar/newapi/model/CalendarList.getClassLoader());
        fallbackColor = parcel.readInt();
    }

    public BasicEditScreenModel(EventModifications eventmodifications, CalendarList calendarlist)
    {
        fallbackColor = -1;
        setEventModifications(eventmodifications);
        calendarList = calendarlist;
    }

    public final Account getAccount()
    {
        return getCalendarListEntry().getDescriptor().account;
    }

    public final CalendarList getCalendarList()
    {
        return calendarList;
    }

    public CalendarListEntry getCalendarListEntry()
    {
        return eventModifications.getCalendarListEntry();
    }

    public EntityColor getColor()
    {
        return eventModifications.getColor();
    }

    public final String getDefaultTimeZoneId(Context context)
    {
        return TimeZone.getTimeZone(Utils.getTimeZoneId(context)).getID();
    }

    public final Event getEvent()
    {
        return eventModifications;
    }

    public final EventModifications getEventModifications()
    {
        return eventModifications;
    }

    public final String getTimeZoneId(Context context)
    {
        String s1 = eventModifications.getTimeZoneId();
        String s = s1;
        if (TextUtils.isEmpty(s1))
        {
            s = TimeZone.getTimeZone(Utils.getTimeZoneId(context)).getID();
        }
        return s;
    }

    public String getTitle()
    {
        return eventModifications.getSummary();
    }

    public int getVisibility()
    {
        return eventModifications.getVisibility();
    }

    public boolean isModified()
    {
        return eventModifications != null && eventModifications.isModified();
    }

    public boolean isNew()
    {
        return eventModifications.isNewEvent();
    }

    public void mergeModel(BasicViewScreenModel basicviewscreenmodel)
    {
        if (((ViewScreenModel) (basicviewscreenmodel)).timelineItem != null)
        {
            fallbackColor = ((ViewScreenModel) (basicviewscreenmodel)).timelineItem.getColor();
        }
    }

    public void mergeModel(EditScreenModel editscreenmodel)
    {
        editscreenmodel = (BasicEditScreenModel)editscreenmodel;
        setEventModifications(((BasicEditScreenModel) (editscreenmodel)).eventModifications);
        calendarList = ((BasicEditScreenModel) (editscreenmodel)).calendarList;
    }

    public volatile void mergeModel(Object obj)
    {
        mergeModel((BasicViewScreenModel)obj);
    }

    public void setColorOverride(EventColor eventcolor)
    {
        eventModifications.setColorOverride(eventcolor);
    }

    public void setEventModifications(EventModifications eventmodifications)
    {
        eventModifications = eventmodifications;
    }

    public void setTitle(String s)
    {
        eventModifications.setSummary(s);
    }

    public void setVisibility(int i)
    {
        eventModifications.setVisibility(i);
    }

    public void switchCalendar(CalendarListEntry calendarlistentry)
    {
        eventModifications.switchCalendar(calendarlistentry);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(eventModifications, i);
        parcel.writeParcelable(calendarList, i);
        parcel.writeInt(fallbackColor);
    }
}
