// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.external.service;

import android.os.RemoteException;
import com.android.emailcommon.service.IEmailService;

// Referenced classes of package com.android.emailcommon.external.service:
//            RemoteServiceProxy

final class ProxyTask extends ProxyTask
{

    private final RemoteServiceProxy this$0;
    private final String val$accountName;

    public final Object call()
        throws RemoteException
    {
        return service.getProtocolVersion(val$accountName);
    }

    ProxyTask()
    {
        this$0 = final_remoteserviceproxy;
        val$accountName = String.this;
        super();
    }
}
