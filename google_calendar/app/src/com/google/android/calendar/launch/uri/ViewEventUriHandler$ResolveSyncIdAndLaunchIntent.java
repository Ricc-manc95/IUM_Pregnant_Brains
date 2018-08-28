// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.launch.uri:
//            ViewEventUriHandler

public final class activity extends AsyncTask
{

    private final Activity activity;
    private final ViewEventUriHandler this$0;

    protected final Object doInBackground(Object aobj[])
    {
        Intent intent = null;
        aobj = ViewEventUriHandler.this;
        if (((ViewEventUriHandler) (aobj)).eidParts != null)
        {
            aobj = ((ViewEventUriHandler) (aobj)).eidParts[0];
        } else
        {
            aobj = null;
        }
        if (aobj == null)
        {
            LogUtils.i(ViewEventUriHandler.TAG, "Could not find event for uri: %s", new Object[] {
                mIntent.getData()
            });
        } else
        {
            Intent intent1 = createIntentForEvent(activity, ((String) (aobj)));
            intent = intent1;
            if (intent1 == null)
            {
                return createIntentForInstance(activity, ((String) (aobj)));
            }
        }
        return intent;
    }

    protected final void onPostExecute(Object obj)
    {
        Intent intent = (Intent)obj;
        ViewEventUriHandler vieweventurihandler = ViewEventUriHandler.this;
        obj = ViewEventUriHandler.this;
        if (((ViewEventUriHandler) (obj)).eidParts != null)
        {
            obj = ((ViewEventUriHandler) (obj)).eidParts[1];
        } else
        {
            obj = null;
        }
        vieweventurihandler.launchGeneratedEventIntent(intent, ((String) (obj)), activity);
    }

    public (Activity activity1)
    {
        this$0 = ViewEventUriHandler.this;
        super();
        activity = activity1;
    }
}
