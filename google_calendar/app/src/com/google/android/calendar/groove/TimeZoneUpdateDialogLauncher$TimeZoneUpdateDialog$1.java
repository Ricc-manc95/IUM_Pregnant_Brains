// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import java.util.List;

final class val.currentTimeZoneId
    implements android.content.ZoneUpdateDialog._cls1
{

    public final _cls1.execute this$0;
    public final List val$accountsToUpdate;
    public final Context val$context;
    public final String val$currentTimeZoneId;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = val$context;
        if (dialoginterface != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(dialoginterface, "groove", "timezone_dialog_accept", "", null);
        }
        class _cls1 extends AsyncTask
        {

            private final TimeZoneUpdateDialogLauncher.TimeZoneUpdateDialog._cls1 this$1;

            protected final Object doInBackground(Object aobj[])
            {
                for (aobj = accountsToUpdate.iterator(); ((Iterator) (aobj)).hasNext(); SettingsUtils.updateTimezoneSettings((Account)((Iterator) (aobj)).next(), currentTimeZoneId)) { }
                LogUtils.d(TimeZoneUpdateDialogLauncher.TimeZoneUpdateDialog.TAG, "Server timezones updated.", new Object[0]);
                return null;
            }

            protected final void onPostExecute(Object obj)
            {
                obj = this$0;
                TimeZoneUpdateDialogLauncher.TimeZoneUpdateDialog.saveCurrentTimeZoneAsLastDisplayed(context, currentTimeZoneId);
            }

            _cls1()
            {
                this$1 = TimeZoneUpdateDialogLauncher.TimeZoneUpdateDialog._cls1.this;
                super();
            }
        }

        (new _cls1()).execute(new Void[0]);
    }

    _cls1()
    {
        this$0 = final__pcls1;
        val$context = context1;
        val$accountsToUpdate = list;
        val$currentTimeZoneId = String.this;
        super();
    }
}
