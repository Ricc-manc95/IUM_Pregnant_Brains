// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.apps.xplat.logging.LoggingApi;
import com.google.apps.xplat.logging.XLogLevel;
import com.google.apps.xplat.logging.XLogger;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.calendar.v2a.android.util.job:
//            FutureJobService

final class this._cls1
    implements FutureCallback
{

    private final FT_ERROR this$1;

    public final void onFailure(Throwable throwable)
    {
        FutureJobService futurejobservice = _fld0;
        JobParameters jobparameters = b;
        futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("finishJob, tag=%s", jobparameters.getTag());
        boolean flag;
        if (futurejobservice.onFinishJobInternal(jobparameters, null, throwable) == FT_ERROR)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("-> %s", Boolean.valueOf(flag));
        futurejobservice.jobFinished(jobparameters, flag);
    }

    public final void onSuccess(Object obj)
    {
        FutureJobService futurejobservice = _fld0;
        JobParameters jobparameters = b;
        futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("finishJob, tag=%s", jobparameters.getTag());
        boolean flag;
        if (futurejobservice.onFinishJobInternal(jobparameters, obj, null) == FT_ERROR)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("-> %s", Boolean.valueOf(flag));
        futurejobservice.jobFinished(jobparameters, flag);
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
