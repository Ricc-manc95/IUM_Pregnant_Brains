// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.CalendarListEntryHolder;
import com.google.android.calendar.newapi.model.CalendarListHolder;
import com.google.android.calendar.newapi.model.CalendarModification;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.SegmentController;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            CalendarEditSegmentController, AutoValue_UiCalendarUtils_UiCalendarListEntry, CalendarFormatter

public class EventCalendarEditSegmentController extends CalendarEditSegmentController
{

    private UiCalendarUtils.CalendarPickerFactory calendarPickerFactory;
    public CalendarFormatter formatter;

    public EventCalendarEditSegmentController()
    {
        calendarPickerFactory = new UiCalendarUtils.CalendarPickerFactory();
    }

    private final Iterable getCalendarListEntries()
    {
        java.util.List list = ((CalendarListHolder)(EventModificationsHolder)super.model).getCalendarList().calendars;
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).ooo())
        {
            com.google.android.calendar.api.event.EventModifications eventmodifications = ((EventModificationsHolder)super.model).getEventModifications();
            boolean flag;
            if (eventmodifications != null && eventmodifications.getParticipantStatus() != null && eventmodifications.getParticipantStatus().getOutOfOffice() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                class .Lambda._cls0
                    implements Predicate
                {

                    public static final Predicate $instance = new .Lambda._cls0();

                    public final boolean apply(Object obj)
                    {
                        return OooUtils.isOooSupportedCalendar((CalendarListEntry)obj);
                    }


            private .Lambda._cls0()
            {
            }
                }

                Predicate predicate = .Lambda._cls0..instance;
                if (list == null)
                {
                    throw new NullPointerException();
                }
                if (predicate == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new com.google.common.collect.Iterables._cls4(list, predicate);
                }
            }
        }
        return list;
    }

    protected final DialogFragment createDialog()
    {
        UiCalendarUtils.CalendarPickerFactory calendarpickerfactory = calendarPickerFactory;
        return UiCalendarUtils.CalendarPickerFactory.create(getContext(), getCalendarListEntries(), this, -1);
    }

    protected final UiCalendarUtils.UiCalendar getCurrentCalendar()
    {
        CalendarFormatter calendarformatter = formatter;
        CalendarListEntry calendarlistentry = ((CalendarListEntryHolder)(EventModificationsHolder)super.model).getCalendarListEntry();
        return new AutoValue_UiCalendarUtils_UiCalendarListEntry(Utils.getCalendarNameToDisplay(calendarlistentry.isPrimary(), calendarlistentry.getDisplayName(), calendarlistentry.getDescriptor().account.type, calendarformatter.defaultEventsTitle), calendarlistentry.getDescriptor().account.name, calendarlistentry.getColor().getValue(), calendarlistentry);
    }

    protected final Iterable getUiCalendars()
    {
        Iterable iterable = getCalendarListEntries();
        class .Lambda._cls1
            implements Function
        {

            private final EventCalendarEditSegmentController arg$1;

            public final Object apply(Object obj)
            {
                Object obj1 = arg$1;
                obj = (CalendarListEntry)obj;
                obj1 = ((EventCalendarEditSegmentController) (obj1)).formatter;
                return new AutoValue_UiCalendarUtils_UiCalendarListEntry(Utils.getCalendarNameToDisplay(((CalendarListEntry) (obj)).isPrimary(), ((CalendarListEntry) (obj)).getDisplayName(), ((CalendarListEntry) (obj)).getDescriptor().account.type, ((CalendarFormatter) (obj1)).defaultEventsTitle), ((CalendarListEntry) (obj)).getDescriptor().account.name, ((CalendarListEntry) (obj)).getColor().getValue(), ((CalendarListEntry) (obj)));
            }

            .Lambda._cls1()
            {
                arg$1 = EventCalendarEditSegmentController.this;
            }
        }

        .Lambda._cls1 _lcls1 = new .Lambda._cls1();
        if (iterable == null)
        {
            throw new NullPointerException();
        }
        if (_lcls1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.collect.Iterables._cls5(iterable, _lcls1);
        }
    }

    protected final void onCalendarChosen(UiCalendarUtils.UiCalendar uicalendar)
    {
        uicalendar = (UiCalendarUtils.UiCalendarListEntry)uicalendar;
        ((CalendarModification)(EventModificationsHolder)super.model).switchCalendar(uicalendar.value());
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        formatter = new CalendarFormatter(requireContext().getResources());
    }

    protected final boolean showSegment()
    {
        return super.showSegment() && ((CalendarModification)(EventModificationsHolder)super.model).canChangeOrganizer();
    }
}
