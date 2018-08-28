// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.accounts.Account;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.widget.ImageView;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.ContactId;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.newapi.image.helper.HeaderAttributeImageHelper;
import com.google.android.calendar.prefs.PrefService;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineHoliday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.utils.account.PrimaryAccountSelector;
import com.google.common.util.concurrent.FutureCallback;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            ViewScreenModel, AccountHolder, AllDayHolder, AvailabilityHolder, 
//            CalendarHolder, CalendarListEntryHolder, EventHolder, GooglePlusHolder, 
//            HolidayHolder, PermissionHolder, RecurrenceHolder, TimeHolder, 
//            VisibilityHolder, VisibleCalendarsHolder

public abstract class BasicViewScreenModel extends ViewScreenModel
    implements AccountHolder, AllDayHolder, AvailabilityHolder, CalendarHolder, CalendarListEntryHolder, EventHolder, GooglePlusHolder, HolidayHolder, PermissionHolder, RecurrenceHolder, TimeHolder, VisibilityHolder, VisibleCalendarsHolder
{

    public Event event;
    public EventPermissions permissions;
    public int visibleCalendars;

    protected BasicViewScreenModel(Parcel parcel)
    {
        super(parcel);
        visibleCalendars = -1;
        Event event1 = (Event)parcel.readParcelable(com/google/android/calendar/api/event/Event.getClassLoader());
        event = event1;
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            permissions = CalendarApi.EventPermissionsFactory.create(event1);
        }
        visibleCalendars = parcel.readInt();
    }

    public BasicViewScreenModel(Event event1, TimelineItem timelineitem, int i)
    {
        super(timelineitem);
        visibleCalendars = -1;
        event = event1;
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            permissions = CalendarApi.EventPermissionsFactory.create(event1);
        }
        visibleCalendars = i;
    }

    public BasicViewScreenModel(TimelineItem timelineitem)
    {
        super(timelineitem);
        visibleCalendars = -1;
    }

    public Account getAccount()
    {
        return event.getCalendar().account;
    }

    public final int getAvailability()
    {
        return event.getAvailability();
    }

    public Drawable getBackgroundDrawable(Context context, FutureCallback futurecallback)
    {
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return super.getBackgroundDrawable(context, futurecallback);
        }
        context = new HeaderAttributeImageHelper(context, super.timelineItem, futurecallback);
        futurecallback = ((ImageHelper) (context)).view;
        if (futurecallback == null)
        {
            return null;
        } else
        {
            context.bind();
            return futurecallback.getDrawable();
        }
    }

    public final CalendarListEntry getCalendarListEntry()
    {
        return event.getCalendarListEntry();
    }

    public int getColor(Context context)
    {
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return super.getColor(context);
        }
        if (isHolidayEvent())
        {
            if (PrefService.instance == null)
            {
                if (PrimaryAccountSelector.instance == null)
                {
                    PrimaryAccountSelector.instance = new PrimaryAccountSelector(context);
                }
                PrefService.instance = new PrefService(PrimaryAccountSelector.instance);
            }
            return PrefService.instance.holidaysColor.getValue();
        } else
        {
            return event.getCalendarListEntry().getColor().getValue();
        }
    }

    public final long getEnd(Context context)
    {
        if (event.isEndTimeUnspecified())
        {
            return event.getStartMillis();
        } else
        {
            return event.getEndMillis();
        }
    }

    public final Event getEvent()
    {
        return event;
    }

    public final EventPermissions getPermissions()
    {
        return permissions;
    }

    public final Recurrence getRecurrence()
    {
        Event event1 = event;
        boolean flag;
        if (event1.isRecurring() && !event1.getRecurrence().rrules.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return null;
        } else
        {
            return event.getRecurrence();
        }
    }

    public final long getStart(Context context)
    {
        return event.getStartMillis();
    }

    public String getTitle()
    {
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return event.getSummary();
        } else
        {
            return super.getTitle();
        }
    }

    public int getVisibility()
    {
        return event.getVisibility();
    }

    public final int getVisibleCalendarsCount()
    {
        return visibleCalendars;
    }

    public boolean isAllDay()
    {
        return event.isAllDayEvent();
    }

    public boolean isEditable()
    {
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && !permissions.isReadOnly();
    }

    public final boolean isGooglePlusEvent()
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (event != null)
        {
            flag = flag1;
            if (event.getOrganizer() != null)
            {
                flag = flag1;
                if (event.getOrganizer().contactId != null)
                {
                    ContactId contactid = event.getOrganizer().contactId;
                    flag = flag1;
                    if (contactid.namespace.equals("com.google"))
                    {
                        flag = flag1;
                        if (contactid.identity.startsWith("gprofile:"))
                        {
                            flag = true;
                        }
                    }
                }
            }
        }
        return flag;
    }

    public final boolean isHolidayEvent()
    {
        if (!(super.timelineItem instanceof TimelineEvent))
        {
            return false;
        }
        TimelineEvent timelineevent = (TimelineEvent)super.timelineItem;
        return (timelineevent instanceof TimelineHoliday) || "holiday@group.v.calendar.google.com".equals(timelineevent.ownerAccount);
    }

    public boolean isSmartMailEvent()
    {
        return false;
    }

    public void mergeModel(ViewScreenModel viewscreenmodel)
    {
        super.mergeModel(viewscreenmodel);
        viewscreenmodel = (BasicViewScreenModel)viewscreenmodel;
        setEvent(((BasicViewScreenModel) (viewscreenmodel)).event);
        visibleCalendars = ((BasicViewScreenModel) (viewscreenmodel)).visibleCalendars;
    }

    public final void setEvent(Event event1)
    {
        event = event1;
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            permissions = CalendarApi.EventPermissionsFactory.create(event1);
        }
    }

    public boolean showSimplifiedScreen()
    {
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (!event.getCalendarListEntry().isPrimary())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(event, i);
        parcel.writeInt(visibleCalendars);
    }
}
