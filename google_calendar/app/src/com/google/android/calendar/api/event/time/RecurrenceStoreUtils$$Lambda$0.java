// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.database.Cursor;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurrenceStoreUtils

final class arg._cls1
    implements com.google.android.apps.calendar.util.database._cls1
{

    private final Cursor arg$1;

    public final Object extract(Cursor cursor)
    {
        return RecurrenceStoreUtils.lambda$loadExceptionRecurrence$0$RecurrenceStoreUtils(arg$1, cursor);
    }

    (Cursor cursor)
    {
        arg$1 = cursor;
    }
}
