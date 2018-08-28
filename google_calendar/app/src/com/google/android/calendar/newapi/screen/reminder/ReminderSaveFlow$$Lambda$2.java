// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderSaveFlow

final class arg._cls3
    implements Consumer
{

    private final ReminderSaveFlow arg$1;
    private final boolean arg$2;
    private final int arg$3;

    public final void accept(Object obj)
    {
        ReminderSaveFlow remindersaveflow = arg$1;
        boolean flag = arg$2;
        int i = arg$3;
        ((arg._cls3)obj).nTaskSaved(flag, remindersaveflow.task, i);
    }

    (ReminderSaveFlow remindersaveflow, boolean flag, int i)
    {
        arg$1 = remindersaveflow;
        arg$2 = flag;
        arg$3 = i;
    }
}
