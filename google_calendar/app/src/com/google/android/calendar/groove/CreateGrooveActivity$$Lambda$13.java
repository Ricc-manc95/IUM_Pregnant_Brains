// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.os.Handler;
import com.google.android.calendar.analytics.AnalyticsLogger;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls1
    implements Runnable
{

    private final CreateGrooveActivity arg$1;

    public final void run()
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        creategrooveactivity.timeoutHandler.removeCallbacks(creategrooveactivity.forceFinishTask);
        creategrooveactivity.finish();
        creategrooveactivity.logger.trackEvent(creategrooveactivity, creategrooveactivity.analyticsCategory, "goal_creation_success");
    }

    (CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
