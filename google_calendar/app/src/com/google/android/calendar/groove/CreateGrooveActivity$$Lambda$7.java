// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.os.Handler;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.utils.network.NetworkUtil;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveSyncTracker, CreateGrooveActivity

final class arg._cls1
    implements Consumer
{

    private final CreateGrooveActivity arg$1;

    public final void accept(Object obj)
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        obj = (Habit)obj;
        HabitDescriptor habitdescriptor = ((Habit) (obj)).getDescriptor();
        if (!NetworkUtil.isConnectedToInternet(creategrooveactivity))
        {
            CalendarExecutor.MAIN.execute(new <init>(creategrooveactivity));
        } else
        {
            if (GrooveSyncTracker.instance == null)
            {
                GrooveSyncTracker.instance = new GrooveSyncTracker();
            }
            GrooveSyncTracker groovesynctracker = GrooveSyncTracker.instance;
            arg._cls1 _lcls1 = new <init>(creategrooveactivity);
            LatencyLoggerHolder.get().markAt(18, habitdescriptor.habitId);
            groovesynctracker.habitCreationListeners.put(habitdescriptor.habitId, _lcls1);
            groovesynctracker.habitDescriptors.put(habitdescriptor.habitId, habitdescriptor);
            creategrooveactivity.timeoutHandler.postDelayed(creategrooveactivity.forceFinishTask, 11500L);
        }
        CalendarExecutor.MAIN.execute(new (creategrooveactivity, ((Habit) (obj))));
    }

    (CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
