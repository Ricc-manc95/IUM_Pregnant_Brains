// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.timely.LoadingStateManager;
import com.google.android.syncadapters.calendar.timely.DebugReportingConstants;
import com.google.android.syncadapters.calendar.timely.consistency.ConsistencyChecker;

// Referenced classes of package com.google.android.calendar.timely.report:
//            DebugReportingReceiver

public class ViewDebugReportActivity extends CalendarSupportActivity
{
    static final class ReportingServiceActionOnClick
        implements android.view.View.OnClickListener
    {

        private final String action;
        private final Activity activity;
        private final Bundle extras;

        public final void onClick(View view)
        {
            view = new Intent(activity, com/google/android/calendar/timely/report/DebugReportingReceiver);
            view.setAction(action);
            view.setData(activity.getIntent().getData());
            view.putExtras(activity.getIntent().getExtras());
            if (extras != null)
            {
                view.putExtras(extras);
            }
            activity.sendBroadcast(view);
            activity.finish();
        }

        public ReportingServiceActionOnClick(Activity activity1, String s, Bundle bundle)
        {
            activity = activity1;
            action = s;
            extras = bundle;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/timely/consistency/ConsistencyChecker);

    public ViewDebugReportActivity()
    {
    }

    protected void onCreate(final Bundle fileName)
    {
        super.onCreate(fileName);
        setContentView(0x7f050020);
        fileName = getSupportActionBar();
        if (fileName != null)
        {
            fileName.setTitle(getString(0x7f13030c));
            fileName.setIcon(0x7f0200fb);
            fileName.setDisplayShowHomeEnabled(true);
        }
        fileName = getIntent().getData().getPath();
        final LoadingStateManager loadingManager = getIntent().getStringExtra(DebugReportingConstants.EXTRA_CALENDAR_ID);
        int i = getIntent().getIntExtra("report_type", 0);
        final TextView reportView = (TextView)findViewById(0x7f10010e);
        Object obj = (TextView)findViewById(0x7f10010b);
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(36)).append("Unsupported report type: ").append(i).toString());

        case 0: // '\0'
            ((TextView) (obj)).setText(getString(0x7f1300ec, new Object[] {
                loadingManager
            }));
            break;
        }
        loadingManager = (Button)findViewById(0x7f10010f);
        obj = (Button)findViewById(0x7f100110);
        Button button = (Button)findViewById(0x7f100111);
        Bundle bundle = new Bundle();
        bundle.putBoolean(DebugReportingReceiver.EXTRA_DONT_ASK_AGAIN, true);
        loadingManager.setOnClickListener(new ReportingServiceActionOnClick(this, DebugReportingReceiver.ACTION_SEND_REPORT, null));
        ((Button) (obj)).setOnClickListener(new ReportingServiceActionOnClick(this, DebugReportingReceiver.ACTION_DELETE_REPORT, null));
        button.setOnClickListener(new ReportingServiceActionOnClick(this, DebugReportingReceiver.ACTION_DELETE_REPORT, bundle));
        loadingManager = new LoadingStateManager(findViewById(0x7f1000e5), findViewById(0x7f10010c));
        loadingManager.startLoadingPhases();
        (new _cls1()).execute(new Void[0]);
    }


    private class _cls1 extends AsyncTask
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
                IOUtils.copy(openFileInput(fileName), ((java.io.OutputStream) (obj)), true);
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
                Toast.makeText(activity, ((CharSequence) (obj)), 1).show();
                obj = new Intent(activity, com/google/android/calendar/timely/report/DebugReportingReceiver);
                ((Intent) (obj)).setAction(DebugReportingReceiver.ACTION_DELETE_REPORT);
                ((Intent) (obj)).setData(activity.getIntent().getData());
                ((Intent) (obj)).putExtras(activity.getIntent().getExtras());
                activity.sendBroadcast(((Intent) (obj)));
                finish();
                return;
            } else
            {
                reportView.setText(((CharSequence) (obj)));
                loadingManager.onDataLoaded();
                return;
            }
        }

        _cls1()
        {
            this$0 = ViewDebugReportActivity.this;
            fileName = s;
            activity = viewdebugreportactivity1;
            reportView = textview;
            loadingManager = loadingstatemanager;
            super();
        }
    }

}
