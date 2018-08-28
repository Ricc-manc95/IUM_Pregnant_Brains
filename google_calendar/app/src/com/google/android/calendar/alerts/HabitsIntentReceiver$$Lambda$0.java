// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentReceiver, HabitsIntentServiceHelper

final class arg._cls3
    implements Runnable
{

    private final HabitsIntentReceiver arg$1;
    private final Context arg$2;
    private final Intent arg$3;

    public final void run()
    {
        HabitsIntentReceiver habitsintentreceiver = arg$1;
        Context context = arg$2;
        Intent intent = arg$3;
        if (habitsintentreceiver.habitsServiceHelper == null)
        {
            habitsintentreceiver.habitsServiceHelper = new HabitsIntentServiceHelper(context);
        }
        habitsintentreceiver.habitsServiceHelper.onHandle(intent);
    }

    (HabitsIntentReceiver habitsintentreceiver, Context context, Intent intent)
    {
        arg$1 = habitsintentreceiver;
        arg$2 = context;
        arg$3 = intent;
    }
}
