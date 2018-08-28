// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            ReminderResultCreator

final class arg._cls1
    implements Consumer
{

    private final ReminderResultCreator arg$1;

    public final void accept(Object obj)
    {
        arg$1.lastFetchedAssistance = (byte[])obj;
    }

    (ReminderResultCreator reminderresultcreator)
    {
        arg$1 = reminderresultcreator;
    }
}
