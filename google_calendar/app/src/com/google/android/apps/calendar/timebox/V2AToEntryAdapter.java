// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import android.accounts.Account;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.goals.common.GoalStoreUtils;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.event.V2AEventAdapter;
import com.google.android.calendar.api.event.V2AEventDescriptor;
import com.google.android.calendar.api.event.V2AEventKey;
import com.google.calendar.v2a.shared.storage.proto.DisplayTime;
import com.google.calendar.v2a.shared.storage.proto.EventBundle;
import com.google.calendar.v2a.shared.storage.proto.EventInstance;
import com.google.calendar.v2a.shared.storage.proto.InstanceTimes;
import com.google.common.base.Platform;
import com.google.protos.calendar.feapi.v1.Event;
import com.google.protos.calendar.feapi.v1.EventLocation;
import com.google.protos.calendar.feapi.v1.HabitInstanceData;
import com.google.protos.calendar.feapi.v1.PrivateEventData;
import com.google.protos.calendar.feapi.v1.StructuredLocation;
import com.google.protos.calendar.feapi.v1.UserStatus;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            TimeRange, AutoValue_TimeRangeEntry, Calendar, AutoValue_Calendar, 
//            TimeRangeEntry

final class V2AToEntryAdapter
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/V2AEventAdapter);

    static final TimeRangeEntry lambda$toEntries$0$V2AToEntryAdapter(TimeZone timezone, EventBundle eventbundle, Calendar calendar, CalendarListEntry calendarlistentry, EventInstance eventinstance)
    {
        Object obj;
        Object obj3;
        V2AEventDescriptor v2aeventdescriptor;
        boolean flag;
        boolean flag3;
        flag3 = true;
        v2aeventdescriptor = V2AEventDescriptor.newInstance(eventbundle, eventinstance);
        if (eventbundle.baseEvent_ == null)
        {
            obj = Event.DEFAULT_INSTANCE;
        } else
        {
            obj = eventbundle.baseEvent_;
        }
        if ((((Event) (obj)).bitField1_ & 0x20000) == 0x20000)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        obj = null;
_L5:
        Object obj1;
        GoalItem.Goal.Builder builder;
        boolean flag1;
        if (eventbundle.baseEvent_ == null)
        {
            eventbundle = Event.DEFAULT_INSTANCE;
        } else
        {
            eventbundle = eventbundle.baseEvent_;
        }
        obj3 = (new AutoValue_EventItem_Event.Builder()).setTitle(((Event) (eventbundle)).summary_);
        if ((((Event) (eventbundle)).bitField0_ & 0x2000) != 8192) goto _L4; else goto _L3
_L2:
        if (((Event) (obj)).habitInstance_ == null)
        {
            obj = com.google.protos.calendar.feapi.v1.Event.HabitInstance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Event) (obj)).habitInstance_;
        }
        builder = (new AutoValue_GoalItem_Goal.Builder()).setHabitId(((com.google.protos.calendar.feapi.v1.Event.HabitInstance) (obj)).habitId_);
        if (((com.google.protos.calendar.feapi.v1.Event.HabitInstance) (obj)).habitInstanceData_ == null)
        {
            obj1 = HabitInstanceData.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((com.google.protos.calendar.feapi.v1.Event.HabitInstance) (obj)).habitInstanceData_;
        }
        obj3 = com.google.protos.calendar.feapi.v1.HabitInstanceData.Status.forNumber(((HabitInstanceData) (obj1)).status_);
        obj1 = obj3;
        if (obj3 == null)
        {
            obj1 = com.google.protos.calendar.feapi.v1.HabitInstanceData.Status.UNKNOWN;
        }
        switch (((com.google.protos.calendar.feapi.v1.HabitInstanceData.Status) (obj1)).ordinal())
        {
        case 3: // '\003'
        default:
            flag1 = true;
            break;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
            break MISSING_BLOCK_LABEL_299;
        }
_L6:
        obj1 = builder.setDone(flag1);
        if (((com.google.protos.calendar.feapi.v1.Event.HabitInstance) (obj)).habitInstanceData_ == null)
        {
            obj = HabitInstanceData.DEFAULT_INSTANCE;
        } else
        {
            obj = ((com.google.protos.calendar.feapi.v1.Event.HabitInstance) (obj)).habitInstanceData_;
        }
        obj = ((GoalItem.Goal.Builder) (obj1)).setType(toGoalType(((HabitInstanceData) (obj)))).build();
          goto _L5
        flag1 = false;
          goto _L6
_L3:
        Object obj2;
        if (CalendarApi.colorFactory != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Must initialize API first."));
        }
        obj2 = CalendarApi.colorFactory.createGoogleEventColor(((Event) (eventbundle)).color_);
        if (obj2 == null) goto _L4; else goto _L7
_L7:
        obj2 = Integer.valueOf(((EventColor) (obj2)).getValue());
_L15:
        int i;
        boolean flag2;
        obj3 = ((EventItem.Event.Builder) (obj3)).setColor(((Integer) (obj2))).setCalendar(calendar).setLocation(((Event) (eventbundle)).location_);
        EventItem.Event.Builder builder1;
        if (((Event) (eventbundle)).organizer_ == null)
        {
            obj2 = com.google.protos.calendar.feapi.v1.Event.Principal.DEFAULT_INSTANCE;
        } else
        {
            obj2 = ((Event) (eventbundle)).organizer_;
        }
        builder1 = ((EventItem.Event.Builder) (obj3)).setOrganizer(((com.google.protos.calendar.feapi.v1.Event.Principal) (obj2)).email_);
        obj3 = com.google.protos.calendar.feapi.v1.Event.Visibility.forNumber(((Event) (eventbundle)).visibility_);
        obj2 = obj3;
        if (obj3 == null)
        {
            obj2 = com.google.protos.calendar.feapi.v1.Event.Visibility.DEFAULT;
        }
        obj3 = builder1.setAccessLevel(V2AEventAdapter.toVisibility(((com.google.protos.calendar.feapi.v1.Event.Visibility) (obj2)))).setInstanceModifiable(true).setSelfAttendeeStatus(toSelfAttendeeStatus(eventbundle));
        if (((Event) (eventbundle)).privateEventData_ == null)
        {
            obj2 = PrivateEventData.DEFAULT_INSTANCE;
        } else
        {
            obj2 = ((Event) (eventbundle)).privateEventData_;
        }
        if ((((PrivateEventData) (obj2)).bitField0_ & 1) == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        obj3 = ((EventItem.Event.Builder) (obj3)).setHasSmartMail(flag2);
        if (!Platform.stringIsNullOrEmpty(((Event) (eventbundle)).backgroundImageUrl_)) goto _L9; else goto _L8
_L8:
        if (((Event) (eventbundle)).privateEventData_ == null)
        {
            obj2 = PrivateEventData.DEFAULT_INSTANCE;
        } else
        {
            obj2 = ((Event) (eventbundle)).privateEventData_;
        }
        if ((((PrivateEventData) (obj2)).bitField0_ & 1) == 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L9; else goto _L10
_L10:
        if (((Event) (eventbundle)).structuredLocation_ == null)
        {
            obj2 = StructuredLocation.DEFAULT_INSTANCE;
        } else
        {
            obj2 = ((Event) (eventbundle)).structuredLocation_;
        }
        if (((StructuredLocation) (obj2)).location_.size() == 0) goto _L12; else goto _L11
_L11:
        if (((Event) (eventbundle)).structuredLocation_ == null)
        {
            obj2 = StructuredLocation.DEFAULT_INSTANCE;
        } else
        {
            obj2 = ((Event) (eventbundle)).structuredLocation_;
        }
        if ((((EventLocation)((StructuredLocation) (obj2)).location_.get(0)).bitField0_ & 0x10) != 16) goto _L12; else goto _L13
_L13:
        i = 1;
_L16:
        if (i == 0) goto _L14; else goto _L9
_L9:
        flag2 = true;
_L17:
        obj2 = ((EventItem.Event.Builder) (obj3)).setHasImageData(flag2).setEndTimeUnspecified(((Event) (eventbundle)).unbounded_);
        if (V2AEventAdapter.hasEveryoneDeclined(eventbundle) && !V2AEventAdapter.everyoneDeclinedDismissed(eventbundle) && calendarlistentry.isPrimary())
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        obj2 = ((EventItem.Event.Builder) (obj2)).setEveryoneDeclined(flag2);
        if ((((Event) (eventbundle)).bitField1_ & 0x40000) != 0x40000)
        {
            break MISSING_BLOCK_LABEL_925;
        }
        if (((Event) (eventbundle)).participantStatus_ == null)
        {
            calendarlistentry = UserStatus.DEFAULT_INSTANCE;
        } else
        {
            calendarlistentry = ((Event) (eventbundle)).participantStatus_;
        }
        if (((UserStatus) (calendarlistentry)).statusCase_ != 1)
        {
            break MISSING_BLOCK_LABEL_925;
        }
        flag2 = flag3;
_L18:
        eventbundle = ((EventItem.Event.Builder) (obj2)).setOutOfOffice(flag2).setHasTimeProposals(V2AEventAdapter.hasTimeProposals(eventbundle)).build();
        if (obj != null)
        {
            eventbundle = (new .AutoValue_GoalImpl.Builder()).setEventDescriptor(v2aeventdescriptor).setEvent(eventbundle).setGoal(((GoalItem.Goal) (obj))).build();
        } else
        {
            calendarlistentry = (new AutoValue_EventImpl.Builder()).setEventDescriptor(v2aeventdescriptor).setEvent(eventbundle);
            if (CalendarType.isHolidayCalendar(calendar.getOwnerAccount()))
            {
                eventbundle = Item.SortType.HOLIDAY;
            } else
            {
                eventbundle = Item.SortType.EVENT;
            }
            eventbundle = calendarlistentry.setSortType(eventbundle).build();
        }
        obj = (V2AEventKey)v2aeventdescriptor.getKey();
        if (eventinstance.localTimes_ == null)
        {
            calendar = InstanceTimes.DEFAULT_INSTANCE;
        } else
        {
            calendar = eventinstance.localTimes_;
        }
        if (((InstanceTimes) (calendar)).isAllDay_)
        {
            if (((InstanceTimes) (calendar)).displayStart_ == null)
            {
                calendarlistentry = DisplayTime.DEFAULT_INSTANCE;
            } else
            {
                calendarlistentry = ((InstanceTimes) (calendar)).displayStart_;
            }
            i = ((DisplayTime) (calendarlistentry)).day_;
            if (((InstanceTimes) (calendar)).displayEnd_ == null)
            {
                calendar = DisplayTime.DEFAULT_INSTANCE;
            } else
            {
                calendar = ((InstanceTimes) (calendar)).displayEnd_;
            }
            timezone = TimeRange.newAllDayFromJulianDay(timezone, i + 0x253d8c, (((DisplayTime) (calendar)).day_ + 0x253d8c) - 1);
        } else
        {
            timezone = TimeRange.newNonAllDay(timezone, ((InstanceTimes) (calendar)).startInstant_, ((InstanceTimes) (calendar)).endInstant_);
        }
        return new AutoValue_TimeRangeEntry(obj, eventbundle, timezone);
_L4:
        obj2 = Integer.valueOf(calendarlistentry.getColor().getValue());
          goto _L15
_L12:
        i = 0;
          goto _L16
_L14:
        flag2 = false;
          goto _L17
        flag2 = false;
          goto _L18
    }

    public static List toEntries(TimeZone timezone, EventBundle eventbundle, CalendarListEntry calendarlistentry)
    {
        Object obj = calendarlistentry.getDescriptor();
        AutoValue_Calendar autovalue_calendar = new AutoValue_Calendar(((CalendarDescriptor) (obj)).calendarKey, calendarlistentry.getAccessLevel(), ((CalendarDescriptor) (obj)).calendarId, "com.google", ((CalendarDescriptor) (obj)).account.name);
        obj = eventbundle.eventInstances_;
        class .Lambda._cls0
            implements Function
        {

            private final TimeZone arg$1;
            private final EventBundle arg$2;
            private final Calendar arg$3;
            private final CalendarListEntry arg$4;

            public final Object apply(Object obj1)
            {
                return V2AToEntryAdapter.lambda$toEntries$0$V2AToEntryAdapter(arg$1, arg$2, arg$3, arg$4, (EventInstance)obj1);
            }

            .Lambda._cls0(TimeZone timezone, EventBundle eventbundle, Calendar calendar, CalendarListEntry calendarlistentry)
            {
                arg$1 = timezone;
                arg$2 = eventbundle;
                arg$3 = calendar;
                arg$4 = calendarlistentry;
            }
        }

        timezone = new .Lambda._cls0(timezone, eventbundle, autovalue_calendar, calendarlistentry);
        if (obj instanceof RandomAccess)
        {
            return new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), timezone);
        } else
        {
            return new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), timezone);
        }
    }

    private static Integer toGoalType(HabitInstanceData habitinstancedata)
    {
        com.google.protos.calendar.feapi.v1.HabitData.Type type;
        com.google.protos.calendar.feapi.v1.HabitData.Type type2;
        int i;
        try
        {
            type2 = com.google.protos.calendar.feapi.v1.HabitData.Type.forNumber(habitinstancedata.type_);
        }
        catch (Exception exception)
        {
            String s = TAG;
            com.google.protos.calendar.feapi.v1.HabitData.Type type1 = com.google.protos.calendar.feapi.v1.HabitData.Type.forNumber(habitinstancedata.type_);
            habitinstancedata = type1;
            if (type1 == null)
            {
                habitinstancedata = com.google.protos.calendar.feapi.v1.HabitData.Type.UNKNOWN;
            }
            LogUtils.wtf(s, "Unknown habit type number %s", new Object[] {
                Integer.valueOf(((com.google.protos.calendar.feapi.v1.HabitData.Type) (habitinstancedata)).value)
            });
            return Integer.valueOf(0);
        }
        type = type2;
        if (type2 != null)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        type = com.google.protos.calendar.feapi.v1.HabitData.Type.UNKNOWN;
        i = GoalStoreUtils.protoTypeToApiType(type.value);
        return Integer.valueOf(i);
    }

    static com.google.android.calendar.api.event.attendee.Response.ResponseStatus toSelfAttendeeStatus(Event event)
    {
        for (event = event.attendee_.iterator(); event.hasNext();)
        {
            com.google.protos.calendar.feapi.v1.Event.Attendee attendee = (com.google.protos.calendar.feapi.v1.Event.Attendee)event.next();
            if (attendee.self_)
            {
                return V2AEventAdapter.toResponseStatus(attendee);
            }
        }

        return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;
    }

}
