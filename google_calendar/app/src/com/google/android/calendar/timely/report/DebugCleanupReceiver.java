// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DebugCleanupReceiver extends BroadcastReceiver
{

    public DebugCleanupReceiver()
    {
    }

    public void onReceive(final Context context, Intent intent)
    {
        (new _cls1()).execute(new Void[0]);
    }

    private class _cls1 extends AsyncTask
    {

        private final DebugCleanupReceiver this$0;
        private final Context val$context;

        protected final Object doInBackground(Object aobj[])
        {
            aobj = DebugCleanupReceiver.this;
            aobj = context;
            String as[] = ((Context) (aobj)).fileList();
            int j = as.length;
            for (int i = 0; i < j; i++)
            {
                String s = as[i];
                if (s.startsWith("consistency_report"))
                {
                    ((Context) (aobj)).deleteFile(s);
                }
            }

            return null;
        }

        _cls1()
        {
            this$0 = DebugCleanupReceiver.this;
            context = context1;
            super();
        }
    }

}
