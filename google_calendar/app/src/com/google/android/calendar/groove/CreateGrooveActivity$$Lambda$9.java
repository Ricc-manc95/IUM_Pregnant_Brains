// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls1
    implements ener
{

    private final CreateGrooveActivity arg$1;

    public final void onHabitInstancesSynced()
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        CalendarExecutor.MAIN.execute(new (creategrooveactivity));
    }

    (CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
