// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.content.Context;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.location.Address;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.event.ConferenceCallUtils;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.newapi.logging.AnalyticsViewType;
import com.google.android.calendar.newapi.utils.LegacyUtils;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.utils.account.AccountUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            BasicViewScreenModel, CalendarListHolder, ConferenceEvent, LocationHolder, 
//            CalendarList, ViewScreenModel

public class EventViewScreenModel extends BasicViewScreenModel
    implements CalendarListHolder, ConferenceEvent, LocationHolder
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private CalendarList calendarList;
    public PhoneNumberDetails localPhone;

    public EventViewScreenModel(Parcel parcel)
    {
        super(parcel);
        localPhone = (PhoneNumberDetails)parcel.readParcelable(com/google/android/calendar/event/conference/PhoneNumberDetails.getClassLoader());
        calendarList = (CalendarList)parcel.readParcelable(com/google/android/calendar/newapi/model/CalendarList.getClassLoader());
    }

    public EventViewScreenModel(Event event, TimelineEvent timelineevent, int i, PhoneNumberDetails phonenumberdetails, CalendarList calendarlist)
    {
        super(event, timelineevent, i);
        localPhone = phonenumberdetails;
        calendarList = calendarlist;
    }

    public EventViewScreenModel(TimelineEvent timelineevent)
    {
        super(timelineevent);
    }

    public CalendarList getCalendarList()
    {
        return calendarList;
    }

    public final String getCategory()
    {
        return "event";
    }

    public int getColor(Context context)
    {
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && !isHolidayEvent())
        {
            return super.event.getColor().getValue();
        } else
        {
            return super.getColor(context);
        }
    }

    public final Set getConferenceAccessTokens()
    {
        Set set = ConferenceCallUtils.parseAccessCode(super.event.getDescription());
        if (!super.event.getLocation().getEventLocations().isEmpty())
        {
            set.addAll(ConferenceCallUtils.parseAccessCode(((EventLocation)super.event.getLocation().getEventLocations().iterator().next()).name));
        }
        return set;
    }

    public final PhoneNumberDetails getLocalPhoneNumber()
    {
        return localPhone;
    }

    public final EventLocation getLocation()
    {
        EventLocation eventlocation;
label0:
        {
            Object obj = null;
            String s;
            for (s = ((TimelineEvent)super.timelineItem).location; TextUtils.isEmpty(s) || super.event.getLocation().getEventLocations().isEmpty();)
            {
                return null;
            }

            eventlocation = (EventLocation)super.event.getLocation().getEventLocations().iterator().next();
            boolean flag;
            if (LegacyUtils.isLegacyLocationOrEmpty(eventlocation) && !TextUtils.isEmpty(eventlocation.name))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if (eventlocation.address != null)
                {
                    obj = eventlocation.address.formattedAddress;
                }
                String s1 = eventlocation.name;
                if (!TextUtils.isEmpty(((CharSequence) (obj))) && s.contains(((CharSequence) (obj))))
                {
                    flag = true;
                } else
                if (!TextUtils.isEmpty(s1) && s.contains(s1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            obj = new com.google.android.calendar.api.event.location.EventLocation.Builder();
            if (s == null)
            {
                throw new NullPointerException();
            } else
            {
                obj.name = (String)s;
                return new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj)));
            }
        }
        return eventlocation;
    }

    protected final Class getTimelineItemClass()
    {
        return com/google/android/calendar/timely/TimelineEvent;
    }

    public final String getViewType()
    {
        if (isSmartMailEvent())
        {
            return "smart-mail";
        }
        if (isHolidayEvent())
        {
            return "holiday";
        }
        if (isGooglePlusEvent())
        {
            return "google-plus";
        } else
        {
            return AnalyticsViewType.fromEvent(super.event);
        }
    }

    public final boolean hasImage(Context context)
    {
        boolean flag1 = false;
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || AccountUtil.isGoogleAccount(getAccount()))
        {
            flag1 = super.hasImage(context);
        }
        return flag1;
    }

    public final boolean hideDetails()
    {
        boolean flag1 = true;
        if (super.event != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        CalendarAccessLevel calendaraccesslevel;
        calendaraccesslevel = getCalendarListEntry().getAccessLevel();
        flag = flag1;
        if (CalendarAccessLevel.FREEBUSY.equals(calendaraccesslevel)) goto _L4; else goto _L3
_L3:
        int i;
        i = getVisibility();
        if (!CalendarAccessLevel.READER.equals(calendaraccesslevel))
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (i == 2) goto _L4; else goto _L5
_L5:
        flag = flag1;
        if (i == 3) goto _L4; else goto _L6
_L6:
        return super.hideDetails();
    }

    public final boolean isAllDay()
    {
        return super.event.isAllDayEvent();
    }

    public void mergeModel(ViewScreenModel viewscreenmodel)
    {
        EventViewScreenModel eventviewscreenmodel = (EventViewScreenModel)viewscreenmodel;
        super.mergeModel(viewscreenmodel);
        localPhone = eventviewscreenmodel.localPhone;
        calendarList = eventviewscreenmodel.calendarList;
    }

    public void setCalendarList(CalendarList calendarlist)
    {
        calendarList = calendarlist;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(localPhone, i);
        parcel.writeParcelable(calendarList, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventViewScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventViewScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
