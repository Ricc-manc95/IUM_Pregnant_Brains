// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import android.os.Handler;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.habit.HabitDescriptor;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentServiceHelper

final class arg._cls6
    implements Consumer
{

    private final EventKey arg$1;
    private final Context arg$2;
    private final int arg$3;
    private final Handler arg$4;
    private final String arg$5;
    private final HabitDescriptor arg$6;

    public final void accept(Object obj)
    {
        HabitsIntentServiceHelper.lambda$deferHabit$0$HabitsIntentServiceHelper(arg$1, arg$2, arg$3, arg$4, arg$5, arg$6, (Event)obj);
    }

    (EventKey eventkey, Context context, int i, Handler handler, String s, HabitDescriptor habitdescriptor)
    {
        arg$1 = eventkey;
        arg$2 = context;
        arg$3 = i;
        arg$4 = handler;
        arg$5 = s;
        arg$6 = habitdescriptor;
    }
}
