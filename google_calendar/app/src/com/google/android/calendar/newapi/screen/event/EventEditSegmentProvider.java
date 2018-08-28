// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.Optionals;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.newapi.model.edit.SettingsHolder;
import com.google.android.calendar.newapi.screen.SegmentMap;
import com.google.android.calendar.newapi.screen.SegmentViews;
import com.google.android.calendar.newapi.segment.attachment.AttachmentEditSegmentController;
import com.google.android.calendar.newapi.segment.attendee.AttendeeEditSegmentController;
import com.google.android.calendar.newapi.segment.availability.AvailabilityEditSegmentController;
import com.google.android.calendar.newapi.segment.calendar.EventCalendarEditSegmentController;
import com.google.android.calendar.newapi.segment.color.ColorEditSegmentController;
import com.google.android.calendar.newapi.segment.conference.ConferenceEditSegmentController;
import com.google.android.calendar.newapi.segment.location.LocationEditSegmentController;
import com.google.android.calendar.newapi.segment.note.NoteEditSegmentController;
import com.google.android.calendar.newapi.segment.notification.EventNotificationEditSegmentController;
import com.google.android.calendar.newapi.segment.ooo.OooEditSegmentController;
import com.google.android.calendar.newapi.segment.recurrence.RecurrenceEditSegmentController;
import com.google.android.calendar.newapi.segment.room.RoomEditSegmentController;
import com.google.android.calendar.newapi.segment.time.EventTimeEditSegmentController;
import com.google.android.calendar.newapi.segment.timezone.TimeZoneEditSegmentController;
import com.google.android.calendar.newapi.segment.title.EventTitleEditSegmentController;
import com.google.android.calendar.newapi.segment.visibility.VisibilityEditSegmentController;

public final class EventEditSegmentProvider
{

    public EventEditSegmentProvider()
    {
    }

    public static SegmentViews getOrderedSegments$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NMQRR4CLM2UPB4D5Q2UKR5EHQ6IRJ7ED46UR34CLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NMSPBNC5O6IBRJCDP6APBE5T9MAPRDCLN78JB1E0TIIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NMSPBNC5O6IBRJCDP6APBE5T9MAPRDCLN78LJ9CLRN6EO_0(SettingsHolder settingsholder, SegmentMap segmentmap)
    {
        boolean flag = AccountUtils.isDasher(settingsholder.getSettings());
        settingsholder = new SegmentViews();
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/title/EventTitleEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls0(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/calendar/EventCalendarEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        if (flag)
        {
            Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/attendee/AttendeeEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        }
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/time/EventTimeEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/timezone/TimeZoneEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/recurrence/RecurrenceEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/room/RoomEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls1(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/location/LocationEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/notification/EventNotificationEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        if (!flag)
        {
            Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/attendee/AttendeeEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        }
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).third_party_conferences_editing();
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/conference/ConferenceEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).ooo())
        {
            Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/ooo/OooEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        }
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/color/ColorEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/note/NoteEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/attachment/AttachmentEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/visibility/VisibilityEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/availability/AvailabilityEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(settingsholder));
        return settingsholder;
    }
}
