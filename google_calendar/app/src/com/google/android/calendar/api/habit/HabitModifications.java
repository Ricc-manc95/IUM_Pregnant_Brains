// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcelable;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.EventColor;

// Referenced classes of package com.google.android.calendar.api.habit:
//            Habit, HabitContractModifications, HabitReminders, HabitDescriptor

public interface HabitModifications
    extends Parcelable, Habit
{

    public abstract void applyModifications(HabitModifications habitmodifications);

    public abstract HabitContractModifications getContractModifications();

    public abstract Habit getOriginal();

    public abstract boolean isColorOverrideModified();

    public abstract boolean isFingerprintModified();

    public abstract boolean isFitIntegrationStatusModified();

    public abstract boolean isModified();

    public abstract boolean isNewHabit();

    public abstract boolean isRemindersModified();

    public abstract boolean isSummaryModified();

    public abstract boolean isTypeModified();

    public abstract boolean isVisibilityModified();

    public abstract HabitModifications setColorOverride(EventColor eventcolor);

    public abstract HabitModifications setFingerprint(String s);

    public abstract HabitModifications setFitIntegrationStatus(int i);

    public abstract HabitModifications setReminders(HabitReminders habitreminders);

    public abstract HabitModifications setSummary(String s);

    public abstract HabitModifications setType(int i);

    public abstract HabitModifications setVisibility(int i);

    public abstract HabitDescriptor switchCalendar(CalendarDescriptor calendardescriptor);
}
