// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import android.os.AsyncTask;

// Referenced classes of package com.google.android.calendar.alerts:
//            NotificationActionTrampoline, AlertServiceHelper

static final class applicationContext extends AsyncTask
{

    private final Context applicationContext;

    protected final Object doInBackground(Object aobj[])
    {
        AlertServiceHelper.updateAlertNotification(applicationContext);
        return null;
    }

    (Context context)
    {
        applicationContext = context.getApplicationContext();
    }
}
