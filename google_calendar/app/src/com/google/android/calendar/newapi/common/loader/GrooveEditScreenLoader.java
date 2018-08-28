// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common.loader;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListFilterOptions;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.GrooveEditScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.common.loader:
//            CalendarListLoader

public final class GrooveEditScreenLoader extends CompositeLoader
{

    private final CalendarList calendarList;
    private CalendarListLoader calendarListLoader;
    private final GrooveEditScreenModel model;

    public GrooveEditScreenLoader(Context context, GrooveEditScreenModel grooveeditscreenmodel)
    {
        model = grooveeditscreenmodel;
        calendarList = ((BasicEditScreenModel) (grooveeditscreenmodel)).calendarList;
        if (calendarList == null)
        {
            grooveeditscreenmodel = new CalendarListFilterOptions();
            grooveeditscreenmodel.writable = Boolean.valueOf(true);
            grooveeditscreenmodel.googleCalendarsOnly = Boolean.valueOf(true);
            grooveeditscreenmodel.primary = Boolean.valueOf(true);
            context = new CalendarListLoader(context, grooveeditscreenmodel, null, false);
            calendarListLoader = context;
            super.loaders.add(context);
        }
    }

    public final Object getResult()
    {
        com.google.android.calendar.api.event.EventModifications eventmodifications = ((BasicEditScreenModel) (model)).eventModifications;
        com.google.android.calendar.api.habit.HabitModifications habitmodifications = model.habitModifications;
        CalendarList calendarlist;
        if (calendarList != null)
        {
            calendarlist = calendarList;
        } else
        {
            calendarlist = (CalendarList)calendarListLoader.getResult();
        }
        return new GrooveEditScreenModel(eventmodifications, habitmodifications, calendarlist);
    }
}
