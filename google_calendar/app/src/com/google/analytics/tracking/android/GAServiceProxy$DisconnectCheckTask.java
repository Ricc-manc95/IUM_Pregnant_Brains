// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

// Referenced classes of package com.google.analytics.tracking.android:
//            GAServiceProxy, Clock

final class this._cls0 extends TimerTask
{

    private final GAServiceProxy this$0;

    public final void run()
    {
        if (state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 == android.support.v4.content.RVICE._fld9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 && queue.isEmpty() && lastRequestTime + idleTimeout < clock.currentTimeMillis())
        {
            disconnectFromService();
            return;
        } else
        {
            disconnectCheckTimer.schedule(new <init>(), idleTimeout);
            return;
        }
    }

    ()
    {
        this$0 = GAServiceProxy.this;
        super();
    }
}