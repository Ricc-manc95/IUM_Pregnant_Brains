// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.ActivityNotFoundException;
import android.widget.ListView;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.alerts:
//            QuickResponseActivity, AlertActionIntentBuilder

final class body extends Thread
{

    private final String body;
    private final EventKey eventKey;
    public final QuickResponseActivity this$0;

    public final void run()
    {
        android.content.Intent intent;
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

            private final QuickResponseActivity.QueryThread this$1;

            public final void run()
            {
                Toast.makeText(this$0, 0x7f1303cc, 1).show();
                finish();
            }

            _cls1()
            {
                this$1 = QuickResponseActivity.QueryThread.this;
                super();
            }
        }

        getListView().post(new _cls1());
        return;
    }

    _cls1(EventKey eventkey)
    {
        this$0 = QuickResponseActivity.this;
        super();
        eventKey = eventkey;
        body = "";
    }

    body(EventKey eventkey, String s)
    {
        this$0 = QuickResponseActivity.this;
        super();
        eventKey = eventkey;
        body = s;
    }
}
