// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.IntentService;
import android.content.Intent;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentServiceHelper, HabitsIntentReceiver

public class HabitsIntentService extends IntentService
{

    public HabitsIntentService()
    {
        super(com/google/android/calendar/alerts/HabitsIntentService.getSimpleName());
    }

    protected void onHandleIntent(Intent intent)
    {
        (new HabitsIntentServiceHelper(this)).onHandle(intent);
        HabitsIntentReceiver.completeWakefulIntent(intent);
    }
}
