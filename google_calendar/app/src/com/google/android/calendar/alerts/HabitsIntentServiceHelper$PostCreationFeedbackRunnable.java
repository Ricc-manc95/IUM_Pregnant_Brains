// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentServiceHelper

final class startTimeMillis
    implements Runnable
{

    private EventKey eventKey;
    private long startTimeMillis;
    private final HabitsIntentServiceHelper this$0;

    public final void run()
    {
        android.content.Context context = HabitsIntentServiceHelper.this.context;
        EventKey eventkey = eventKey;
        long l = startTimeMillis;
        Intent intent = new Intent("com.google.android.calendar.intent.action.ACTION_SHOW_FEEDBACK");
        intent.putExtra("feedbackType", 1);
        intent.putExtra("eventKey", eventkey);
        intent.putExtra("timeMillis", l);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    (EventKey eventkey, long l)
    {
        this$0 = HabitsIntentServiceHelper.this;
        super();
        eventKey = eventkey;
        startTimeMillis = l;
    }
}
