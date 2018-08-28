// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.api.habit.HabitReminders;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls2
    implements Consumer
{

    private final CreateGrooveActivity arg$1;
    private final boolean arg$2;

    public final void accept(Object obj)
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        boolean flag = arg$2;
        obj = (Integer)obj;
        creategrooveactivity.habitModifications.setReminders(new HabitReminders(false, ((Integer) (obj)), flag, flag));
    }

    (CreateGrooveActivity creategrooveactivity, boolean flag)
    {
        arg$1 = creategrooveactivity;
        arg$2 = flag;
    }
}
