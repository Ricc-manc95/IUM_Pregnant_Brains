// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.groovesync;

import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitApiStoreImpl;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.syncadapters.calendar.SyncLog;
import java.io.IOException;

final class SyncLoggingHabitApi extends HabitApiStoreImpl
{

    SyncLoggingHabitApi()
    {
    }

    public final int count(HabitFilterOptions habitfilteroptions)
        throws IOException
    {
        SyncLog.start("DB: habits.count");
        int i = super.count(habitfilteroptions);
        SyncLog.stop("DB: habits.count");
        return i;
        habitfilteroptions;
        SyncLog.stop("DB: habits.count");
        throw habitfilteroptions;
    }

    public final Habit create(HabitModifications habitmodifications)
        throws IOException
    {
        SyncLog.start("DB: habits.insert");
        habitmodifications = super.create(habitmodifications);
        SyncLog.stop("DB: habits.insert");
        return habitmodifications;
        habitmodifications;
        SyncLog.stop("DB: habits.insert");
        throw habitmodifications;
    }

    public final Habit create(HabitModifications habitmodifications, boolean flag)
        throws IOException
    {
        SyncLog.start("DB: habits.insert");
        habitmodifications = super.create(habitmodifications, flag);
        SyncLog.stop("DB: habits.insert");
        return habitmodifications;
        habitmodifications;
        SyncLog.stop("DB: habits.insert");
        throw habitmodifications;
    }

    public final Habit[] list(HabitFilterOptions habitfilteroptions)
        throws IOException
    {
        SyncLog.start("DB: habits.list");
        habitfilteroptions = super.list(habitfilteroptions);
        SyncLog.stop("DB: habits.list");
        return habitfilteroptions;
        habitfilteroptions;
        SyncLog.stop("DB: habits.list");
        throw habitfilteroptions;
    }

    public final Habit read(HabitDescriptor habitdescriptor)
        throws IOException
    {
        SyncLog.start("DB: habits.get");
        habitdescriptor = super.read(habitdescriptor);
        SyncLog.stop("DB: habits.get");
        return habitdescriptor;
        habitdescriptor;
        SyncLog.stop("DB: habits.get");
        throw habitdescriptor;
    }

    public final boolean removeDeleted(HabitDescriptor habitdescriptor)
    {
        SyncLog.start("DB: habits.delete");
        boolean flag = super.removeDeleted(habitdescriptor);
        SyncLog.stop("DB: habits.delete");
        return flag;
        habitdescriptor;
        SyncLog.stop("DB: habits.delete");
        throw habitdescriptor;
    }

    public final Habit update(HabitModifications habitmodifications, Void void1)
        throws IOException
    {
        SyncLog.start("DB: habits.update");
        habitmodifications = super.update(habitmodifications, void1);
        SyncLog.stop("DB: habits.update");
        return habitmodifications;
        habitmodifications;
        SyncLog.stop("DB: habits.update");
        throw habitmodifications;
    }

    public final Habit update(HabitModifications habitmodifications, Void void1, boolean flag)
        throws IOException
    {
        SyncLog.start("DB: habits.update");
        habitmodifications = super.update(habitmodifications, void1, flag);
        SyncLog.stop("DB: habits.update");
        return habitmodifications;
        habitmodifications;
        SyncLog.stop("DB: habits.update");
        throw habitmodifications;
    }
}
