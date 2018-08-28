// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import android.os.Bundle;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.calendarlist.CalendarListFilterOptions;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.event.EditHelper;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.loader.CalendarListLoader;
import com.google.android.calendar.newapi.common.loader.SettingsMapLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.android.calendar.newapi.segment.ooo.OooUtils;
import com.google.android.calendar.time.clock.Clock;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            NewEventPopulator

public final class EventEditScreenLoader extends CompositeLoader
{

    private final CalendarList calendarList;
    private CalendarListLoader calendarListLoader;
    private final Context context;
    private final EventEditScreenModel model;
    private final SettingsMap settingsMap;
    private SettingsMapLoader settingsMapLoader;

    EventEditScreenLoader(Context context1, EventEditScreenModel eventeditscreenmodel)
    {
        model = eventeditscreenmodel;
        calendarList = ((BasicEditScreenModel) (eventeditscreenmodel)).calendarList;
        settingsMap = eventeditscreenmodel.settingsMap;
        context = context1;
        if (calendarList == null)
        {
            eventeditscreenmodel = new CalendarListFilterOptions();
            eventeditscreenmodel.writable = Boolean.valueOf(true);
            context1 = new CalendarListLoader(context1, eventeditscreenmodel, null, true);
            calendarListLoader = context1;
            super.loaders.add(context1);
        }
        if (settingsMap == null)
        {
            context1 = new SettingsMapLoader();
            settingsMapLoader = context1;
            super.loaders.add(context1);
        }
    }

    public final Object getResult()
    {
        Object obj;
        Object obj1;
        Object obj2;
        if (((BasicEditScreenModel) (model)).eventModifications != null)
        {
            obj = ((BasicEditScreenModel) (model)).eventModifications;
        } else
        {
            obj = model.extras;
            String s;
            boolean flag;
            long l;
            if (obj != null && ((Bundle) (obj)).getBoolean("out_of_office_event", false))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                obj = OooUtils.getDefaultCalendar();
            } else
            {
                if (calendarList != null)
                {
                    obj = calendarList;
                } else
                {
                    obj = (CalendarList)calendarListLoader.getResult();
                }
                obj = ((CalendarList) (obj)).getDefaultEntry();
            }
            obj2 = CalendarApi.EventFactory.newEvent(((CalendarListEntry) (obj)));
            if (settingsMap != null)
            {
                obj1 = settingsMap;
            } else
            {
                obj1 = (SettingsMap)settingsMapLoader.getResult();
            }
            obj = ((CalendarListEntry) (obj)).getDescriptor().account;
            obj = (Settings)((SettingsMap) (obj1)).settingsMap.get(obj);
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            l = EditHelper.constructDefaultStartTime(l);
            obj1 = Utils.getTimeZoneId(context);
            s = context.getString(0x7f13036a);
            if (flag)
            {
                NewEventPopulator.updateOooEventModifications(((com.google.android.calendar.api.event.EventModifications) (obj2)), model.extras, l, s, ((String) (obj1)));
            } else
            {
                NewEventPopulator.updateEventModifications(((com.google.android.calendar.api.event.EventModifications) (obj2)), model.extras, l, ((Settings) (obj)), ((String) (obj1)));
            }
            obj = CalendarApi.EventFactory.modifyEvent(((com.google.android.calendar.api.event.Event) (obj2)));
        }
        if (calendarList != null)
        {
            obj1 = calendarList;
        } else
        {
            obj1 = (CalendarList)calendarListLoader.getResult();
        }
        if (settingsMap != null)
        {
            obj2 = settingsMap;
        } else
        {
            obj2 = (SettingsMap)settingsMapLoader.getResult();
        }
        return new EventEditScreenModel(((com.google.android.calendar.api.event.EventModifications) (obj)), ((CalendarList) (obj1)), ((SettingsMap) (obj2)));
    }
}
