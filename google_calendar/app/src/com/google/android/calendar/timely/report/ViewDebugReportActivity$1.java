// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.LoadingStateManager;
import com.google.api.client.util.IOUtils;
import com.google.common.base.Platform;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.timely.report:
//            ViewDebugReportActivity, DebugReportingReceiver

final class val.loadingManager extends AsyncTask
{

    private final ViewDebugReportActivity this$0;
    private final ViewDebugReportActivity val$activity;
    private final String val$fileName;
    private final LoadingStateManager val$loadingManager;
    private final TextView val$reportView;

    private final transient String doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPFADQ74QBECSTG____0()
    {
        Object obj;
        try
        {
            obj = new ByteArrayOutputStream();
            IOUtils.copy(openFileInput(val$fileName), ((java.io.OutputStream) (obj)), true);
            obj = ((ByteArrayOutputStream) (obj)).toString();
        }
        catch (IOException ioexception)
        {
            LogUtils.e(ViewDebugReportActivity.TAG, ioexception, "Failed to load report", new Object[0]);
            return null;
        }
        return ((String) (obj));
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        return doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPFADQ74QBECSTG____0();
    }

    protected final void onPostExecute(Object obj)
    {
        obj = (String)obj;
        if (Platform.stringIsNullOrEmpty(((String) (obj))))
        {
            LogUtils.e(ViewDebugReportActivity.TAG, "Null or empty report contents", new Object[0]);
            obj = getString(0x7f1301cd);
            Toast.makeText(val$activity, ((CharSequence) (obj)), 1).show();
            obj = new Intent(val$activity, com/google/android/calendar/timely/report/DebugReportingReceiver);
            ((Intent) (obj)).setAction(DebugReportingReceiver.ACTION_DELETE_REPORT);
            ((Intent) (obj)).setData(val$activity.getIntent().getData());
            ((Intent) (obj)).putExtras(val$activity.getIntent().getExtras());
            val$activity.sendBroadcast(((Intent) (obj)));
            finish();
            return;
        } else
        {
            val$reportView.setText(((CharSequence) (obj)));
            val$loadingManager.onDataLoaded();
            return;
        }
    }

    I()
    {
        this$0 = final_viewdebugreportactivity;
        val$fileName = s;
        val$activity = viewdebugreportactivity1;
        val$reportView = textview;
        val$loadingManager = LoadingStateManager.this;
        super();
    }
}
