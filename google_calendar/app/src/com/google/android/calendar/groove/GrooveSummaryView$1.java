// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.res.Resources;
import android.os.Handler;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveSummaryView

final class this._cls0
    implements Runnable
{

    private final GrooveSummaryView this$0;

    public final void run()
    {
        String as[] = getResources().getStringArray(0x7f0b0030);
        waitingTextView.setText(as[GrooveSummaryView.waitingTextIndex]);
        GrooveSummaryView.waitingTextIndex++;
        if (GrooveSummaryView.waitingTextIndex < as.length)
        {
            handler.postDelayed(waitingTextUpdateTask, 2500L);
        }
    }

    ()
    {
        this$0 = GrooveSummaryView.this;
        super();
    }
}
