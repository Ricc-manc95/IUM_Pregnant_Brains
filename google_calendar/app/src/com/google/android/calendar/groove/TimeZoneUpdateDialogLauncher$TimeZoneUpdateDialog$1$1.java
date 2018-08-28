// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.accounts.Account;
import android.os.AsyncTask;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.settings.SettingsUtils;
import java.util.Iterator;
import java.util.List;

final class this._cls1 extends AsyncTask
{

    private final l.currentTimeZoneId this$1;

    protected final Object doInBackground(Object aobj[])
    {
        for (aobj = accountsToUpdate.iterator(); ((Iterator) (aobj)).hasNext(); SettingsUtils.updateTimezoneSettings((Account)((Iterator) (aobj)).next(), currentTimeZoneId)) { }
        LogUtils.d(l.currentTimeZoneId, "Server timezones updated.", new Object[0]);
        return null;
    }

    protected final void onPostExecute(Object obj)
    {
        obj = _fld0;
        CurrentTimeZoneAsLastDisplayed(context, currentTimeZoneId);
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
