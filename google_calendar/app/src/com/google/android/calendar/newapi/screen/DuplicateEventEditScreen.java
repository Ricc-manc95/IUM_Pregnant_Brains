// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventImpl;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventModificationsImpl;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.api.event.conference.ConferenceDataModifications;
import com.google.android.calendar.api.event.conference.ConferenceDataUtils;
import com.google.android.calendar.api.event.conference.CreatedConference;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.DuplicateEventEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.android.calendar.newapi.screen.event.EventEditSegmentProvider;
import com.google.android.calendar.newapi.segment.attachment.AttachmentEditSegmentController;
import com.google.android.calendar.newapi.segment.attendee.AttendeeEditSegmentController;
import com.google.android.calendar.newapi.segment.availability.AvailabilityEditSegmentController;
import com.google.android.calendar.newapi.segment.calendar.EventCalendarEditSegmentController;
import com.google.android.calendar.newapi.segment.color.ColorEditSegmentController;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.conference.ConferenceEditSegmentController;
import com.google.android.calendar.newapi.segment.location.LocationEditSegmentController;
import com.google.android.calendar.newapi.segment.note.NoteEditSegmentController;
import com.google.android.calendar.newapi.segment.notification.EventNotificationEditSegmentController;
import com.google.android.calendar.newapi.segment.ooo.OooEditSegmentController;
import com.google.android.calendar.newapi.segment.recurrence.RecurrenceEditSegmentController;
import com.google.android.calendar.newapi.segment.room.RoomEditSegmentController;
import com.google.android.calendar.newapi.segment.room.RoomUtils;
import com.google.android.calendar.newapi.segment.time.EventTimeEditSegmentController;
import com.google.android.calendar.newapi.segment.timezone.TimeZoneEditSegmentController;
import com.google.android.calendar.newapi.segment.title.EventTitleEditSegmentController;
import com.google.android.calendar.newapi.segment.visibility.VisibilityEditSegmentController;
import com.google.android.calendar.newapi.utils.LegacyUtils;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EventEditScreenController, EditScreenController, SegmentMap, EventViewScreenController, 
//            ViewScreenController, ViewScreenOpenCloseHelper, SegmentViews

public final class DuplicateEventEditScreen extends EventEditScreenController
{

    private final EventEditSegmentProvider segmentProvider = new EventEditSegmentProvider();

    public DuplicateEventEditScreen()
    {
    }

    protected final volatile EditScreenModel createModel()
    {
        return createModel();
    }

    protected final EventEditScreenModel createModel()
    {
        Event event;
        DuplicateEventEditScreenModel duplicateeventeditscreenmodel;
        EventModifications eventmodifications;
        int i1;
        Object obj1 = (Event)getArguments().getParcelable("ARG_ORIGINAL_EVENT");
        i1 = getArguments().getInt("ARG_VIEW_MODE");
        android.content.Context context = getContext();
        duplicateeventeditscreenmodel = new DuplicateEventEditScreenModel(i1);
        Object obj3 = Parcel.obtain();
        ((Event) (obj1)).writeToParcel(((Parcel) (obj3)), 0);
        ((Parcel) (obj3)).setDataPosition(0);
        int i;
        int k;
        long l1;
        long l2;
        if (obj1 instanceof EventImpl)
        {
            event = (Event)EventImpl.CREATOR.createFromParcel(((Parcel) (obj3)));
        } else
        {
            event = (Event)EventModificationsImpl.CREATOR.createFromParcel(((Parcel) (obj3)));
        }
        ((Parcel) (obj3)).recycle();
        eventmodifications = CalendarApi.EventFactory.newEvent(((Event) (obj1)).getCalendarListEntry());
        if (event.getDescriptor().isRecurringPhantom())
        {
            eventmodifications.setRecurrence(event.getRecurrence());
        }
        eventmodifications.setVisibility(event.getVisibility());
        eventmodifications.setColorOverride(event.getColorOverride());
        eventmodifications.setDescription(event.getDescription());
        eventmodifications.setSummary(event.getSummary());
        eventmodifications.setAvailability(event.getAvailability());
        if (event.getDescriptor().isRecurringPhantom())
        {
            l1 = event.getRecurringFirstStartMillis();
        } else
        {
            l1 = event.getStartMillis();
        }
        if (event.getDescriptor().isRecurringPhantom())
        {
            l2 = (event.getEndMillis() - event.getStartMillis()) + l1;
        } else
        {
            l2 = event.getEndMillis();
        }
        if (event.isAllDayEvent())
        {
            eventmodifications.setToAllDayWithDates(l1, l2);
            break MISSING_BLOCK_LABEL_271;
        } else
        {
            eventmodifications.setToTimedWithTimes(l1, l2);
            String s1 = event.getTimeZoneId();
            String s = s1;
            if (s1 == null)
            {
                s = s1;
                if (event.getDescriptor().isRecurringPhantom())
                {
                    s = Utils.getTimeZone(context).getID();
                }
            }
            if (s != null)
            {
                eventmodifications.setTimeZoneId(s);
            }
            if (event.getEndTimeZoneId() != null)
            {
                eventmodifications.setEndTimeZoneId(event.getEndTimeZoneId());
            }
            continue;
        }
        do
        {
            if (i1 == 2)
            {
                eventmodifications.setEndTimeUnspecified(event.isEndTimeUnspecified());
            }
            if (!ConferenceDataUtils.isEmptyConference(event.getConferenceData()) && !ConferenceDataUtils.isAddOn(event.getConferenceData()) && (event.getConferenceData().getCreateConferenceRequest() == null || ConferenceDataUtils.isCreationSuccessful(event.getConferenceData())))
            {
                eventmodifications.getConferenceDataModifications().cloneFrom(CreatedConference.createdConference(event.getConferenceData()));
            }
            if (event.getNotifications() == null)
            {
                eventmodifications.getNotificationModifications().useCalendarDefaults();
            } else
            {
                Iterator iterator = event.getNotifications().iterator();
                while (iterator.hasNext()) 
                {
                    Notification notification = (Notification)iterator.next();
                    eventmodifications.getNotificationModifications().addNotification(notification);
                }
            }
            obj1 = (ImmutableList)event.getAttachments();
            k = ((ImmutableList) (obj1)).size();
            obj3 = (UnmodifiableIterator)null;
            for (i = 0; i < k;)
            {
                obj3 = ((ImmutableList) (obj1)).get(i);
                i++;
                obj3 = (Attachment)obj3;
                eventmodifications.getAttachmentModifications().addAttachment(((Attachment) (obj3)));
            }

            if (i1 != 1)
            {
                Response response = new Response(new com.google.android.calendar.api.event.attendee.Response.Builder());
                Attendee attendee = AttendeeUtils.getCurrentAttendee(event);
                ImmutableList immutablelist = (ImmutableList)event.getAttendees();
                int j1 = immutablelist.size();
                UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
                int j = 0;
                do
                {
                    if (j >= j1)
                    {
                        break;
                    }
                    Object obj4 = immutablelist.get(j);
                    int l = j + 1;
                    obj4 = (Attendee)obj4;
                    boolean flag1;
                    if (obj4 == attendee || obj4 != null && obj4.equals(attendee))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    j = l;
                    if (!flag1)
                    {
                        eventmodifications.getAttendeeModifications().addAttendee(((Attendee) (obj4)));
                        eventmodifications.getAttendeeModifications().setAttendeeResponse(((Attendee) (obj4)), response);
                        j = l;
                    }
                } while (true);
            }
            List list = RoomUtils.getRooms(event);
            Iterator iterator1 = event.getLocation().getEventLocations().iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                Object obj2 = (EventLocation)iterator1.next();
                Object obj = obj2;
                if (i1 == 2)
                {
                    obj = Platform.nullToEmpty(RoomUtils.removeRoomsFromLocation(((EventLocation) (obj2)).name, list));
                    obj2 = new com.google.android.calendar.api.event.location.EventLocation.Builder(((EventLocation) (obj2)));
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    obj2.name = (String)obj;
                    obj = new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj2)));
                }
                boolean flag;
                if (LegacyUtils.isLegacyLocationOrEmpty(((EventLocation) (obj))) && TextUtils.isEmpty(((EventLocation) (obj)).name))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    eventmodifications.getLocationModification().addEventLocation(((EventLocation) (obj)));
                }
            } while (true);
            duplicateeventeditscreenmodel.setEventModifications(eventmodifications);
            return duplicateeventeditscreenmodel;
        } while (true);
    }

    protected final SegmentMap createSegments()
    {
        Object obj = segmentProvider;
        obj = (DuplicateEventEditScreenModel)getModel();
        ArrayList arraylist = new ArrayList(Arrays.asList(new Class[] {
            com/google/android/calendar/newapi/segment/title/EventTitleEditSegmentController, com/google/android/calendar/newapi/segment/calendar/EventCalendarEditSegmentController, com/google/android/calendar/newapi/segment/time/EventTimeEditSegmentController, com/google/android/calendar/newapi/segment/timezone/TimeZoneEditSegmentController, com/google/android/calendar/newapi/segment/recurrence/RecurrenceEditSegmentController, com/google/android/calendar/newapi/segment/room/RoomEditSegmentController, com/google/android/calendar/newapi/segment/location/LocationEditSegmentController, com/google/android/calendar/newapi/segment/notification/EventNotificationEditSegmentController, com/google/android/calendar/newapi/segment/attendee/AttendeeEditSegmentController, com/google/android/calendar/newapi/segment/color/ColorEditSegmentController, 
            com/google/android/calendar/newapi/segment/note/NoteEditSegmentController, com/google/android/calendar/newapi/segment/attachment/AttachmentEditSegmentController, com/google/android/calendar/newapi/segment/visibility/VisibilityEditSegmentController, com/google/android/calendar/newapi/segment/availability/AvailabilityEditSegmentController
        }));
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).ooo())
        {
            arraylist.add(com/google/android/calendar/newapi/segment/ooo/OooEditSegmentController);
        }
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).third_party_conferences_editing();
            arraylist.add(com/google/android/calendar/newapi/segment/conference/ConferenceEditSegmentController);
            return SegmentMap.create(this, obj, arraylist);
        }
    }

    public final EditScreenModel getModel()
    {
        return (DuplicateEventEditScreenModel)Utils.uncheckedCast(super.getModel());
    }

    protected final SegmentViews getOrderedSegments(SegmentMap segmentmap)
    {
        return EventEditSegmentProvider.getOrderedSegments$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NMQRR4CLM2UPB4D5Q2UKR5EHQ6IRJ7ED46UR34CLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NMSPBNC5O6IBRJCDP6APBE5T9MAPRDCLN78JB1E0TIIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NMSPBNC5O6IBRJCDP6APBE5T9MAPRDCLN78LJ9CLRN6EO_0((DuplicateEventEditScreenModel)getModel(), segmentmap);
    }

    protected final void onLoadingCompleted(boolean flag)
    {
        CalendarListEntry calendarlistentry;
        super.onLoadingCompleted(flag);
        if (getArguments().getInt("ARG_VIEW_MODE") == 2)
        {
            Object obj = (DuplicateEventEditScreenModel)getModel();
            SettingsMap settingsmap = ((EventEditScreenModel) (obj)).settingsMap;
            obj = ((EditScreenModel) (obj)).getAccount();
            if (!AccountUtils.isDasher((Settings)settingsmap.settingsMap.get(obj)))
            {
                EventModifications eventmodifications = ((BasicEditScreenModel) ((DuplicateEventEditScreenModel)getModel())).eventModifications;
                Attendee attendee;
                for (Iterator iterator = RoomUtils.getRooms(eventmodifications).iterator(); iterator.hasNext(); eventmodifications.getAttendeeModifications().removeAttendee(attendee))
                {
                    attendee = (Attendee)iterator.next();
                }

            }
        }
        calendarlistentry = (CalendarListEntry)getArguments().getParcelable("ARG_TARGET_CALENDAR");
        if (calendarlistentry == null || ((DuplicateEventEditScreenModel)getModel()).getCalendarListEntry().equals(calendarlistentry)) goto _L2; else goto _L1
_L1:
        EditScreenController..Lambda._cls1 _lcls1;
        Iterator iterator1;
        ((DuplicateEventEditScreenModel)getModel()).switchCalendar(calendarlistentry);
        _lcls1 = new EditScreenController..Lambda._cls1(false, false);
        iterator1 = super.segments.segmentControllers.values().iterator();
_L12:
        if (!iterator1.hasNext()) goto _L4; else goto _L3
_L3:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag1;
        editsegmentcontroller = (EditSegmentController)iterator1.next();
        if (editsegmentcontroller == null || editsegmentcontroller != null && editsegmentcontroller.equals(null))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L8; else goto _L7
_L7:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L9; else goto _L8
_L8:
        flag1 = false;
_L10:
        if (flag1)
        {
            _lcls1.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag1 = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag1 = true;
            continue; /* Loop/switch isn't completed */
        }
_L6:
        flag1 = false;
        if (true) goto _L10; else goto _L4
_L4:
        onCalendarChanged();
_L2:
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final void onSaveCompleted(boolean flag, Event event, int i)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).mFragments.mHost.mFragmentManager.findFragmentByTag("ViewScreenController");
        if (obj instanceof EventViewScreenController)
        {
            ((ViewScreenController) ((EventViewScreenController)obj)).animationHelper.animationData = null;
        }
        super.onSaveCompleted(flag, event, i);
    }

    protected final boolean shouldCloseViewScreenAfterSave(Event event)
    {
        return true;
    }
}
