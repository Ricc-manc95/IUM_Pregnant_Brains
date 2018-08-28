// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.launch.uri:
//            ViewEventUriHandler

final class val.activity extends AsyncQueryHandler
{

    private final Activity val$activity;
    private final Intent val$intent;
    private final int val$status;

    protected final void onUpdateComplete(int i, Object obj, int j)
    {
        if (j == 0)
        {
            LogUtils.w(ViewEventUriHandler.TAG, "No rows updated - starting event viewer", new Object[0]);
            val$intent.putExtra("attendeeStatus", val$status);
            val$activity.startActivity(val$intent);
            return;
        }
        val$status;
        JVM INSTR tableswitch 1 4: default 76
    //                   1 77
    //                   2 93
    //                   3 76
    //                   4 99;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return;
_L2:
        i = 0x7f130420;
_L6:
        Toast.makeText(val$activity, i, 1).show();
        return;
_L3:
        i = 0x7f130421;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0x7f130423;
        if (true) goto _L6; else goto _L5
_L5:
    }

    (Activity activity1)
    {
        val$intent = intent1;
        val$status = i;
        val$activity = activity1;
        super(final_contentresolver);
    }
}
