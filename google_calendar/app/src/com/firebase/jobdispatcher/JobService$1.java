// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import java.util.concurrent.ThreadFactory;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobService

final class this._cls0
    implements ThreadFactory
{

    private final JobService this$0;

    public final Thread newThread(Runnable runnable)
    {
        String s = String.valueOf("FJD.JobService ");
        String s1 = String.valueOf(getClass().getName());
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        return new Thread(runnable, s);
    }

    ()
    {
        this$0 = JobService.this;
        super();
    }
}
