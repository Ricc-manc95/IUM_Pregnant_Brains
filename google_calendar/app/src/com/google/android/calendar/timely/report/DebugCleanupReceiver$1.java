// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.content.Context;
import android.os.AsyncTask;

// Referenced classes of package com.google.android.calendar.timely.report:
//            DebugCleanupReceiver

final class val.context extends AsyncTask
{

    private final DebugCleanupReceiver this$0;
    private final Context val$context;

    protected final Object doInBackground(Object aobj[])
    {
        aobj = DebugCleanupReceiver.this;
        aobj = val$context;
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

    ()
    {
        this$0 = final_debugcleanupreceiver;
        val$context = Context.this;
        super();
    }
}
