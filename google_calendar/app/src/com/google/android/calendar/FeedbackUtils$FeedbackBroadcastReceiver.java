// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.google.android.calendar:
//            FeedbackUtils, AllInOneCalendarActivity

public static final class activity extends BroadcastReceiver
{

    public final AllInOneCalendarActivity activity;

    public final void onReceive(Context context, Intent intent)
    {
        FeedbackUtils.showFeedbackInActivity(activity, intent);
    }

    public I(AllInOneCalendarActivity allinonecalendaractivity)
    {
        activity = allinonecalendaractivity;
    }
}
