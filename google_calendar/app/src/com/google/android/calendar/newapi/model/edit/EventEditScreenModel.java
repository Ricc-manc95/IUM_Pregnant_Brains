// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model.edit;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.newapi.logging.AnalyticsViewType;
import com.google.android.calendar.newapi.logging.EventEditLogMetrics;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.ColorHolder;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.RecurrenceHolder;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.android.calendar.newapi.model.TimeHolder;
import com.google.android.calendar.newapi.segment.time.TimeUtils;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.common.io.BaseEncoding;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Referenced classes of package com.google.android.calendar.newapi.model.edit:
//            BasicEditScreenModel, EventEditLogMetricsHolder, EventReferenceIdHolder, MoreTimeOptionsStatusHolder, 
//            RecurrenceEditHolder, SettingsHolder, EditScreenModel

public class EventEditScreenModel extends BasicEditScreenModel
    implements ColorHolder, PermissionHolder, RecurrenceHolder, TimeHolder, EventEditLogMetricsHolder, EventReferenceIdHolder, MoreTimeOptionsStatusHolder, RecurrenceEditHolder, SettingsHolder
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public String eventReferenceId;
    public Bundle extras;
    public boolean isFindTimeButtonClicked;
    private boolean isMoreTimeOptionsButtonClicked;
    public EventEditLogMetrics logMetrics;
    public EventPermissions permissions;
    public SettingsMap settingsMap;

    public EventEditScreenModel()
    {
        logMetrics = new EventEditLogMetrics();
        isFindTimeButtonClicked = false;
        isMoreTimeOptionsButtonClicked = false;
        BaseEncoding baseencoding = BaseEncoding.BASE32_HEX.lowerCase().omitPadding();
        byte abyte0[] = UUID.randomUUID().toString().getBytes();
        eventReferenceId = baseencoding.encode(abyte0, 0, abyte0.length);
    }

    protected EventEditScreenModel(Parcel parcel)
    {
        super(parcel);
        logMetrics = new EventEditLogMetrics();
        isFindTimeButtonClicked = false;
        isMoreTimeOptionsButtonClicked = false;
        eventReferenceId = parcel.readString();
        extras = parcel.readBundle();
        settingsMap = (SettingsMap)parcel.readParcelable(com/google/android/calendar/newapi/model/SettingsMap.getClassLoader());
        logMetrics = (EventEditLogMetrics)parcel.readParcelable(com/google/android/calendar/newapi/logging/EventEditLogMetrics.getClassLoader());
        boolean flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isFindTimeButtonClicked = flag;
    }

    public EventEditScreenModel(EventModifications eventmodifications, CalendarList calendarlist, SettingsMap settingsmap)
    {
        super(eventmodifications, calendarlist);
        logMetrics = new EventEditLogMetrics();
        isFindTimeButtonClicked = false;
        isMoreTimeOptionsButtonClicked = false;
        settingsMap = settingsmap;
    }

    public final boolean canChangeOrganizer()
    {
        return permissions.canChangeOrganizer();
    }

    public final boolean canModifyColorOverride()
    {
        return permissions.canModifyColorOverride();
    }

    public final boolean canModifyRecurrence(Context context)
    {
        return permissions.canModifyRecurrence() && TimeUtils.shouldExpandMoreTimeOptions(context, this);
    }

    public final boolean canModifyTitle()
    {
        return permissions.canModifySummary();
    }

    public final List getAllowedVisibilityValues()
    {
label0:
        {
            if (getCalendarListEntry().isPotentiallyShared())
            {
                break label0;
            }
            if (!super.eventModifications.isNewEvent())
            {
                EventModifications eventmodifications = super.eventModifications;
                byte byte0;
                if (SmartMailUtils.isSupportedSmartMailType(eventmodifications.getSmartMailInfo()))
                {
                    byte0 = 3;
                } else
                {
                    byte0 = 0;
                }
                if (eventmodifications.getOriginal().getVisibility() != byte0)
                {
                    break label0;
                }
            }
            return Collections.emptyList();
        }
        return permissions.getAllowedVisibilityValues();
    }

    public String getAnalyticsAction()
    {
        if (isNew())
        {
            return "create";
        } else
        {
            return "update";
        }
    }

    public final int getColor(Context context)
    {
        if (super.eventModifications == null)
        {
            return -1;
        } else
        {
            return super.eventModifications.getColor().getValue();
        }
    }

    public final long getEnd(Context context)
    {
        return super.eventModifications.getEndMillis();
    }

    public final String getEventReferenceId()
    {
        return eventReferenceId;
    }

    public final EventEditLogMetrics getLogMetrics()
    {
        return logMetrics;
    }

    public final EventPermissions getPermissions()
    {
        return permissions;
    }

    public final Recurrence getRecurrence()
    {
        EventModifications eventmodifications = super.eventModifications;
        boolean flag;
        if (eventmodifications.isRecurring() && !eventmodifications.getRecurrence().rrules.isEmpty())
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
            return super.eventModifications.getRecurrence();
        }
    }

    public final Settings getSettings()
    {
        SettingsMap settingsmap = settingsMap;
        android.accounts.Account account = getAccount();
        return (Settings)settingsmap.settingsMap.get(account);
    }

    public final long getStart(Context context)
    {
        return super.eventModifications.getStartMillis();
    }

    public final String getViewType()
    {
        return AnalyticsViewType.fromEvent(super.eventModifications);
    }

    public final boolean isAllDay()
    {
        return super.eventModifications.isAllDayEvent();
    }

    public final boolean isMoreTimeOptionsButtonClicked()
    {
        return isMoreTimeOptionsButtonClicked;
    }

    public final volatile void mergeModel(BasicViewScreenModel basicviewscreenmodel)
    {
        mergeModel((EventViewScreenModel)basicviewscreenmodel);
    }

    public final void mergeModel(EventViewScreenModel eventviewscreenmodel)
    {
        super.mergeModel(eventviewscreenmodel);
        eventviewscreenmodel = CalendarApi.EventFactory.modifyEvent(((BasicViewScreenModel) (eventviewscreenmodel)).event);
        Bundle bundle = extras;
        if (bundle != null)
        {
            long l = bundle.getLong("beginTime");
            if (l != 0L)
            {
                eventviewscreenmodel.setStartMillis(l);
            }
            l = bundle.getLong("endTime");
            if (l != 0L)
            {
                eventviewscreenmodel.setEndMillis(l);
            }
        }
        setEventModifications(eventviewscreenmodel);
    }

    public final void mergeModel(EditScreenModel editscreenmodel)
    {
        super.mergeModel(editscreenmodel);
        settingsMap = ((EventEditScreenModel)editscreenmodel).settingsMap;
    }

    public final volatile void mergeModel(Object obj)
    {
        mergeModel((EventViewScreenModel)obj);
    }

    public final void setEventModifications(EventModifications eventmodifications)
    {
        super.setEventModifications(eventmodifications);
        permissions = CalendarApi.EventPermissionsFactory.create(eventmodifications);
    }

    public final void setMoreTimeOptionsButtonClicked(boolean flag)
    {
        isMoreTimeOptionsButtonClicked = true;
    }

    public final void setRecurrence(Recurrence recurrence)
    {
        super.eventModifications.setRecurrence(recurrence);
    }

    public final void switchCalendar(CalendarListEntry calendarlistentry)
    {
        super.switchCalendar(calendarlistentry);
        permissions = CalendarApi.EventPermissionsFactory.create(super.eventModifications);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeString(eventReferenceId);
        parcel.writeBundle(extras);
        parcel.writeParcelable(settingsMap, i);
        parcel.writeParcelable(logMetrics, i);
        if (isFindTimeButtonClicked)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventEditScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventEditScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
