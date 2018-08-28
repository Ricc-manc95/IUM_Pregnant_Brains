// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.os.Parcel;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.newapi.logging.GrooveEditLogMetrics;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            HabitModificationsHolder, BasicViewScreenModel, GrooveViewScreenModel, CalendarList

public final class GrooveEditScreenModel extends BasicEditScreenModel
    implements HabitModificationsHolder
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public HabitModifications habitModifications;
    public GrooveEditLogMetrics logMetrics;

    public GrooveEditScreenModel()
    {
        logMetrics = new GrooveEditLogMetrics();
    }

    protected GrooveEditScreenModel(Parcel parcel)
    {
        super(parcel);
        logMetrics = new GrooveEditLogMetrics();
        habitModifications = (HabitModifications)parcel.readParcelable(com/google/android/calendar/api/habit/HabitModifications.getClassLoader());
        logMetrics = (GrooveEditLogMetrics)parcel.readParcelable(com/google/android/calendar/newapi/logging/GrooveEditLogMetrics.getClassLoader());
    }

    public GrooveEditScreenModel(EventModifications eventmodifications, HabitModifications habitmodifications, CalendarList calendarlist)
    {
        super(eventmodifications, calendarlist);
        logMetrics = new GrooveEditLogMetrics();
        habitModifications = habitmodifications;
    }

    private final void mergeModel(GrooveViewScreenModel grooveviewscreenmodel)
    {
        super.mergeModel(grooveviewscreenmodel);
        setEventModifications(CalendarApi.EventFactory.modifyEvent(((BasicViewScreenModel) (grooveviewscreenmodel)).event));
        if (grooveviewscreenmodel.habit != null)
        {
            habitModifications = CalendarApi.HabitFactory.modifyHabit(grooveviewscreenmodel.habit);
        }
    }

    public final boolean canChangeOrganizer()
    {
        return isNew();
    }

    public final boolean canModifyColorOverride()
    {
        return true;
    }

    public final boolean canModifyTitle()
    {
        return true;
    }

    public final List getAllowedVisibilityValues()
    {
        HabitModifications habitmodifications = habitModifications;
        EventModifications eventmodifications = super.eventModifications;
        boolean flag;
        if (habitmodifications == null || eventmodifications != null && !eventmodifications.getCalendarListEntry().isPrimary())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return Collections.emptyList();
        }
        if (!getCalendarListEntry().isPotentiallyShared() && (habitModifications.isNewHabit() || habitModifications.getOriginal().getVisibility() == 0))
        {
            return Collections.emptyList();
        } else
        {
            return Arrays.asList(new Integer[] {
                Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2)
            });
        }
    }

    public final CalendarListEntry getCalendarListEntry()
    {
        if (showSimplifiedScreen())
        {
            return super.getCalendarListEntry();
        } else
        {
            return super.calendarList.findEntry(habitModifications.getDescriptor().calendar.calendarId);
        }
    }

    public final EntityColor getColor()
    {
        if (showSimplifiedScreen())
        {
            return super.getColor();
        }
        if (habitModifications.getColorOverride() != null)
        {
            return habitModifications.getColorOverride();
        } else
        {
            return getCalendarListEntry().getColor();
        }
    }

    public final HabitModifications getHabitModifications()
    {
        return habitModifications;
    }

    public final String getTitle()
    {
        if (showSimplifiedScreen())
        {
            return super.getTitle();
        } else
        {
            return habitModifications.getSummary();
        }
    }

    public final String getViewType()
    {
        return "groove";
    }

    public final int getVisibility()
    {
        HabitModifications habitmodifications = habitModifications;
        EventModifications eventmodifications = super.eventModifications;
        boolean flag;
        if (habitmodifications == null || eventmodifications != null && !eventmodifications.getCalendarListEntry().isPrimary())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return super.getVisibility();
        } else
        {
            return habitModifications.getVisibility();
        }
    }

    public final boolean hasStartTimeChanges()
    {
        while (super.eventModifications == null || super.eventModifications.getOriginal() == null || super.eventModifications.getStartMillis() == super.eventModifications.getOriginal().getStartMillis()) 
        {
            return false;
        }
        return true;
    }

    public final boolean isModified()
    {
        if (habitModifications != null && habitModifications.isModified())
        {
            return true;
        } else
        {
            return super.isModified();
        }
    }

    public final boolean isNew()
    {
        return habitModifications != null && habitModifications.isNewHabit();
    }

    public final volatile void mergeModel(BasicViewScreenModel basicviewscreenmodel)
    {
        mergeModel((GrooveViewScreenModel)basicviewscreenmodel);
    }

    public final void mergeModel(EditScreenModel editscreenmodel)
    {
        super.mergeModel(editscreenmodel);
        habitModifications = ((GrooveEditScreenModel)editscreenmodel).habitModifications;
    }

    public final volatile void mergeModel(Object obj)
    {
        mergeModel((GrooveViewScreenModel)obj);
    }

    public final boolean readOnly()
    {
        return showSimplifiedScreen();
    }

    public final void setColorOverride(EventColor eventcolor)
    {
        if (habitModifications != null)
        {
            habitModifications.setColorOverride(eventcolor);
            return;
        } else
        {
            super.setColorOverride(eventcolor);
            return;
        }
    }

    public final void setTitle(String s)
    {
        if (showSimplifiedScreen())
        {
            super.setTitle(s);
            return;
        } else
        {
            habitModifications.setSummary(s);
            return;
        }
    }

    public final void setVisibility(int i)
    {
        HabitModifications habitmodifications = habitModifications;
        EventModifications eventmodifications = super.eventModifications;
        boolean flag;
        if (habitmodifications == null || eventmodifications != null && !eventmodifications.getCalendarListEntry().isPrimary())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            super.setVisibility(i);
            return;
        } else
        {
            habitModifications.setVisibility(i);
            return;
        }
    }

    public final boolean showSimplifiedScreen()
    {
        HabitModifications habitmodifications = habitModifications;
        EventModifications eventmodifications = super.eventModifications;
        return habitmodifications == null || eventmodifications != null && !eventmodifications.getCalendarListEntry().isPrimary();
    }

    public final void switchCalendar(CalendarListEntry calendarlistentry)
    {
        habitModifications.switchCalendar(calendarlistentry.getDescriptor());
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(habitModifications, i);
        parcel.writeParcelable(logMetrics, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GrooveEditScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GrooveEditScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
