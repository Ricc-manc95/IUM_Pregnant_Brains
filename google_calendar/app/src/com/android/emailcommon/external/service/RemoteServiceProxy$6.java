// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.external.service;

import android.os.RemoteException;
import com.android.emailcommon.service.IEmailService;
import java.util.List;

// Referenced classes of package com.android.emailcommon.external.service:
//            RemoteServiceProxy

final class ProxyTask extends ProxyTask
{

    private final RemoteServiceProxy this$0;
    private final String val$accountName;
    private final long val$endTime;
    private final List val$recipients;
    private final long val$startTime;

    public final Object call()
        throws RemoteException
    {
        return service.retrieveRecipientAvailabilities(val$accountName, val$recipients, val$startTime, val$endTime);
    }

    ProxyTask()
    {
        this$0 = final_remoteserviceproxy;
        val$accountName = s;
        val$recipients = list;
        val$startTime = l;
        val$endTime = J.this;
        super();
    }
}
