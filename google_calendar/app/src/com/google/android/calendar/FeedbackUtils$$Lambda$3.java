// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar:
//            FeedbackUtils, AllInOneCalendarActivity

final class arg._cls1
    implements Consumer
{

    private final AllInOneCalendarActivity arg$1;

    public final void accept(Object obj)
    {
        FeedbackUtils.lambda$showSnackbarFeedbackInActivity$2$FeedbackUtils(arg$1, (TimeRangeEntry)obj);
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
