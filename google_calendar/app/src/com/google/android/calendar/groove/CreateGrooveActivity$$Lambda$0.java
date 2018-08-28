// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.utils.snackbar.SnackbarFeedbackUtils;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls1
    implements Runnable
{

    private final CreateGrooveActivity arg$1;

    public final void run()
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        SnackbarFeedbackUtils.showSnackbarFeedback(creategrooveactivity, creategrooveactivity.getResources().getString(0x7f1300bf), true, null, 0);
        creategrooveactivity.finish();
        creategrooveactivity.logger.trackEvent(creategrooveactivity, creategrooveactivity.analyticsCategory, "goal_creation_timeout");
    }

    (CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
