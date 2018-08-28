// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar:
//            FeedbackUtils, AllInOneCalendarActivity

final class arg._cls1
    implements Supplier
{

    private final AllInOneCalendarActivity arg$1;

    public final Object get()
    {
        return FeedbackUtils.lambda$showSnackbarFeedbackInActivity$1$FeedbackUtils(arg$1);
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
