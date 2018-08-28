// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertActionIntentBuilder

public class QuickResponseActivity extends ListActivity
    implements android.widget.AdapterView.OnItemClickListener
{
    final class QueryThread extends Thread
    {

        private final String body;
        private final EventKey eventKey;
        public final QuickResponseActivity this$0;

        public final void run()
        {
            Intent intent;
            intent = (new AlertActionIntentBuilder(QuickResponseActivity.this)).createEmailIntent(eventKey, body);
            if (intent == null)
            {
                break MISSING_BLOCK_LABEL_42;
            }
            startActivity(intent);
            finish();
            return;
            ActivityNotFoundException activitynotfoundexception;
            activitynotfoundexception;
            class _cls1
                implements Runnable
            {

                private final QueryThread this$1;

                public final void run()
                {
                    Toast.makeText(_fld0, 0x7f1303cc, 1).show();
                    finish();
                }

                _cls1()
                {
                    this$1 = QueryThread.this;
                    super();
                }
            }

            getListView().post(new _cls1());
            return;
        }

        QueryThread(EventKey eventkey)
        {
            this$0 = QuickResponseActivity.this;
            super();
            eventKey = eventkey;
            body = "";
        }

        QueryThread(EventKey eventkey, String s)
        {
            this$0 = QuickResponseActivity.this;
            super();
            eventKey = eventkey;
            body = s;
        }
    }


    private EventKey eventKey;
    private String responses[];

    public QuickResponseActivity()
    {
        responses = null;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getIntent();
        if (bundle == null)
        {
            finish();
            return;
        }
        if (!AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            Toast.makeText(this, 0x7f13034d, 1).show();
            finish();
            return;
        }
        eventKey = (EventKey)bundle.getParcelableExtra("eventKey");
        if (eventKey == null)
        {
            finish();
            return;
        }
        if (!bundle.getBooleanExtra("showQuickResponses", true))
        {
            (new QueryThread(eventKey)).start();
            return;
        }
        getListView().setOnItemClickListener(this);
        bundle = Utils.getQuickResponses(this);
        Arrays.sort(bundle);
        responses = new String[bundle.length + 1];
        int i;
        for (i = 0; i < bundle.length; i++)
        {
            responses[i] = bundle[i];
        }

        responses[i] = getResources().getString(0x7f1303cb);
        setListAdapter(new ArrayAdapter(this, 0x7f050138, responses));
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        view = null;
        adapterview = view;
        if (responses != null)
        {
            adapterview = view;
            if (i < responses.length - 1)
            {
                adapterview = responses[i];
            }
        }
        (new QueryThread(eventKey, adapterview)).start();
    }
}
