// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.external.service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.android.emailcommon.Device;
import com.android.emailcommon.service.EmailServiceProxy;
import com.android.emailcommon.service.IEmailService;
import com.android.mail.log.LogUtils;
import java.io.IOException;
import java.util.List;

// Referenced classes of package com.android.emailcommon.external.service:
//            ServiceProxy

public final class RemoteServiceProxy extends ServiceProxy
    implements EmailServiceProxy, IEmailService
{

    public IEmailService service;

    public RemoteServiceProxy(Context context, Intent intent)
    {
        super(context, intent);
        try
        {
            Device.getDeviceId(context);
        }
        // Misplaced declaration of an exception variable
        catch (Intent intent) { }
        context.getCacheDir();
    }

    public final IBinder asBinder()
    {
        return null;
    }

    public final int getApiVersion()
    {
        Object obj = new _cls8();
        setTask(((ServiceProxy.ProxyTask) (obj)), "getApiVersion");
        waitForCompletion();
        obj = (Integer)((ServiceProxy.CallableProxyTask) (obj)).result;
        if (obj == null)
        {
            LogUtils.wtf("EmailServiceProxy", "failed to get api version", new Object[0]);
            return -1;
        } else
        {
            return ((Integer) (obj)).intValue();
        }
    }

    public final String getProtocolVersion(final String accountName)
    {
        accountName = new _cls7();
        setTask(accountName, "getProtocolVersion");
        waitForCompletion();
        return (String)((ServiceProxy.CallableProxyTask) (accountName)).result;
    }

    public final void onConnected(IBinder ibinder)
    {
        if (ibinder == null)
        {
            ibinder = null;
        } else
        {
            android.os.IInterface iinterface = ibinder.queryLocalInterface("com.android.emailcommon.service.IEmailService");
            if (iinterface instanceof IEmailService)
            {
                ibinder = (IEmailService)iinterface;
            } else
            {
                ibinder = new com.android.emailcommon.service.IEmailService.Stub.Proxy(ibinder);
            }
        }
        service = ibinder;
    }

    public final List retrieveRecipientAvailabilities(final String accountName, final List recipients, final long startTime, final long endTime)
    {
        accountName = new _cls6();
        setTask(accountName, "retrieveRecipientAvailabilities");
        waitForCompletion();
        return (List)((ServiceProxy.CallableProxyTask) (accountName)).result;
    }

    private class _cls8 extends ServiceProxy.CallableProxyTask
    {

        private final RemoteServiceProxy this$0;

        public final Object call()
            throws RemoteException
        {
            return Integer.valueOf(service.getApiVersion());
        }

        _cls8()
        {
            this$0 = RemoteServiceProxy.this;
            super();
        }
    }


    private class _cls7 extends ServiceProxy.CallableProxyTask
    {

        private final RemoteServiceProxy this$0;
        private final String val$accountName;

        public final Object call()
            throws RemoteException
        {
            return service.getProtocolVersion(accountName);
        }

        _cls7()
        {
            this$0 = RemoteServiceProxy.this;
            accountName = s;
            super();
        }
    }


    private class _cls6 extends ServiceProxy.CallableProxyTask
    {

        private final RemoteServiceProxy this$0;
        private final String val$accountName;
        private final long val$endTime;
        private final List val$recipients;
        private final long val$startTime;

        public final Object call()
            throws RemoteException
        {
            return service.retrieveRecipientAvailabilities(accountName, recipients, startTime, endTime);
        }

        _cls6()
        {
            this$0 = RemoteServiceProxy.this;
            accountName = s;
            recipients = list;
            startTime = l;
            endTime = l1;
            super();
        }
    }

}
