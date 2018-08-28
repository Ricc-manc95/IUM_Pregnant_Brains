// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.accounts.Account;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.gms.reminders.model.RemindersBuffer;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            SimpleTaskDataLoader

final class arg._cls3
    implements Function
{

    private final SimpleTaskDataLoader arg$1;
    private final Account arg$2;
    private final GoogleSettings arg$3;

    public final Object apply(Object obj)
    {
        SimpleTaskDataLoader simpletaskdataloader = arg$1;
        Account account = arg$2;
        GoogleSettings googlesettings = arg$3;
        return simpletaskdataloader.convertTasksForAccount((RemindersBuffer)obj, account, googlesettings.getTaskColor().getValue());
    }

    (SimpleTaskDataLoader simpletaskdataloader, Account account, GoogleSettings googlesettings)
    {
        arg$1 = simpletaskdataloader;
        arg$2 = account;
        arg$3 = googlesettings;
    }
}
