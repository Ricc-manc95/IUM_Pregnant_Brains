// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.snackbar;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.calendar.api.event.EventKey;

public final class SnackbarFeedbackUtils
{

    public static void showSnackbarFeedback(Context context, String s, boolean flag, EventKey eventkey, int i)
    {
        Intent intent = new Intent("com.google.android.calendar.intent.action.ACTION_SHOW_FEEDBACK");
        intent.putExtra("feedbackType", 0);
        intent.putExtra("feedbackMessage", s);
        intent.putExtra("shortLength", flag);
        if (eventkey != null)
        {
            intent.putExtra("eventKey", eventkey);
        }
        intent.putExtra("eventAction", i);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
