// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.utils.snackbar.SnackbarFeedbackUtils;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentServiceHelper

static final class action
    implements Runnable
{

    private int action;
    private Context context;
    private EventKey eventKey;
    private String feedbackText;

    public final void run()
    {
        if (eventKey == null)
        {
            SnackbarFeedbackUtils.showSnackbarFeedback(context, feedbackText, false, null, 0);
            return;
        } else
        {
            SnackbarFeedbackUtils.showSnackbarFeedback(context, feedbackText, false, eventKey, action);
            return;
        }
    }

    (Context context1, int i)
    {
        this(context1, context1.getString(i), null, 0);
    }

    <init>(Context context1, String s, EventKey eventkey, int i)
    {
        context = context1;
        feedbackText = s;
        eventKey = eventkey;
        action = i;
    }
}
