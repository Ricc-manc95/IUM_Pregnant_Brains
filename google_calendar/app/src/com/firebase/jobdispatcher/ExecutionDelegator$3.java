// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;


// Referenced classes of package com.firebase.jobdispatcher:
//            JobServiceConnection

final class val.finalConnectionRef
    implements Runnable
{

    private final JobServiceConnection val$finalConnectionRef;

    public final void run()
    {
        val$finalConnectionRef.enforceInitialBindTimeout();
    }

    ()
    {
        val$finalConnectionRef = jobserviceconnection;
        super();
    }
}
